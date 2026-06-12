# antlr-generator-3.2.0-patch.jar

Patched ANTLR 3.2 parser generator used by Xtext's `AntlrToolFacade` to generate
the DDK languages' lexers and parsers when running the MWE2 workflows.

- Source: https://artifacts.itemis.cloud/repository/p2/antlr-generator-3.2.0-patch.jar
- SHA-256: `35853e64321ed8aded0bf89b791db4af0a18b362c7c084fb8a46c78dca21f74e`
- License: BSD (ANTLR 3, http://www.antlr.org/license.html)

Committed so that grammar regeneration does not depend on third-party hosting:
`AntlrToolFacade` loads `de.itemis.xtext.antlr.toolrunner.AntlrToolRunner` from the
launch classpath (see `GenerateTestLanguage.mwe2.launch`) and otherwise falls back to
downloading this exact jar — unverified — from the URL above. Launch configurations
without an explicit classpath still use that download fallback, which caches to a
gitignored `./.antlr-generator-3.2.0-patch.jar` in the generating project.

This jar becomes deletable if generation ever moves to the `xtext-maven-plugin`
with `org.eclipse.xtext:xtext-antlr-generator` from Maven Central.
