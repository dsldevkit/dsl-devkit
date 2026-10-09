"""Condense a TLC counterexample: one line per step with the action and the variables that changed."""
import re, sys
text = open(sys.argv[1]).read()
states = re.split(r'\n(?=State \d+: )', text)
prev = {}
for st in states:
    m = re.match(r'State (\d+): <(\w+)', st)
    if not m:
        continue
    st = st.split("\n\n")[0]
    cur = {}
    for vm in re.finditer(r'^/\\ (\w+) = (.*?)(?=^/\\ |\Z)', st, re.S | re.M):
        cur[vm.group(1)] = ' '.join(vm.group(2).split())
    diff = {k: v for k, v in cur.items() if prev.get(k) != v}
    if m.group(1) == '1':
        diff = {k: cur[k] for k in ('pcS', 'pcU', 'view', 'provReg')}
    print(f"{m.group(1):>3} {m.group(2):<16} " + '  '.join(f"{k}={v}" for k, v in sorted(diff.items())))
    prev = cur
