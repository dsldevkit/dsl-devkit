# DDK Docker build environment

A Linux image with Java, Maven, GTK and Xvfb, for running the full build and the UI tests headlessly on any machine with Docker (x86-64 or ARM64).

Build the image from the repository root:

```bash
docker build --pull -t ddk-build docker
```

Run any build command from [AGENTS.md](../AGENTS.md#build-commands) in it, for example the full CI build, which already starts with `xvfb-run`:

```bash
docker run --rm --init \
  --mount "type=bind,source=${PWD},target=/workspace" \
  --mount "type=volume,source=ddk-m2,target=/root/.m2" \
  ddk-build \
  <command from AGENTS.md>
```

In PowerShell, use `$PWD.Path` for the source and backticks for the line breaks, and quote `-D` arguments (`'-DskipTests'`).

- **Use a full clone, not a linked worktree:** a worktree's `.git` file points outside the mount, and Tycho's build qualifier then fails with `repository not found`.
- **`--init` is required:** without it `xvfb-run` runs as PID 1 and can hang before starting Maven.
- The `ddk-m2` volume keeps Maven and p2 downloads between runs; on a machine that builds both architectures, use one volume per architecture.
- The container runs as root, so on a Linux host the build output in the checkout is owned by root.
