package com.avaloq.tools.ddk.check.validation;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.avaloq.tools.ddk.check.runtime.configuration.ModelLocation;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckCatalogRegistry;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckValidatorRegistry;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckValidatorStandaloneSetup;

/**
 * Standalone setup for LibraryChecks as required by the standalone builder.
 */
@SuppressWarnings("nls")
public class LibraryChecksStandaloneSetup implements ICheckValidatorStandaloneSetup {

  private static final Logger LOG = LogManager.getLogger(LibraryChecksStandaloneSetup.class);
  private static final String GRAMMAR_NAME = "com.avaloq.tools.ddk.check.TestLanguage";
  private static final String CATALOG_FILE_PATH = "com/avaloq/tools/ddk/check/validation/LibraryChecks.check";

  @Override
  public void doSetup() {
    ICheckValidatorRegistry.INSTANCE.registerValidator(GRAMMAR_NAME, new LibraryChecksCheckImpl());
    ICheckCatalogRegistry.INSTANCE.registerCatalog(GRAMMAR_NAME, new ModelLocation(
      LibraryChecksStandaloneSetup.class.getClassLoader().getResource(CATALOG_FILE_PATH), CATALOG_FILE_PATH));
    LOG.info("Standalone setup done for com/avaloq/tools/ddk/check/validation/LibraryChecks.check");
  }

  @Override
  public String toString() {
    return "CheckValidatorSetup(/resource/com.avaloq.tools.ddk.check.test.runtime/src/com/avaloq/tools/ddk/check/validation/LibraryChecks.check)";
  }
}
