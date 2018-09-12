package com.avaloq.tools.ddk.sample.helloworld.validation;

/**
 * Issue codes which may be used to address validation issues (for instance in quickfixes).
 */
@SuppressWarnings("all")
public final class LibraryChecksIssueCodes {

  public static final String CACHE_DOESNT_WORK = "com.avaloq.tools.ddk.sample.helloworld.validation.LibraryChecksIssueCodes.cache.doesnt.work";
  public static final String CACHE_INJECTION_FAILED = "com.avaloq.tools.ddk.sample.helloworld.validation.LibraryChecksIssueCodes.cache.injection.failed";
  public static final String CHECK_CATALOG_IS_ACTIVE = "com.avaloq.tools.ddk.sample.helloworld.validation.LibraryChecksIssueCodes.check.catalog.is.active";
  public static final String FORMAL_PARAMETERS = "com.avaloq.tools.ddk.sample.helloworld.validation.LibraryChecksIssueCodes.formal.parameters";

  private LibraryChecksIssueCodes() {
    // Prevent instantiation.
  }
}
