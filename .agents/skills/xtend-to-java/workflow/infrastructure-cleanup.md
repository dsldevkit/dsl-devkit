# Module infrastructure cleanup

When Xtend is fully removed from a module, update these files.

## Per-module files

| File | Change |
|------|--------|
| **META-INF/MANIFEST.MF** | Remove `org.eclipse.xtend.lib` / `org.eclipse.xtext.xbase.lib` from `Require-Bundle` **only after** grepping BOTH `src` and `src-gen` for any reference — imports plus `StringConcatenation`/`CollectionLiterals`/`Conversions`/`Exceptions`/`ObjectExtensions`/`IterableExtensions`/`Procedures`/`Functions`/`Pair`. Remove **iff zero references**; keep it if any remain (e.g. `src-gen` still uses it). At zero refs it is guaranteed-safe: `Require-Bundle` isn't re-exported, and with no bytecode reference transitive availability is irrelevant. |
| **build.properties** | Remove `xtend-gen/` from `source..` entries |
| **.classpath** | Remove `<classpathentry kind="src" path="xtend-gen">` (including any nested `<attributes>`) |
| **.project** | Remove `org.eclipse.xtext.ui.shared.xtextBuilder` from `<buildSpec>` and `org.eclipse.xtext.ui.shared.xtextNature` from `<natures>` |
| **xtend-gen/** | Delete the entire directory (including its `.gitignore` marker) |

## Verify: no leftover Xtend references

```bash
grep -r "xtend" <module>/META-INF/ <module>/build.properties <module>/.classpath <module>/.project
```

## What stays put

- **`.mwe2.launch` files** (e.g. `GenerateCheck.mwe2.launch`) — drive MWE2 src-gen regeneration, which is independent of Xtend. They remain after a module is off Xtend.
- **`com.avaloq.tools.ddk.workflow/`** — the MWE2 workflow bundle. Same reason.
- **`ddk-target/ddk.target`** — keep any Xtend SDK reference until the LAST module migrates; removing earlier breaks builds for modules still on Xtend.

## If last Xtend module in the repository

Also update:
- `ddk-parent/pom.xml` — remove `xtend-maven-plugin`, `xtend.version`, xtend-gen entries in `maven-clean-plugin` and `tycho-source-plugin`
- Root `.gitignore` — remove `/*/xtend-gen/*` and `!/*/xtend-gen/.gitignore`
