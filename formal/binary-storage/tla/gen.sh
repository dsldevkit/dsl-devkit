#!/bin/sh
# usage: gen.sh NAME SIZE(S|M|L) NSTORE QCAP MAXTO LDEPS LFAIL LINKFAIL FIXA FIXB FIXC FIXD PLANT "INVARIANTS" "PROPERTIES"
cd "$(dirname "$0")"; mkdir -p cfg
n=$1; sz=$2
cat > cfg/$n.cfg <<CFG
CONSTANTS
  Clusters <- Clusters$sz
  Deps <- Deps$sz
  NStore = $3
  QCap = $4
  MaxTimeouts = $5
  LoaderLoadsDeps = $6
  AllowLoadFail = $7
  AllowLinkFail = $8
  FixA = $9
  FixB = ${10}
  FixC = ${11}
  FixD = ${12}
  PlantBug = ${13}
SPECIFICATION Spec
INVARIANT TypeOK
CFG
for i in ${14}; do echo "INVARIANT $i" >> cfg/$n.cfg; done
for p in ${15}; do echo "PROPERTY $p" >> cfg/$n.cfg; done
echo "CHECK_DEADLOCK FALSE" >> cfg/$n.cfg
