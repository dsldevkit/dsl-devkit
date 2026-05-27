#!/usr/bin/env bash
# Removes old p2 snapshots from gh-pages, keeping the last 20.
# Ordering uses the .created timestamp written by publish-p2-ghpages.sh;
# dirs without that file (pre-dating this change) are treated as oldest.
# Required env vars: GITHUB_TOKEN

set -euo pipefail

KEEP=20

cd gh-pages
git config user.email "github-actions[bot]@users.noreply.github.com"
git config user.name "github-actions[bot]"

if [ -d p2/snapshots ]; then
  to_delete=$(
    for d in $(ls -1 p2/snapshots | grep -v latest); do
      ts=0
      [ -f "p2/snapshots/$d/.created" ] && ts=$(cat "p2/snapshots/$d/.created")
      printf '%s %s\n' "$ts" "$d"
    done | sort -n | head -n -"$KEEP" | awk '{print $2}'
  )
  if [ -n "$to_delete" ]; then
    echo "$to_delete" | xargs -I{} rm -rf "p2/snapshots/{}"
  fi
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
