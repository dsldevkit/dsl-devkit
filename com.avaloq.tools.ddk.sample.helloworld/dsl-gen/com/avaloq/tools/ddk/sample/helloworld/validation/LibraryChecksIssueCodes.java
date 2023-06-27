package com.avaloq.tools.ddk.sample.helloworld.validation;

/**
 * Issue codes which may be used to address validation issues (for instance in quickfixes).
 */
@SuppressWarnings("all")
public final class LibraryChecksIssueCodes {

  public static final String CACHE_DOESNT_WORK = "com.avaloq.tools.ddk.sample.helloworld.validation.LibraryChecksIssueCodes.CacheDoesntWork";
  public static final String CACHE_INJECTION_FAILED = "com.avaloq.tools.ddk.sample.helloworld.validation.LibraryChecksIssueCodes.CacheInjectionFailed";
  public static final String CHECK_CATALOG_IS_ACTIVE = "com.avaloq.tools.ddk.sample.helloworld.validation.LibraryChecksIssueCodes.CheckCatalogIsActive";
  public static final String FORMAL_PARAMETERS = "com.avaloq.tools.ddk.sample.helloworld.validation.LibraryChecksIssueCodes.FormalParameters";

  private LibraryChecksIssueCodes() {
    // Prevent instantiation.
  }
}
