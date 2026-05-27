#!/usr/bin/env bash
# Fails the build if no Tycho Surefire test reports were produced.
# This catches broken test discovery early.
set -euo pipefail

if ! find . -path '*/target/surefire-reports/TEST-*.xml' -print -quit | grep -q .; then
  echo "::error::No surefire reports found. Test discovery is likely broken."
  exit 1
fi
