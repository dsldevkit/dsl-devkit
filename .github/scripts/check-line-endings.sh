#!/usr/bin/env bash
# Verifies that no tracked files have CRLF or mixed line endings in the index.
# .bat/.cmd/.ps1 files are exempted (declared CRLF in .gitattributes).
set -euo pipefail

violations=$(git ls-files --eol \
  | grep -E "^i/(crlf|mixed)" \
  | grep -vE "\.(bat|cmd|ps1)$" || true)

if [ -n "$violations" ]; then
  echo "::error::Files with CRLF/mixed line endings stored in the index:"
  echo "$violations"
  exit 1
fi
