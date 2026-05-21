#!/usr/bin/env bash
# Removes old p2 snapshots from gh-pages, keeping the last 20.
# Required env vars: GITHUB_TOKEN

set -euo pipefail

cd gh-pages
git config user.email "github-actions[bot]@users.noreply.github.com"
git config user.name "github-actions[bot]"

if [ -d p2/snapshots ]; then
  (cd p2/snapshots && ls -1t | grep -v latest | tail -n +21 | xargs -r -I{} rm -rf "{}")
fi

git add -A
if git diff --cached --quiet; then
  echo "No snapshot cleanup needed"
  exit 0
fi
git commit -m "Clean up old p2 snapshots"
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
