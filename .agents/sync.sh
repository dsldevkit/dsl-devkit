#!/bin/sh
# Mirror .agents/skills/ -> .claude/skills/ for Claude Code auto-discovery.
# macOS / Linux: single symlink. Windows (Git Bash): recursive copy.
# Idempotent. Re-run after any pull that changes .agents/skills/.

set -eu

# Anchor at repo root regardless of cwd, so `./sync.sh` from .agents/
# and `./.agents/sync.sh` from root both behave identically.
cd "$(dirname "$0")/.."

SKILL_SRC=".agents/skills"
SKILL_DST=".claude/skills"

mkdir -p "$(dirname "$SKILL_DST")"

case "$(uname -s)" in
  Darwin|Linux)
    if [ -L "$SKILL_DST" ]; then
      if [ "$(readlink "$SKILL_DST")" = "../$SKILL_SRC" ]; then
        echo "skills: symlink already correct, no-op"
        exit 0
      fi
      rm "$SKILL_DST"
    elif [ -e "$SKILL_DST" ]; then
      echo "skills: $SKILL_DST exists and is not the expected symlink; refusing to overwrite" >&2
      exit 1
    fi
    ln -s "../$SKILL_SRC" "$SKILL_DST"
    echo "skills: symlinked $SKILL_DST -> ../$SKILL_SRC"
    ;;
  MINGW*|CYGWIN*|MSYS*)
    # Windows: recursive copy (Git symlinks unreliable without Developer Mode).
    rm -rf "$SKILL_DST"
    mkdir -p "$SKILL_DST"
    cp -R "$SKILL_SRC"/. "$SKILL_DST"/
    echo "skills: copied $SKILL_SRC -> $SKILL_DST"
    ;;
  *)
    echo "skills: unsupported OS $(uname -s)" >&2
    exit 1
    ;;
esac
