# DDK Docker build environment

This image provides Maven 3.9, Eclipse Temurin 21, GTK 3, and Xvfb for
consistent Linux builds and headless Eclipse/SWT tests. It works with Docker
Desktop's Linux engine on Windows as well as with Docker Engine on Linux.

## Build the image

From the repository root:

```bash
docker build --pull -t ddk-build docker
```

The default base is `maven:3.9.16-eclipse-temurin-21-noble`. To use a registry
mirror, override it without copying the Dockerfile:

```bash
docker build --build-arg MAVEN_IMAGE=<mirror>/maven:3.9.16-eclipse-temurin-21-noble \
  -t ddk-build docker
```

## Run the full build on Windows

Use PowerShell from a full clone's repository root:

```powershell
$ddkWorkspace = (Get-Location).Path
docker volume create ddk-m2
docker run --rm --init `
  --mount "type=bind,source=$ddkWorkspace,target=/workspace" `
  --mount "type=volume,source=ddk-m2,target=/root/.m2" `
  --workdir /workspace `
  ddk-build `
  xvfb-run --auto-servernum --error-file=/dev/stderr `
  -- `
  mvn -T 3C clean verify checkstyle:check pmd:pmd pmd:cpd pmd:check pmd:cpd-check spotbugs:check `
    -f ./ddk-parent/pom.xml --batch-mode --fail-at-end
```

After Maven exits, verify that the expected Surefire report was actually
created:

```powershell
docker run --rm `
  --mount "type=bind,source=$ddkWorkspace,target=/workspace" `
  --workdir /workspace `
  ddk-build `
  bash ./.github/scripts/check-surefire-reports.sh
```

Docker Desktop runs this command in Linux even though the source tree is on
Windows. Tycho automatically selects the `64bit_linux` profile and resolves the
Linux x86-64 SWT and launcher fragments.

Quote Maven `-D` properties passed through `docker run` in PowerShell, for
example `'-DskipTests'` or `'-Dtest.timeout=7200'`. Otherwise PowerShell may
split a dotted property name before Docker receives it.

## Run the full build on Linux or macOS

```bash
docker volume create ddk-m2
docker run --rm --init \
  --mount "type=bind,source=${PWD},target=/workspace" \
  --mount "type=volume,source=ddk-m2,target=/root/.m2" \
  --workdir /workspace \
  ddk-build \
  xvfb-run --auto-servernum --error-file=/dev/stderr \
  -- \
  mvn -T 3C clean verify checkstyle:check pmd:pmd pmd:cpd pmd:check pmd:cpd-check spotbugs:check \
    -f ./ddk-parent/pom.xml --batch-mode --fail-at-end
```

Then run `./.github/scripts/check-surefire-reports.sh` from the checkout.

The named volume retains Maven and p2 downloads. Delete it with
`docker volume rm ddk-m2` when a completely cold dependency cache is required.

`--init` is required. Without an init process, `xvfb-run` becomes PID 1 and can
wait indefinitely for Xvfb's readiness signal instead of starting Maven.
`--auto-servernum` also prevents collisions when more than one container runs.

The container runs as root, matching the upstream Maven image. This is harmless
for Docker Desktop mounts, but a native Linux host may see root-owned build
outputs in the checkout.

## ARM64

Build and run an ARM64 image explicitly:

```bash
docker build --platform linux/arm64 -t ddk-build:arm64 docker
docker run --rm --init --platform linux/arm64 \
  --mount "type=bind,source=${PWD},target=/workspace" \
  --mount "type=volume,source=ddk-m2-arm64,target=/root/.m2" \
  --workdir /workspace \
  ddk-build:arm64 \
  xvfb-run --auto-servernum --error-file=/dev/stderr \
  -- \
  mvn -T 3C clean verify -f ./ddk-parent/pom.xml --batch-mode --fail-at-end \
    -Dtest.timeout=7200
```

Tycho automatically selects `64bit_linux_aarch64`. Docker Desktop can emulate
ARM64 on an x86-64 Windows machine, but a native ARM64 runner is substantially
faster. The example raises Tycho's test-runtime timeout from 1,800 to 7,200
seconds for emulation; a native ARM64 host can normally omit that property.
Keep separate Maven volumes for the two architectures so native p2 artifacts
cannot be mixed.

In PowerShell, reuse the Windows command above with
`--platform linux/arm64`, image `ddk-build:arm64`, and volume
`ddk-m2-arm64`; also add the quoted argument `'-Dtest.timeout=7200'`.

## Linked Git worktrees

Mount a full clone when possible. A linked worktree has a `.git` file pointing
to metadata in the parent clone, normally outside the container mount. Tycho's
JGit-based build qualifier then fails with `repository not found`.

If a linked worktree is unavoidable, mount both the worktree and the parent
clone at paths that preserve the `.git` file's reference. A standalone clone is
usually simpler, especially on Windows.

## TLS-intercepting networks

Do not modify a running `--rm` container: the change disappears with the
container. Build a local derived image containing the organization's root CA.
For example, place `company-root-ca.crt` beside a local Dockerfile containing:

```dockerfile
ARG BASE_IMAGE=ddk-build
FROM ${BASE_IMAGE}
COPY company-root-ca.crt /usr/local/share/ca-certificates/company-root-ca.crt
RUN update-ca-certificates \
    && keytool -importcert -trustcacerts -cacerts \
        -alias company-root-ca \
        -file /usr/local/share/ca-certificates/company-root-ca.crt \
        -storepass changeit -noprompt
```

Then build that directory with `docker build -t ddk-build:corporate .` and use
`ddk-build:corporate` in the commands above. Keep private CA files out of Git.

## Expected warnings

Xvfb may print unresolved `XF86...` multimedia key symbols, and SWT may report
that GNOME or Xfce session managers are unavailable. These are non-fatal in the
headless test environment and do not justify installing a desktop session.
