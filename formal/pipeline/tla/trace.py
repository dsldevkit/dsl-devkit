#!/usr/bin/env python3
"""Condense a TLC counterexample: one line per state with the action and the changed variables."""
import re
import sys

text = open(sys.argv[1]).read()
blocks = re.split(r"\n(?=State \d+: |\d+: Back to state)", text)
prev = {}
for b in blocks:
    m = re.match(r"State (\d+): <?([^\n>]*)", b)
    if not m:
        m2 = re.match(r"(\d+): Back to state", b)
        if m2:
            print("  -- lasso: back to state", b.split("state")[1].split(":")[0].strip())
        continue
    body = b.split("\n", 1)[1] if "\n" in b else ""
    cur = {}
    for part in re.split(r"\n(?=/\\ )", body):
        part = part.strip()
        if part.startswith("/\\ "):
            k, _, v = part[3:].partition(" = ")
            cur[k.strip()] = " ".join(v.split())
    act = m.group(2).split(" line")[0]
    changed = {k: v for k, v in cur.items() if prev.get(k) != v}
    if int(m.group(1)) == 1:
        print(f"{m.group(1):>3} Init")
    else:
        shown = []
        for k, v in changed.items():
            if k == "runs":
                # show only run slots that are non-free, compactly
                recs = re.split(r"(?=\[ ref \|->)", v)[1:]
                out = []
                for i, rec in enumerate(recs):
                    f = lambda name: (re.search(name + r' \|-> ("?[\w]+"?|<<\d+, \d+>>)', rec) or [None, "?"])[1].strip('"')
                    ref = re.search(r'ref \|-> \[kind \|-> "(\w+)", br \|-> "(\w+)", c \|-> (\d+)\]', rec)
                    rs = ref.group(2) if ref and ref.group(1) == "br" else ("c" + ref.group(3) if ref else "?")
                    if f("st") == "free":
                        continue
                    out.append(f"{i+1}:{f('wf')}/{rs}/{f('bump')} {f('st')}@{f('pc')} job={f('job')} sha={f('sha')} ver={f('ver')}{' tried' if 'tried |-> TRUE' in rec else ''}")
                v = "; ".join(out)
            if k == "queue" or k == "bud":
                continue
            if k == "gp":
                v = re.sub(r"base \|-> \[[^\]]*\]", "", v)
            shown.append(f"{k}={v}")
        print(f"{m.group(1):>3} {act}: " + " | ".join(shown))
    prev = cur
