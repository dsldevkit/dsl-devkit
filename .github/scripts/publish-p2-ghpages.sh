#!/usr/bin/env bash
# Publishes the p2 update site to the gh-pages branch.
#
# Snapshot mode (RELEASE_VERSION unset):
#   Copies ddk-repository/target/repository/ → p2/snapshots/<sha8>/
#   Updates p2/snapshots/latest/ composite repository
#
# Release mode (RELEASE_VERSION set):
#   Copies p2/snapshots/<SNAPSHOT_SHA>/ → p2/releases/<RELEASE_VERSION>/
#   Updates p2/releases/latest/ composite repository
#
# Required env vars: GITHUB_TOKEN, GITHUB_SHA, GITHUB_REPOSITORY
# Release-only vars: RELEASE_VERSION, SNAPSHOT_SHA

set -euo pipefail

write_composite() {
  local dir="$1"
  local name="$2"
  local child="$3"
  rm -rf "$dir"
  mkdir -p "$dir"
  cat > "$dir/p2.index" << 'EOF'
version=1
metadata.repository.factory.order=compositeContent.xml,\!
artifact.repository.factory.order=compositeArtifacts.xml,\!
EOF
  cat > "$dir/compositeContent.xml" << EOF
<?xml version='1.0' encoding='UTF-8'?>
<?compositeMetadataRepository version='1.0.0'?>
<repository name='$name' type='org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository' version='1.0.0'>
  <properties size='1'><property name='p2.timestamp' value='$TIMESTAMP'/></properties>
  <children size='1'><child location='$child'/></children>
</repository>
EOF
  cat > "$dir/compositeArtifacts.xml" << EOF
<?xml version='1.0' encoding='UTF-8'?>
<?compositeArtifactRepository version='1.0.0'?>
<repository name='$name' type='org.eclipse.equinox.internal.p2.artifact.repository.CompositeArtifactRepository' version='1.0.0'>
  <properties size='1'><property name='p2.timestamp' value='$TIMESTAMP'/></properties>
  <children size='1'><child location='$child'/></children>
</repository>
EOF
}

cd gh-pages
git config user.email "github-actions[bot]@users.noreply.github.com"
git config user.name "github-actions[bot]"

touch .nojekyll

if [ -n "${RELEASE_VERSION:-}" ]; then
  TARGET="p2/releases/$RELEASE_VERSION"
  REF_DESC="release $RELEASE_VERSION"
  rm -rf "$TARGET"
  mkdir -p "$TARGET"
  cp -r "p2/snapshots/$SNAPSHOT_SHA/." "$TARGET/"
else
  TARGET="p2/snapshots/$SNAPSHOT_SHA"
  REF_DESC="snapshot $SNAPSHOT_SHA"
  rm -rf "$TARGET"
  mkdir -p "$TARGET"
  cp -r ../ddk-repository/target/repository/. "$TARGET/"
  date +%s > "$TARGET/.created"
fi

TIMESTAMP="$(date +%s)000"
if [ -n "${RELEASE_VERSION:-}" ]; then
  HIGHEST=$(ls -1 p2/releases | grep -v latest | sort -Vr | head -1)
  write_composite p2/releases/latest "DDK p2 Latest Release" "../$HIGHEST/"
else
  write_composite p2/snapshots/latest "DDK p2 Latest Snapshot" "../$SNAPSHOT_SHA/"
fi

# Generate index.html
{
  echo '<!DOCTYPE html>'
  echo '<html><body>'
  echo '<h1>DSL DevKit p2 Repository</h1>'
  echo '<h2>Snapshots</h2>'
  echo '<ul>'
  if [ -d p2/snapshots/latest ]; then
    echo '  <li><a href="p2/snapshots/latest/">Latest Snapshot</a></li>'
  fi
  if [ -d p2/snapshots ]; then
    while IFS= read -r s; do
      echo "  <li><a href=\"p2/snapshots/$s/\">$s</a></li>"
    done < <(
      for d in $(ls -1 p2/snapshots | grep -v latest); do
        ts=0
        [ -f "p2/snapshots/$d/.created" ] && ts=$(cat "p2/snapshots/$d/.created")
        printf '%s %s\n' "$ts" "$d"
      done | sort -rn | head -20 | awk '{print $2}'
    )
  fi
  echo '</ul>'
  if [ -d p2/releases ] && [ -n "$(ls -A p2/releases 2>/dev/null)" ]; then
    echo '<h2>Releases</h2>'
    echo '<ul>'
    if [ -d p2/releases/latest ]; then
      echo '  <li><a href="p2/releases/latest/">Latest Release</a></li>'
    fi
    for v in $(ls -1 p2/releases | grep -v latest | sort -Vr); do
      echo "  <li><a href=\"p2/releases/$v/\">$v</a></li>"
    done
    echo '</ul>'
  fi
  echo '</body></html>'
} > index.html

git add -A
if git diff --cached --quiet; then
  echo "No changes to publish"
  exit 0
fi
git commit -m "Publish $REF_DESC"
for attempt in 1 2 3; do
  if git push origin HEAD:gh-pages; then
    exit 0
  fi
  echo "Push attempt $attempt failed; fetching and rebasing"
  git fetch origin gh-pages
  git rebase origin/gh-pages
done
echo "Failed to push gh-pages after 3 attempts"
exit 1
