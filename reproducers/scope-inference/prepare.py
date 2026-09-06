#!/usr/bin/env python3
"""Prepare the pinned public target, or use a locally built DDK p2 repository."""
import argparse
import hashlib
from pathlib import Path
import urllib.request
import zipfile

ROOT = Path(__file__).resolve().parent
URL = "https://github.com/dsldevkit/dsl-devkit/releases/download/v19.1.0/p2-update-site.zip"
SHA256 = "3f785c0690762503de1bc7d173a770531537264030e1ed23675ddf31f0b40971"
parser = argparse.ArgumentParser(description=__doc__)
parser.add_argument("--ddk-repository", type=Path, help="Use an extracted local p2 repository (for the fixed build)")
args = parser.parse_args()
if args.ddk_repository:
    repository = args.ddk_repository.resolve()
else:
    archive = ROOT / "target-platform" / "ddk-19.1.zip"
    archive.parent.mkdir(exist_ok=True)
    if not archive.exists():
        print("Downloading public DDK 19.1 release...")
        urllib.request.urlretrieve(URL, archive)
    if hashlib.sha256(archive.read_bytes()).hexdigest() != SHA256:
        raise SystemExit("DDK release checksum mismatch; remove target-platform/ddk-19.1.zip and retry")
    repository = archive.parent / "ddk-19.1"
    repository.mkdir(exist_ok=True)
    with zipfile.ZipFile(archive) as bundle:
        for member in bundle.infolist():
            if not (repository / member.filename).resolve().is_relative_to(repository.resolve()):
                raise SystemExit("Unsafe archive member")
        bundle.extractall(repository)
    if (repository / "repository").is_dir():
        repository = repository / "repository"
if not any((repository / name).exists() for name in ("content.jar", "content.xml", "content.xml.xz")):
    raise SystemExit("The supplied directory does not contain p2 metadata")
template = (ROOT / "reproducer.target.in").read_text(encoding="utf-8")
(ROOT / "reproducer.target").write_text(template.replace("@DDK_REPOSITORY@", repository.as_uri()), encoding="utf-8")
print("Prepared reproducer.target. Run mvn verify, or open it in Eclipse and select Set as Active Target Platform.")
