#!/bin/sh
# trace.sh Spec.tla cfg : compact counterexample (action + key variables per state)
cd "$(dirname "$0")"
./run.sh "$1" "$2" 2>&1 | awk '
/^State [0-9]+:|^[0-9]+: Back to state|Stuttering/ { if (line) print line; a=$0; sub(/ line.*/,"",a); line=a; next }
/^\/\\ (pc|toProcess|queue|wst|wintr|resQ|bintr|cancelReq|op|cancelled) =/ { v=$0; sub(/^\/\\ /,"",v); line=line " | " v }
/violated|No error/ { print }
END { if (line) print line }'
