#!/usr/bin/env python3
"""Condense a TLC counterexample: print each step's action and only the variables that changed."""
import re, sys
txt = sys.stdin.read()
m = re.search(r"Error: (.*?)\n", txt)
if m: print("ERROR:", m.group(1))
states = re.split(r"\nState (\d+): ", txt)
prev = {}
for i in range(1, len(states), 2):
    n, body = states[i], states[i + 1]
    body = body.split("\n\n")[0]
    head, _, rest = body.partition("\n")
    act = re.match(r"<(\w+)", head)
    act = act.group(1) if act else head.strip()
    cur = {}
    for vm in re.finditer(r"^/\\ (\w+) = (.*?)(?=^/\\ |\Z)", rest, re.S | re.M):
        cur[vm.group(1)] = " ".join(vm.group(2).split())
    diff = {k: v for k, v in cur.items() if prev.get(k) != v}
    if n == "1":
        keep = ("binary", "sources", "queue", "lpc")
        diff = {k: v for k, v in cur.items() if k in keep}
    print(f"{n:>3} {act:<11} " + "; ".join(f"{k}={v}" for k, v in sorted(diff.items())))
    prev = cur
    if "Back to state" in body or "Stuttering" in body:
        print("    ", [l for l in body.splitlines() if "Back to state" in l or "Stuttering" in l])
m = re.search(r"(\d+) states generated, (\d+) distinct states found", txt)
if m: print("states:", m.group(1), "generated,", m.group(2), "distinct")
m = re.search(r"depth of the complete state graph search is (\d+)", txt)
if m: print("depth:", m.group(1))
