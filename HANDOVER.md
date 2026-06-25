# Handover — Xtend→Java migration campaign + open-PR shepherding

*Generated: 2026-06-15*
*Branch: master (synced to upstream/master `cf1c4cb4b`)*
*Living document — keep referring to and updating it; NOT read-and-delete.*
*Live per-module pipeline status: see `MIGRATION-PIPELINE.md` (companion tracker, also untracked, never commit).*

## Mission

Two parallel tracks:
1. **Xtend→Java migration** — peel the complete migration on PR #1274 into small, independently-reviewable per-module PRs, easiest→hardest, using a four-eyes regen+diff method.
2. **Shepherd the remaining open draft PRs** to merge, one at a time, joint review before toggling ready.

## Standing process rules (hard constraints)

- **One task `in_progress` at a time.** Pause + report when each completes; wait for João's go before the next. He sequences.
- **Explain before side-effects** — full plan (commands, targets, order) before any state-changing/visible action.
- **Draft PRs only.** Never `gh pr ready` autonomously. Toggle ready only on joint satisfaction.
- **Rebase-merge only:** `gh pr merge <n> --repo dsldevkit/dsl-devkit --rebase --delete-branch`. Never squash/merge-commit.
- **Review before merge** — read the diff, surface a counter-argument, then await go.
- **Amend on own active PR branches** (force-with-lease); don't add fixup commits.
- **Comments to Ruben** need an attribution line AND explicit per-comment approval. Never post autonomously.
- **Local builds use `-T 3C`** (CI uses `-T 2C`).
- **Branch from `upstream/master` explicitly** — master moves under us (concurrent agents share `.git`). `upstream` = canonical (dsldevkit/dsl-devkit); `origin` = João's fork (joaodinissf).
- Narrate worktree/branch switches; confirm work is safe.
- **Skill / process improvements ship in their OWN dedicated PRs** (separate from migration PRs) — persist them as we go. (e.g. PR #1415, skill ground-truth process.)

## Migration method (four-eyes, per module)

**THE SKILL:** in-repo at `.agents/skills/xtend-to-java/` (doc-driven; read+follow, NOT a Skill-tool entry). Start at `SKILL.md` → `workflow/overview.md` (Steps 0–7) → `workflow/one-file-conversion.md` → `rules/00-09` → `workflow/validation-checklist.md`. Hard rule: read BOTH the `.xtend` source AND a behavioral ground truth IN FULL before writing any Java.

**Ground truth = a FRESH build, not #1274.** For each module, build off `upstream/master` with `-T 3C` to (re)generate `xtend-gen/` — that is the authoritative, current, Java-authoritative ground truth (and the first build gate). **PR #1274** `origin/feature/xtend-to-java-migration` (complete migration, 0 `.xtend`) is the **four-eyes CROSS-CHECK** — read it and reconcile every divergence, but it may be STALE vs current master and is NEVER a substitute for fresh `xtend-gen/`. (DO NOT merge #1274 directly.) Roadmap with full per-file checklist lives on that branch: `XTEND_MIGRATION_ROADMAP.md`.

Per-module loop:
1. `git fetch upstream` → worktree/branch `migrate/xtend-to-java/<module>` from `upstream/master`.
2. **Build for ground truth:** `mvn -f ./ddk-parent/pom.xml -pl :<module> -am -DskipTests -T 3C compile --batch-mode` → read the fresh `xtend-gen/` Java (authoritative) IN FULL, and the `.xtend` source IN FULL. If the class extends a generated supertype, read it under `src-gen/`.
3. **Regenerate** the Java from scratch via the skill — idiomatic, matching `xtend-gen/` behavior exactly.
4. **Four-eyes cross-check:** diff regen vs #1274's `.java`; reconcile every divergence (either side may be wrong — #1274 may be stale OR skill-non-compliant, e.g. `String.format`).
5. Vet vs validation-checklist: no `val`/`var`; idiomatic strings (literal → text block → `.formatted()` → `StringBuilder` only in control flow; NEVER `String.format`); preserved stack traces; parameterized Log4j2 (`{}`); try-with-resources; no unnecessary boxing; `@Override` everywhere; no wildcard imports; behavior preserved on non-mechanical transforms.
6. Full CI-equivalent build: `xvfb-run mvn clean verify checkstyle:check pmd:check pmd:cpd-check spotbugs:check -f ./ddk-parent/pom.xml --batch-mode -T 3C`

### Per-PR process (João's standing instruction — follow methodically EVERY slice)
1. Open PR **as draft, NO reviewers**.
2. `gh pr view <n> --web` to open it in the browser.
3. **PAUSE — wait for João's explicit approval.** Never `gh pr ready` autonomously.
4. On his go: `gh pr ready <n>` + assign reviewer.
5. Move to the next slice. One slice in_progress at a time; pause+report between.

`#1277` is UNRELATED (migrates `org.eclipse.xtend` runtime *type-system dependency*, not file conversion). Own track.

## Phase 1 — migration sequence (easy → hard). 71 `.xtend` / 16 modules remain.

Task list is now **per-module, 1:1 task↔PR**, labelled `P0-NN` where **NN = Seq #**. Each module = its own independent PR.

| Seq | Module | Files | Risk | Task | Status |
|----|--------|-------|------|------|--------|
| 1 | `check.ui.test` | 1 | Low — **PILOT** | #26 (P0-01) | **#1416 READY + APPROVED + green — MERGE-READY** |
| 2 | `xtext.ui.test` | 1 | Low | #41 (P0-02) | **#1417 READY + APPROVED + green — MERGE-READY** |
| 3 | `xtext.generator.test` | 1 | Low | #42 (P0-03) | **#1418 READY + APPROVED + green — MERGE-READY** |
| 4 | `check.test.runtime` | 1 | Low | #32 (P0-04) | **#1419 DRAFT + green — awaiting flip** |
| 5 | `xtext.format.test` | 1 | Low | #27 (P0-05) | **#1420 DRAFT + green — awaiting flip** |
| 6 | `xtext.format.generator` | 1 | Low | #43 (P0-06) | **#1421 DRAFT + green + APPROVED — awaiting flip** |
| 7 | `xtext.export.generator` | 1 | Low | #44 (P0-07) | **#1422 DRAFT + green + APPROVED — awaiting flip** |
| 8 | `xtext.test.core` | 1 | Med | #31 (P0-08) | **#1423 DRAFT — BLOCKED** (active annotation; DDK CI can't validate) |
| 9 | `xtext.check.generator` | 2 | Med | #45 (P0-09) | Not started |
| 10 | `xtext.scope` | 4 | Low | #28 (P0-10) | Not started |
| 11 | `xtext.expression` | 5 | Low | #29 (P0-11) | Not started |
| 12 | `xtext.format` | 6 | Low | #46 (P0-12) | Not started |
| 13 | `xtext.export` | 9 | Med | #30 (P0-13) | Not started |
| 14 | `check.core` | 8 | **HIGH** (prod Check framework — line-by-line diff) | #33 (P0-14) | Not started |
| 15 | `check.core.test` | 11 | Low (test) | #34 (P0-15) | Not started |
| 16 | `xtext.generator` — parser group | ~8 | Med | #35 (P0-16) | Not started |
| 17 | `xtext.generator` — builder group | ~10 | Med | #36 (P0-17) | Not started |
| 18 | Cleanup — drop Xtend infra (POMs/MANIFESTs/feature.xml/.classpath/.project/xtend-gen) | 0 | Low | #37 (P0-18) | Not started |

Already merged (gone from upstream/master): `helloworld.ui.test`, `format.ide`, `format.ui`, `scope.generator`, `checkcfg.core`, `checkcfg.core.test`, `check.test.runtime.tests`, `xtext.ui`.

Generator split (seq 16/17) is approximate — confirm exact parser-vs-builder cut against #1274 when reached. Seq 8 (`xtext.test.core`) — only 1 of roadmap's 2 files remains; verify which.

## Phase 2 — existing open PRs

| Action | PR | Status | Trigger |
|--------|-----|--------|---------|
| Merge | #1399 cache restore-keys | ready, APPROVED pending | Ruben approves (monitor active) |
| Merge | #1308 stale-readme removal | ready | Ruben approves (monitor active) |
| Finish review | #1376 checkcfg dup-language | draft, validation-only, green | reviewer pass → joint → toggle |
| Rework | #1396 SARIF lint redesign | draft | sweep |
| Rework | #1397 CPD threshold | draft | sweep (de-stack from #1396) |
| Revisit | #1400 spotbugs-skip | draft | reconcile w/ task #25 |
| Gated | #1382 scoping fragment2 manifest | draft | behind Ruben's #1405 |
| Gated | #1281 export generatorX guard | draft | behind Ruben's #1405 |
| Standalone | #1364 check-docs maven PoC | draft | sweep |
| External | #1405 (Ruben's) scope/export migration | draft | his; unblocks #1382/#1281 |
| Reference | #1274 complete migration | keep open | four-eyes source; close after Phase 1 |
| Separate | #1277 xtend type-system dep | draft | own decision |

## Merged today (2026-06-15)

#1412 (style tidy-ups), #1394 (robustness sweep — added LOG.warn to ScopeResourceDescriptionStrategy per Ruben), #1410 (spotbugs 4.10.2 bump — full saga w/ #1411 IAOM-exclusion + #1414 USO-fix complete).

## Monitors active

#1399 + #1308 review/CI-failure/merge watchers (persistent). They fire on review change / CI failure / merge — NOT on CI-success-only.

## Stale worktrees (prunable — branches merged)

`/tmp/wt-sb410` (#1414), `/Users/joao/Git/Avaloq/dsl-devkit-fixes` (#1394), and the migrate/xtend-to-java/{format-ide,format-ui,scope-generator,xtext-ui} worktrees (their plugins already merged). `git worktree remove` when convenient.

## Other backlog tasks

#10 empty `{@inheritDoc}` sweep (P3); #15 KNOWN-ISSUES → tracker issues incl. language-scoped catalog/param uniqueness follow-up from #1376 (P3); #25 SpotBugs CI speedup — exclude generated code + pin effort=default (P3); #20 #1399 restore-keys post-merge check (P4); #17 src-gen Xtext 2.43 sync (P4 DO-LAST).

## End-of-campaign tasks (strict order, after ALL file conversion)

These are SEPARATE from the `.xtend`→`.java` file conversion. Keep the three concepts distinct: **Xtend1** (classic Xtend/Xpand, pre-Xbase) ≠ **Xbase** (modern Xtext expression framework) ≠ **`.xtend`→`.java` file conversion** (#1274, what Phase 1 does).

- **#38 [FINAL] — Sort out the Xbase / Xtend1-retirement branches.** Two separate legacy tracks to reconcile/decide (revive vs abandon):
  1. Xbase: branch `claude/migrate-xtend-to-xbase-4OWuz` (local + origin); plan `~/.claude/plans/composed-yawning-lighthouse.md`.
  2. Xtend1 expression-engine retirement: branch `feat/retire-xtend1-step-1`; spec `~/.claude/jobs/0c9fde26/xtend1-migration-spec.md`; plan `~/.claude/plans/can-you-do-a-cheerful-matsumoto.md`. Drops `org.eclipse.xtend.typesystem.Type` + `.ext` → hand-written `DdkType`/`DdkScope`. = the `#1277` track.
- **#39 [VERY LAST] — Clear out all stale branches (local + remote).** Full branch+worktree hygiene sweep after #38 decides the legacy branches' fate. Leave only live/protected refs.

## RESUME SNAPSHOT — 2026-06-17 (morning)

**8 PRs MERGED** (#1415 skill + 7 migrations #1416–#1422 = P0-01→07). master @ `40034ebb4`. **5 migration drafts open, all CI-green.**

| PR | P0 | State | Review | CI | Notes |
|----|----|-------|--------|----|-------|
| #1415–#1422 | skill + 01–07 | **MERGED** ✅ | APPROVED | — | done |
| **#1426** | 09 check.generator | OPEN (ready) | CHANGES_REQUESTED→addressed | 4/4 ✅ | Ruben wanted text blocks; converted (proven byte-identical), amended `d66599eee` + force-pushed → re-review |
| **#1427** | 15 check.core.test | OPEN draft | REVIEW_REQUIRED | 4/4 ✅ | xbase.lib-removal blocker did NOT materialize (CI green). +nits |
| **#1428** | 12 format | OPEN draft | REVIEW_REQUIRED | 4/4 ✅ | 766-line inferrer; 1 nit |
| **#1429** | 16 generator-parser | OPEN draft | REVIEW_REQUIRED | 4/4 ✅ | SHIP; split — infra deferred |
| **#1430** | 17 generator-builder | OPEN draft | REVIEW_REQUIRED | green | SHIP; split — infra deferred; rebase before merge |
| **#1423** | 08 Tag | OPEN draft | APPROVED | 4/4 ✅ | **BLOCKED** — gated on asmd's 6 non-test `@Tag` holders. DO NOT merge. |

**Open threads:** (1) #1426 awaiting Ruben re-review after the text-block fix. (2) Drafts #1427/#1428/#1429/#1430 awaiting Ruben review (no reviewers assigned yet — flip/assign on João's go). (3) **Text-block sweep** (task #24): Ruben+João both want StringConcatenation→text-block where clean; #1426 done, rest pending. (4) Proposed but NOT dispatched: a **diff-vs-#1274 workflow** (four-eye audit of every split migration against the reference complete-migration). (5) Deferred behind Ruben's #1405: P0-10 scope, P0-11 expression, P0-13 export. (6) Held: P0-14 check.core (HIGH RISK). (7) End: P0-08 Tag, P0-18 cleanup.

**Live worktrees:** dsl-devkit-{xtext-format, check-core-test, xtext-generator-parser, xtext-generator-builder, xtext-check-generator} (+ older stale ones for end-of-campaign sweep).

**Equivalence-proof method (for text-block conversions):** compile a tiny comparator (`/tmp/CmpQf.java` pattern) linking the real `org.eclipse.xtext.xbase.lib` classes jar (under `~/.m2/.../p2/osgi/bundle/`), build both the StringConcatenation sequence and the candidate text block, `assertEquals`. For test files, running the test is itself the proof.

## RESUME SNAPSHOT — 2026-06-21 (flaky-test fix + JUnit 4 purge sub-thread)

Side-thread off **#1438** (dependabot checkstyle 13.6 bump, kept failing CI). Diagnosed a long-standing flaky SWTBot test and purged remaining JUnit 4 traces. Two of my draft PRs, both CI-green, awaiting flip:

| PR | What | State | CI |
|----|------|-------|----|
| **#1440** | `fix(test)` de-flake `bulkApplyQuickfix` + remove dead `@Retry` | draft; row-count gate; ponytail-trimmed; full multi-agent review clean (1 nit fixed, 3 refuted) | green 4/4 |
| **#1441** | `chore` purge orphaned JUnit 4 declarations | draft; 5 commits (manifests, .classpath, devkit-run.launch sweep, dead PMD suppressions, stale comments); ultracode audit = **PASS** (no real JUnit 4 left repo-wide) | green 4/4 |
| **#1442** | `build(checkstyle)` require Javadoc on public/protected **methods** + document them | draft; `MissingJavadocMethod scope=protected tokens=METHOD_DEF` (**constructors excluded** per João); ~154 method docs across 64 files (144 boilerplate ctor docs stripped, 0 deletions vs master); checkstyle 0/65 green; 6 area-grouped commits | re-running |

- **Flake root cause (task #26):** the Quick Fix dialog repopulates its Problems table async; the tick loop ran with no wait → 0 rows ticked → Finish a no-op → `expected:<0> but was:<4>`. Fixed by gating on `matchingRowCount == markers.length` (a *Set* of locations collapses — the 2 test files share line numbers). `@Retry` was a dead no-op since its JUnit 4 `ClassRunner` consumer was deleted (`d920b3bad`). The earlier 60s-timeout-bump attempt was **disproven** (still failed → nothing was applied, not slow refresh).
- **#1438 (task #28):** leave red; comment `@dependabot rebase` once #1440 merges → it picks up the de-flake → green.

**Deferred follow-ups (own branches; harness tasks #29 / #30):**
- **#29 [JAVADOC]:** convert `&#064;`/HTML entities in Javadoc `<pre>` examples → `<pre>{@code …}</pre>` (readable source; never naively swap `&#064;`→`@` outside `{@code}`). 2 files w/ `&#064;` + 4 w/ `&lt;/&gt;`. Own branch, deferred.
- **#30 [CHECKSTYLE]: DONE → PR #1442.** Backlog measured (~350 public/protected; the "598" was an inflated parallel-log count), documented via a 29-agent ultracode workflow, then **constructors excluded** (`tokens=METHOD_DEF`) and the 144 boilerplate ctor docs stripped → ~154 method docs. Reactor checkstyle 0/65 green. Draft, awaiting review/flip.

New worktrees: `dsl-devkit-flaky-quickfix` (#1440), `dsl-devkit-junit4-purge` (#1441), `dsl-devkit-checkstyle-javadoc` (#30).

## RESUME SNAPSHOT — 2026-06-22 (flaky PRs MERGED + Sonnet×Opus faithfulness experiment)

**Flaky-test campaign COMPLETE — 3 merges since last snapshot:**
| PR | Now | Note |
|----|-----|------|
| **#1440** de-flake + drop `@Retry` | ✅ **MERGED** | shipped Option B: single retrying `checkMatchingRows` waited op; catch broadened to `IllegalArgumentException\|IndexOutOfBoundsException`; `!item.isChecked()` event-idempotency guard (verified `check()`=`setChecked(true)` in SWTBot bytecode). Tasks #26 ✅ |
| **#1441** JUnit4 purge | ✅ **MERGED** | task #27 ✅ |
| **#1438** checkstyle 13.6 bump | ✅ **MERGED** | went green once #1440 landed; task #28 moot ✅ |
| **#1442** Javadoc presence | OPEN/**draft**, tip `1b2bc5f64` on fork | FINAL design: `scope=protected`, `tokens=METHOD_DEF` (ctors excluded), `allowMissingPropertyJavadoc=true` (trivial getters/setters excluded), `@Override`/`@Inject` exempt. 51 method docs / 21 files, **0 deletions**, reactor green. Rewrote 7 boilerplate docs; stripped `setUserData` (lone missed trivial accessor). PR body updated. Task #30 — NOT flipped/merged. |

**Sonnet×Opus migration-faithfulness experiment (DONE).** 8 workflows = 4 branches (#1426–1429) × 2 models, one model-pinned agent per file, comparing migrated `.java` vs original `.xtend` (ground truth = committed `xtend-gen`, found at `eclipse/ddk-master/git/dsl-devkit/**/xtend-gen/**`). All findings adjudicated against `xtend-gen`:
- **ONE material finding:** **#1428 `FormatJvmModelInferrer._getDirectiveName(GroupBlock)`** — migration **silently fixed a latent Xtend bug**. Xtend `newArrayList(Iterables.filter(...))` hit the `CollectionLiterals.newArrayList(T...)` varargs trap → `ArrayList<Iterable<GroupBlock>>` → `indexOf` always −1 → **always `"Group0"`** (verified `xtend-gen` L922). Migrated builds real `List<GroupBlock>` → `"Group1/2/…"`; feeds generated formatter class/method names. Caught by BOTH models. **Decision pending:** reproduce `Group0` (strict) vs keep repair + document + check downstream.
- **Everything else:** benign idiomatic deviations (UTF-8 `getBytes`, dropped no-op `close()`, `orElseThrow`-vs-NPE), 2 trivial trailing-newline diffs (`generateSrc`, `IssueCodeValueTest`), and a pile of **whitespace FALSE POSITIVES**.
- **Scorecard:** Opus 1 real / **2 FP** (CheckModelUtil, #1429 Javadoc); Sonnet 1 real / **~10 FP** (all whitespace). **Model agreement ≠ correctness** — both wrong on `CheckModelUtil.modelWithSeverityRange` (verified byte-identical 0/4/4). `xtend-gen` required for every call.
- **Cost (real tokens):** Sonnet **$35.41** ($1.42/file) vs Opus **$117.99** ($4.72/file, ~3.3×). Sonnet churns on whitespace files (#1429-sonnet: $21.37, 285k out, 369 tool calls, 50 min) — advantage erodes where it's least accurate.
- **STAGED, NOT POSTED:** the #1428 `getDirectiveName` PR comment (inline, lines 623-628, commit_id `a0e4275e989fa26415ea4e0476383cc6cdcccb5e`, attribution line included). Full `gh api` command + body in conversation. **Needs explicit per-comment approval.**

**Experiment infra gotchas (reusable):** (1) `args` is NOT delivered to workflow scripts here → **bake `const CFG={...}` into the script**. (2) 8-at-once → server-side rate limit → use ≤2-in-flight throttle + sequential retry + `incomplete:[]` reporting. (3) compute file lists in main thread (agent discovery truncated to 10). Scratch scripts under `.claude/wf-*.js` + `.claude/wf-filelists.json` (untracked; clean up later).

**Migration PR review status:** #1426–1429 = Ruben's CHANGES_REQUESTED are STALE (his text-block/Iterables/`{@inheritDoc}` feedback already applied & pushed) → user to request re-review. **#1430** = genuinely unaddressed (static `StringConcatenation`→text-block across ~7 Fragment2 generators; his review is on current HEAD) — deferred sweep (tasks #24/#25).

**Xtend whitespace rule (settled empirically):** Xtend strips the **min content indentation** (NOT the closing-`'''` delimiter indent); Java text blocks strip min content indent too, so a correctly-placed closing `"""` matches. Migration does this right everywhere; the only real whitespace divergence is a trailing newline when `"""` sits on its own line (`}\n` vs `}`).

## RESUME SNAPSHOT — 2026-06-23 (append-run → text-block coalescing; verification harness)

**PR state:** **#1426 MERGED 06-23** (xtend↔java reviewed by João = ✅ OK). #1427/#1428/#1429/#1430 still OPEN. Older batch #1416–1422 merged 06-16/17.

**#1428 — CI fixed.** Audit-driven extra cleanup landed: `542eb5710` (7× single-line `StringConcatenation`→`.formatted`) + `e5c9dea0c`→ rebased to `00e2743f6` (`FormatGenerator` exception `.formatted`). The middle commit `2ec9f181f` (`_elementAccess`/`_locator` `StringConcatenation`→`java.lang.StringBuilder`) **broke PMD** (`InsufficientStringBufferDeclaration` + `AppendCharacterWithChar`) → **DROPPED** via `git rebase --onto 542eb5710 2ec9f181f`, force-pushed, **CI green**. Ruben's 8 #1428 threads: **all addressed** (6 applied in original migration + `generateJavaDoc` `:412` deliberately kept — loop + two-arg indented append).

**#1429 — `kwBuilder` `.formatted` pushed** (`4df925f4d`), CI green. **Ruben added NEW comments 06-23**: `:567` (`ruleImpl`), `:175` (parser ctor), more on `:90` — all the **dynamic ANTLR generators** (the risky ones).

**KEY DECISION — avoid risky translations.** Removing `StringConcatenation` from the big dynamic ANTLR generators (#1429 `AnnotationAwareAntlrGrammarGenerator` 1099 ln / `…ContentAssist…` 1302 ln; 86/90 `newLineIfNotEmpty`, 43/66 two-arg appends) is **too risky / low value** (Ruben himself: "a builder is justified") → **NOT doing it**. Dedicated stacked branch idea scrapped; scratch worktree `dsl-devkit-antlr-strconcat` removed.

**CURRENT INITIATIVE — coalesce consecutive append-runs → text blocks**, as a **new dedicated commit per OPEN branch** (#1427–1430; NOT amend; fast-forward push). **Scope = TARGETED** (João's pick): Ruben's flagged methods + big pure-static/single-arg runs; skip heavily-fragmented small runs.

**Verification (load-bearing): executable byte-equality harness** `.claude/coalesce-verify/Probe.java` — lifts the OLD append run verbatim onto real `new StringConcatenation("\n")` vs the candidate text block `.formatted(...)`, asserts byte-equality over a battery {empty, multi-line, spaces, quotes, `%`, unicode}. Compile/run: `javac -cp <xbaseJar> -d . Probe.java && java -cp <xbaseJar>:. Probe`. Jar = `~/.m2/repository/org/eclipse/xtext/org.eclipse.xtext.xbase.lib/2.43.0/org.eclipse.xtext.xbase.lib-2.43.0.jar`; Java 26.
- **Empirical rules (executed, not assumed):** single-arg `append(v)` does NOT re-indent multi-line → safe `%s`. Two-arg `append(v,indent)` DOES re-indent multi-line → **exclude/split**. `newLine()`/`newLineIfNotEmpty()`→`\n`; `newLineIfNotEmpty` safe after a line with literal content, **UNSAFE after a bare value** (empty→divergence). Import-managed `append(TypeReference/Class)` → keep as separate append (split text block around it).
- **CRITICAL:** `mvn verify`/CI do **NOT** regenerate generated artifacts (no `xtext-maven-plugin`/`mwe2` in poms; IDE-only) and there's **no CI freshness guard** → **CI cannot catch generator-output drift**. The harness is the ONLY real output gate. Committed `src-gen` is LF (line-ending check enforces) = matches `\n` modeling.

**DONE so far:** **#1430 `LspBuilderIntegrationFragment2`** fully coalesced — 4 blocks (generateBuildService prologue+epilogue, generateBuildSetup imports + injector bodies), **all proven `IDENTICAL`**, **−70 net lines**, compiles (EXIT=0). **NOT committed yet** (more #1430 files under targeted scope before the single #1430 commit).

**Review tracker (João confirms each xtend↔java):** #1426 ✅ OK. Pending: #1427–1430 (review AFTER coalescing lands) then older #1416–1422. **Sequence (João's order): update open PRs → review open ones → then older merged ones.**

**Held comments (drafted, NOT posted — need per-comment approval + attribution; link to the coalescing commits once landed):** #1429 reply (static parts already text blocks; dynamic builders justified, kept), #1430 `LspBuilder` reply (API-bound wrapper, body now text blocks), #1428 `:412` `generateJavaDoc` note, and the staged **#1428 `getDirectiveName`** behavioural comment.

**Memories added this session:** `feedback_generated_output_lf` (emit `\n` unconditionally; line-separator divergence not a blocker), `feedback_local_validation_full_checks` (pre-push must run `pmd:check`/`checkstyle:check`/`spotbugs:check`, not just `package`).

**Scratch (untracked, clean up later):** `.claude/x2j-review.sh` (opens each PR's original `.xtend` vs new `.java` in `code --diff`; usage `bash .claude/x2j-review.sh <pr>`), `.claude/x2j-review/<pr>/` (extracted originals), `.claude/coalesce-verify/Probe.java` (+ `.class`), earlier `.claude/wf-*.js` + `wf-filelists.json`.

## RESUME SNAPSHOT — 2026-06-23 (late): APPROVALS IN → merge approved + coalescing-as-follow-ups

**Ruben re-reviewed.** **#1427 APPROVED, #1428 APPROVED, #1430 APPROVED** (all `CLEAN`/`MERGEABLE`, CI 4/4 green). **#1429 = CHANGES_REQUESTED / BLOCKED** — his `:90/:112/:150/:175/:567` ANTLR multi-line-string comments; the only holdout.

**Coalescing committed (harness-proven IDENTICAL, CI-gate green):**
- **#1430** (APPROVED, includes these): `e832f558b` (LspBuilder+StandaloneBuilder+FormatterFragment2) + `5f6feaa13` (LanguageConstants). Approval survived the pushes.
- **#1429**: `cd1d420bf` (AnnotationAwareAntlrGrammarGenerator). **ContentAssist batch-1** (imports + setters/getGrammar block) **built-green, UNCOMMITTED** in worktree `dsl-devkit-xtext-generator-parser`.
- #1427/#1428 nothing to coalesce; #1426 merged.

**Refined rule (proven):** pure-static→text block; single-arg value→`%s`; two-arg `append(v,indent)` with provably single-line value→`%s` (no-op re-indent, verify single-line battery); EXCLUDE import-managed `append(Class/TypeReference)`, multi-line values, `appendImmediate`, loop bodies. **Remainder verdict:** leftover ContentAssist/Fragment2 runs are tiny fragments inside dynamic loops/typeRef appends → low/negative value → **skip (churn).**

**THE PLAN (await João's go — merging is irreversible):**
- **Phase A — merge approved:** rebase-merge #1427 → #1428 → #1430 (`gh pr merge <n> --repo dsldevkit/dsl-devkit --rebase --delete-branch`), re-check `CLEAN` before each. #1430 brings its 2 approved coalescing commits. *Caveat (review-before-merge): João has NOT done his own xtend↔java review of #1427/#1428/#1430 (only #1426); merging trusts Ruben+CI — `bash .claude/x2j-review.sh <pr>` to review first.*
- **Phase B — #1429:** commit ContentAssist batch-1, post the held reply (static→text-blocks done; dynamic ANTLR generators kept as justified builders), request re-review → merge on approval.
- **Phase C — follow-ups:** skip marginal coalescing; genuine follow-ups = #29 Javadoc `{@code}` + staged #1428 `getDirectiveName` comment. Scratch cleanup: `.claude/coalesce-verify/`, `.claude/x2j-review/`, `.claude/wf-*.js`.

**Harnesses:** `.claude/coalesce-verify/{Probe,SB,FF,AAGG1,AAGG2,LC,CA1}.java`; xbase jar `~/.m2/.../org.eclipse.xtext.xbase.lib/2.43.0/...jar`, Java 26.

## RESUME SNAPSHOT — 2026-06-23 (latest): Phase 0 review DONE → A0 bugfix split-out → ordered merge

**Phase 0 ultracode pre-merge review (workflow `w8mi77f4s`) DONE.** 29 files, 8 findings, **2 confirmed / 6 refuted** (whitespace/style FPs). **All 3 approved PRs CLEAR — 0 blockers.** Both confirmed are minor + in *original migration code* (my coalescing = 0 drift):
- #1428 `List.of(...)` in ~12 dispatcher fall-through throws (`FormatJvmModelInferrer:1434…`): NPE-on-null vs xtend-gen `Arrays.asList`→IAE; unreachable `infer(null,…)` path → optional follow-up.
- #1427 `modelWithContexts` trailing `"\n    "`: test-helper, CI-green/harmless, whitespace-FP class → optional follow-up.

**NEW DECISION (João): split the `getDirectiveName` repair into its own PR.** The migration silently fixed a latent Xtend bug (`newArrayList(filter(...))` varargs trap → `ArrayList<Iterable<GroupBlock>>` → `indexOf` always −1 → every group `"Group0"`). Fix it **in the `.xtend` on a dedicated branch off `upstream/master`** so the migration stays a faithful 1:1. **This is now Phase A0, merged FIRST.** Fix = `FormatJvmModelInferrer.xtend:475` → `grammarRule.directives.filter(GroupBlock).toList` (→ `Group1/2/3`); changes generated formatter output (`Group0`→`GroupN`); open PR fork→upstream `master`, **reviewer = rubenporras**. Supersedes the old staged discussion comment.

**Revised merge order: A0 → #1428 → #1427/#1430.** After A0 merges, **rebase #1428** onto bugfixed master (modify/delete on `FormatJvmModelInferrer.xtend` → keep #1428's deletion; force-push; may need Ruben re-approval) — its `.java` already emits `Group1/2/3` so it's faithful to the fixed `.xtend`. Then rebase-merge #1428/#1427/#1430 (`--rebase --delete-branch`, re-check CLEAN each).

**A0 DONE (2026-06-23):** worktree `/Users/joao/Git/Avaloq/dsl-devkit-format-groupfix` (branch `fix/format-directive-group-numbering` off `upstream/master` @ `088854035`). Fix = 2 lines in `FormatJvmModelInferrer.xtend` (`.filter(GroupBlock).toList` + drop orphaned `Iterables` import). Verified: `xtend-gen` untracked + `git grep Group0` = 0 → no committed artifact affected (blast radius nil-to-cosmetic in-repo; latent bug). Full CI-gate build GREEN. Commit `c9d56d8ec`, pushed to fork. **PR [#1443](https://github.com/dsldevkit/dsl-devkit/pull/1443)** opened: draft ✓, `joaodinissf:fix/format-directive-group-numbering → dsldevkit:master`, reviewer **rubenporras** ✓. **Confirmed #1428's migrated `_getDirectiveName(GroupBlock)` already emits `Group1/2/3`** (via `Iterables.addAll(new ArrayList, filter)`) → faithful once A0 lands.

## RESUME SNAPSHOT — 2026-06-25: A0+5 PRs MERGED → correctness-first faithfulness sweep running

**📒 VERIFICATION LEDGER:** `.claude/migration-verification.md` — per-file byte-vet status (HARNESS / APPEND-DIFF / FAITHFULNESS / SWEEP) for every migrated file. Keep it updated as files are vetted. Bottom line: **every byte-output generator is byte-vetted**; logic/test files are faithfulness-vetted (byte-vet N/A); confirmed drifts all fixed (#1443/#1446) or benign-deferred.

**#1446** (faithfulness follow-ups, draft, Ruben requested): 2 commits — `AnnotationAware` indent fix + faithful `FormatJvmModelInferrer` dispatcher throws. Ruben CHANGES_REQUESTED w/ 3 suggestions (empty-arg throws → `"Unhandled parameter types"`) → **applied + force-pushed (`e78494ffb`)**, build green. Pending: re-request Ruben / resolve 3 suggestion threads (await João).

**#1429 DECISION (João):** keep `StringConcatenation` (do NOT remove per Ruben's `:90` — removal risks byte-drift), but **coalesce static append-runs into text blocks where byte-safe** (harness-gated). Sequence pending approval.

**MERGED this campaign:** A0 `#1443` (getDirectiveName fix in `.xtend`), `#1428` (format), `#1427` (check-core-test), `#1444` (Ruben's CheckGenModelUtil log fix), `#1430` (generator-builder). master @ `839bfdde5`. **`#1429` (parser) is the only open migration PR** (CHANGES_REQUESTED).

**Merge mechanics learned:** repo **dismisses approvals on push** (force-push re-opens review). `gh pr merge <n> --repo dsldevkit/dsl-devkit --rebase --delete-branch`. Rebase locally onto `upstream/master` (git cherry-pick-detection drops duplicate stacked commits, e.g. A0's commit dropped from #1428).

**Per-pair review done (read-only `OK`/`not OK`):** #1427 (11 pairs → 1 nit: `CheckModelUtil.modelWithContexts` trailing `"\n    "`, harmless, disabled-caller). #1430 (12 pairs → `ResourceFactoryFragment2` verified clean; **`AnnotationAwareContentAssistFragment2` had a REAL byte-drift bug** — multi-line `Alternatives` leaf continuation-lines lost indentation: migrated single-arg `append` vs xtend-gen two-arg `append(v,"  ")`). **Fix done + harness-proven (AACA.java 7/10→10/10) + build-green; saved as `scratchpad/aaca-indent-fix.patch`** — NOT on #1430 (merged as-is); goes in the follow-up PR.

### ★ GUIDING PRINCIPLE (João + Claude agreed 2026-06-25) — correctness over readability, in order
**Generated-output BYTE-IDENTITY is the non-negotiable gate. Readability (`StringConcatenation`→text-block) conversions are a genuine good (we AGREE with Ruben's goal) but are VERIFIED FOLLOW-UPS — never unverified blockers, sequenced AFTER correctness.** Never comply with "make it a multi-line string" on sight: convert only where **harness-proven byte-identical**; keep the builder (with byte-level justification to Ruben) where folding changes output (e.g. import-managed `append(TypeReference)` cannot fold to `%s`). Three real byte-drift bugs already came from *unverified* readability conversions (`AnnotationAware`, `modelWithContexts`, `FormatJvmModelInferrer`). The sweep exists to catch exactly this.

### Ordered plan
1. **Sweep running** — `wqx01ugww` (`.claude/wf-sweep.js`): 51 Opus agents, one per merged migrated `.xtend` (excl. #1430's 12 = already harness-done), hunting byte-drift + faithfulness; adversarial verify. **WAIT for it.**
2. **Phase 2 — harness-confirm** every `NEEDS_HARNESS`/drift file (build `AACA`/`Probe`-style harness vs `xtend-gen`); pin/fix real drift. *(`xtend-gen` ground truth lives in sibling worktrees' `xtend-gen/` dirs; `git show <commit>^:<xtend>` for originals.)*
3. **Phase 3 — ONE follow-up PR** off master: `aaca-indent-fix.patch` + every sweep-confirmed fix, each harness-gated, for Ruben.
4. **#1429, same discipline** — harness-verify its existing text-block conversions; address Ruben's 6 comments by converting where provably safe (harness-gated) + keeping builders with byte evidence where not; commit ContentAssist batch-1; re-request review as *"correctness-verified readability."*
5. **Broader readability follow-ups** — LAST, only after correctness locked; separate harness-gated PRs.

### #1429 — the 6 unresolved Ruben threads (all "use a multi-line string", correctness-gated)
- `AbstractAnnotationAwareAntlrGrammarGenerator.java:90` (current): keep builder but **drop `org.eclipse.xtend2.lib.StringConcatenation`**.
- `…Abstract…:112/:150/:180` (OUTDATED — code changed, likely already text-block'd by `b49eae6bc`): "clearly a multi-line string … and so on, I did not continue the review" (← he stopped partway, expects whole-file treatment).
- `AnnotationAwareAntlrContentAssistGrammarGenerator.java:567` (current): would benefit from a multi-line string (ContentAssist batch-1 coalescing still UNCOMMITTED in worktree).
- `AnnotationAwareAntlrGrammarGenerator.java:168` (current): "and this" (file coalesced in `cd1d420bf` — verify covers it).
His steering note: *"Claude constantly forgets … multi-line strings for readability … I interject it as steering."*

## Next action

**WAIT for sweep `wqx01ugww` to complete.** Then: report the per-file verdict table → **Phase 2** harness-confirm flagged files → fix → **Phase 3** single follow-up PR (incl. `aaca-indent-fix.patch`). Then **#1429** per the discipline above. Deferred: #29 Javadoc `{@code}`; scratch cleanup (`.claude/coalesce-verify`,`x2j-review`,`wf-*.js`,`sweep-cfg.json`,`pre-merge-cfg.json`); #1442 draft decision; Phase-0 nits (#1427 `modelWithContexts`, #1428 `List.of`→`Arrays.asList` dead-path) → fold into Phase-3 PR. Tasks: #11 sweep (in progress), #12 harness-confirm, #13 follow-up PR.
