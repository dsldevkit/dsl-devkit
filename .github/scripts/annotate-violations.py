#!/usr/bin/env python3
"""Annotate PMD, Checkstyle, and SpotBugs violations as GitHub workflow commands.

Walks `target/` reports under GITHUB_WORKSPACE, emits one `::warning` or `::error`
per violation (rendered inline on the PR's Files-changed view), and exits 1 if any
violation was found so the workflow step fails fast.
"""
import argparse
import os
import sys
from pathlib import Path
from xml.etree import ElementTree as ET


def make_emit(root: Path):
    count = 0

    def emit(level: str, file, line, title: str, msg: str | None) -> None:
        nonlocal count
        count += 1
        text = (msg or '').strip().replace('\n', ' ')[:1000]
        try:
            rel = Path(file).relative_to(root)
        except ValueError:
            rel = file
        print(f"::{level} file={rel},line={line},title={title}::{text}")

    return emit, lambda: count


def parse_pmd(root: Path, emit) -> None:
    for xml in root.rglob('target/pmd.xml'):
        for f in ET.parse(xml).getroot().findall('file'):
            for v in f.findall('violation'):
                level = 'error' if v.attrib.get('priority') == '1' else 'warning'
                emit(level, f.attrib['name'], v.attrib.get('beginline', '1'),
                     f"PMD/{v.attrib.get('rule', '')}", v.text)


def parse_checkstyle(root: Path, emit) -> None:
    for xml in root.rglob('target/checkstyle-result.xml'):
        for f in ET.parse(xml).getroot().findall('file'):
            for e in f.findall('error'):
                level = 'error' if e.attrib.get('severity') == 'error' else 'warning'
                emit(level, f.attrib['name'], e.attrib.get('line', '1'),
                     f"Checkstyle/{e.attrib.get('source', '').split('.')[-1]}",
                     e.attrib.get('message', ''))


def parse_spotbugs(root: Path, emit) -> None:
    for xml in root.rglob('target/spotbugsXml.xml'):
        # SpotBugs sourcepath is package-relative (e.g. "com/avaloq/tools/ddk/Foo.java"),
        # not repo-relative — combine with the module's source root so GitHub renders the
        # annotation inline on the file in the PR's Files-changed view.
        module_dir = xml.parent.parent
        for b in ET.parse(xml).getroot().findall('BugInstance'):
            sl = b.find('SourceLine')
            lm = b.find('LongMessage')
            if sl is None or lm is None:
                continue
            sourcepath = sl.attrib.get('sourcepath', '?')
            file_path = None
            for src_root in ('src', 'src/main/java', 'src-gen'):
                candidate = module_dir / src_root / sourcepath
                if candidate.exists():
                    file_path = candidate
                    break
            if file_path is None:
                file_path = module_dir / sourcepath
            emit('warning', file_path, sl.attrib.get('start', '1'),
                 f"SpotBugs/{b.attrib.get('type', '')}", lm.text)


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--pmd', action='store_true', help='annotate PMD violations')
    parser.add_argument('--checkstyle', action='store_true', help='annotate Checkstyle violations')
    parser.add_argument('--spotbugs', action='store_true', help='annotate SpotBugs violations')
    args = parser.parse_args()

    if not (args.pmd or args.checkstyle or args.spotbugs):
        parser.error('pick at least one of --pmd, --checkstyle, --spotbugs')

    root = Path(os.environ.get('GITHUB_WORKSPACE', '.'))
    emit, total = make_emit(root)

    if args.pmd:
        parse_pmd(root, emit)
    if args.checkstyle:
        parse_checkstyle(root, emit)
    if args.spotbugs:
        parse_spotbugs(root, emit)

    kinds = ' + '.join(k for k, on in (('PMD', args.pmd), ('Checkstyle', args.checkstyle), ('SpotBugs', args.spotbugs)) if on)
    print(f"{kinds} violations: {total()}")
    return 1 if total() > 0 else 0


if __name__ == '__main__':
    sys.exit(main())
