#!/bin/bash
set -e

# This script enables SpotBugs only for modules that have changed files

BASE_SHA="$1"

if [ -z "$BASE_SHA" ]; then
  echo "Usage: $0 <base-sha>"
  echo "Enabling SpotBugs for ALL modules (no base SHA provided)"
  exit 0
fi

# Get list of changed files
CHANGED_FILES=$(git diff --name-only "$BASE_SHA" HEAD)

# Extract unique module directories (top-level dirs with pom.xml or META-INF/MANIFEST.MF)
CHANGED_MODULES=$(echo "$CHANGED_FILES" | grep -E '^[^/]+/' | cut -d'/' -f1 | sort -u)

echo "Detecting changed modules..."
MODULE_COUNT=0

for dir in $CHANGED_MODULES; do
  # Check if this is a module directory
  if [ -f "$dir/pom.xml" ]; then
    echo "  - $dir"

    # Add spotbugs.skip=false property to this module's pom.xml
    # Check if pom.xml already has a <properties> section
    if grep -q "<properties>" "$dir/pom.xml"; then
      # Insert after opening <properties> tag
      sed -i '/<properties>/a\
    <spotbugs.skip>false</spotbugs.skip>' "$dir/pom.xml"
    else
      # Insert new <properties> section after <packaging> or </parent>
      sed -i '/<packaging>eclipse-plugin<\/packaging>/a\
  <properties>\
    <spotbugs.skip>false</spotbugs.skip>\
  </properties>' "$dir/pom.xml"
    fi

    MODULE_COUNT=$((MODULE_COUNT + 1))

  elif [ -f "$dir/META-INF/MANIFEST.MF" ]; then
    echo "  - $dir (MANIFEST only, checking for pom.xml)"
    # Some modules might only have MANIFEST.MF, check if pom exists
    if [ ! -f "$dir/pom.xml" ]; then
      echo "    Warning: $dir has no pom.xml, skipping SpotBugs"
    fi
  fi
done

if [ $MODULE_COUNT -eq 0 ]; then
  echo "No modules with pom.xml files were changed"
  echo "SpotBugs will be skipped for all modules"
else
  echo ""
  echo "Enabled SpotBugs for $MODULE_COUNT module(s)"
fi
