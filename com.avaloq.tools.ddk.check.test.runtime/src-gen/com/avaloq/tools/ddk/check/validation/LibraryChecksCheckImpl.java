package com.avaloq.tools.ddk.check.validation;

import com.avaloq.tools.ddk.check.lib.IResourceCache;
import com.avaloq.tools.ddk.check.runtime.issue.DispatchingCheckImpl;
import com.avaloq.tools.ddk.check.testLanguage.Greeting;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;

/**
 * Validator for LibraryChecks.
 */
@SuppressWarnings("all")
public class LibraryChecksCheckImpl extends DispatchingCheckImpl {
  @Inject
  private LibraryChecksCheckCatalog libraryChecksCatalog;

  @Inject
  private IResourceCache cache;

  public String getQualifiedCatalogName() {
    return "com.avaloq.tools.ddk.check.validation.LibraryChecks";
  }

  public final ImmutableMap<String, String> getIssueCodeToLabelMap() {
    return LibraryChecksCheckCatalog.getIssueCodeToLabelMap();
  }

  @Override
  public void validate(final CheckMode checkMode, final EObject object, final DispatchingCheckImpl.DiagnosticCollector diagnosticCollector) {
    if (checkMode.shouldCheck(CheckType.FAST)) {
      diagnosticCollector.setCurrentCheckType(CheckType.FAST);
      if (object instanceof final com.avaloq.tools.ddk.check.testLanguage.Greeting castObject) {
        validate("checkCatalogIsActiveGreeting", "LibraryChecks.checkCatalogIsActiveGreeting", object,
                 () -> checkCatalogIsActiveGreeting(castObject, diagnosticCollector), diagnosticCollector);
        validate("cacheInjectionFailedGreeting", "LibraryChecks.cacheInjectionFailedGreeting", object,
                 () -> cacheInjectionFailedGreeting(castObject, diagnosticCollector), diagnosticCollector);
        validate("cacheDoesntWorkGreeting", "LibraryChecks.cacheDoesntWorkGreeting", object,
                 () -> cacheDoesntWorkGreeting(castObject, diagnosticCollector), diagnosticCollector);
        validate("formalParametersGreeting", "LibraryChecks.formalParametersGreeting", object,
                 () -> formalParametersGreeting(castObject, diagnosticCollector), diagnosticCollector);
      }
    }
  }

  /**
   * checkCatalogIsActiveGreeting.
   */
  @Check(CheckType.FAST)
  public void checkCatalogIsActiveGreeting(final Greeting it, final DispatchingCheckImpl.DiagnosticCollector diagnosticCollector) {// Issue diagnostic
    libraryChecksCatalog.accept(diagnosticCollector, //
      it, // context EObject
      null, // EStructuralFeature
      libraryChecksCatalog.getCheckCatalogIsActiveMessage(), // Message
      libraryChecksCatalog.getCheckCatalogIsActiveSeverityKind(it), // Severity 
      ValidationMessageAcceptor.INSIGNIFICANT_INDEX, // Marker index
      LibraryChecksIssueCodes.CHECK_CATALOG_IS_ACTIVE // Issue code & data
    );
  }

  /**
   * cacheInjectionFailedGreeting.
   */
  @Check(CheckType.FAST)
  public void cacheInjectionFailedGreeting(final Greeting g, final DispatchingCheckImpl.DiagnosticCollector diagnosticCollector) {
    if ((LibraryChecksCheckImpl.this.cache == null)) {// Issue diagnostic
      libraryChecksCatalog.accept(diagnosticCollector, //
        g, // context EObject
        null, // EStructuralFeature
        libraryChecksCatalog.getCacheInjectionFailedMessage(), // Message
        libraryChecksCatalog.getCacheInjectionFailedSeverityKind(g), // Severity 
        ValidationMessageAcceptor.INSIGNIFICANT_INDEX, // Marker index
        LibraryChecksIssueCodes.CACHE_INJECTION_FAILED // Issue code & data
      );
    }
  }

  /**
   * cacheDoesntWorkGreeting.
   */
  @Check(CheckType.FAST)
  public void cacheDoesntWorkGreeting(final Greeting it, final DispatchingCheckImpl.DiagnosticCollector diagnosticCollector) {
    String _qualifiedCatalogName = this.getQualifiedCatalogName();
    final String key = (_qualifiedCatalogName + ".testValue");
    try {
      LibraryChecksCheckImpl.this.cache.<Boolean>put(it, key, Boolean.TRUE);
      final Boolean value = LibraryChecksCheckImpl.this.cache.<Boolean>get(it, key);
      if (((value == null) || (!(value).booleanValue()))) {
        // Issue diagnostic
        libraryChecksCatalog.accept(diagnosticCollector, //
          it, // context EObject
          null, // EStructuralFeature
          libraryChecksCatalog.getCacheDoesntWorkMessage(("Could not read value from cache: " + value)), // Message
          libraryChecksCatalog.getCacheDoesntWorkSeverityKind(it), // Severity 
          ValidationMessageAcceptor.INSIGNIFICANT_INDEX, // Marker index
          LibraryChecksIssueCodes.CACHE_DOESNT_WORK // Issue code & data
        );
      }
    } catch (final Throwable _t) {
      if (_t instanceof Throwable) {
        final Throwable t = (Throwable)_t;
        String _message = t.getMessage();
        String _plus = ("Exception in cache access: " + _message);
        // Issue diagnostic
        libraryChecksCatalog.accept(diagnosticCollector, //
          it, // context EObject
          null, // EStructuralFeature
          libraryChecksCatalog.getCacheDoesntWorkMessage(_plus), // Message
          libraryChecksCatalog.getCacheDoesntWorkSeverityKind(it), // Severity 
          ValidationMessageAcceptor.INSIGNIFICANT_INDEX, // Marker index
          LibraryChecksIssueCodes.CACHE_DOESNT_WORK // Issue code & data
        );
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }

  private class FormalParametersClass {





    public void runGreeting(final Greeting it, final DispatchingCheckImpl.DiagnosticCollector diagnosticCollector) {
      final String p1 = libraryChecksCatalog.getFormalParameters_Param1(it);
      final boolean p2 = false;
      final List<String> expectedNames = ImmutableList.<String>of("foo", "bar", "ba\u0001\nz");
      final List<Integer> expectedInts = Collections.<Integer>unmodifiableList(CollectionLiterals.<Integer>newArrayList(Integer.valueOf(5), Integer.valueOf((-42)), Integer.valueOf(7)));
      boolean _equals = "param1".equals(p1);
      boolean _not = (!_equals);
      if (_not) {
        // Issue diagnostic
        libraryChecksCatalog.accept(diagnosticCollector, //
          it, // context EObject
          null, // EStructuralFeature
          libraryChecksCatalog.getFormalParametersMessage(("String parameter wrong (expected \"param1\"): " + p1)), // Message
          libraryChecksCatalog.getFormalParametersSeverityKind(it), // Severity 
          ValidationMessageAcceptor.INSIGNIFICANT_INDEX, // Marker index
          LibraryChecksIssueCodes.FORMAL_PARAMETERS // Issue code & data
        );
      }
      if (((p2 != (libraryChecksCatalog.getFormalParameters_Param3(it)).booleanValue()) || ((!p2) != libraryChecksCatalog.getFormalParameters_Param2(it)))) {
        // Issue diagnostic
        libraryChecksCatalog.accept(diagnosticCollector, //
          it, // context EObject
          null, // EStructuralFeature
          libraryChecksCatalog.getFormalParametersMessage("Boolean parameter wrong."), // Message
          libraryChecksCatalog.getFormalParametersSeverityKind(it), // Severity 
          ValidationMessageAcceptor.INSIGNIFICANT_INDEX, // Marker index
          LibraryChecksIssueCodes.FORMAL_PARAMETERS // Issue code & data
        );
      }
      int i = 0;
      final List<String> names = libraryChecksCatalog.getFormalParameters_Names(it);
      int _size = names.size();
      int _size_1 = expectedNames.size();
      boolean _notEquals = (_size != _size_1);
      if (_notEquals) {
        int _size_2 = names.size();
        String _plus = ("Expected three names, got " + Integer.valueOf(_size_2));
        // Issue diagnostic
        libraryChecksCatalog.accept(diagnosticCollector, //
          it, // context EObject
          null, // EStructuralFeature
          libraryChecksCatalog.getFormalParametersMessage(_plus), // Message
          libraryChecksCatalog.getFormalParametersSeverityKind(it), // Severity 
          ValidationMessageAcceptor.INSIGNIFICANT_INDEX, // Marker index
          LibraryChecksIssueCodes.FORMAL_PARAMETERS // Issue code & data
        );
      } else {
        while ((i < names.size())) {
          {
            boolean _equals_1 = expectedNames.get(i).equals(names.get(i));
            boolean _not_1 = (!_equals_1);
            if (_not_1) {
              String _get = expectedNames.get(i);
              String _plus_1 = ("String mismatch in list, expected \"" + _get);
              String _plus_2 = (_plus_1 + "\" but got \"");
              String _get_1 = names.get(i);
              String _plus_3 = (_plus_2 + _get_1);
              String _plus_4 = (_plus_3 + "\"");
              // Issue diagnostic
              libraryChecksCatalog.accept(diagnosticCollector, //
                it, // context EObject
                null, // EStructuralFeature
                libraryChecksCatalog.getFormalParametersMessage(_plus_4), // Message
                libraryChecksCatalog.getFormalParametersSeverityKind(it), // Severity 
                ValidationMessageAcceptor.INSIGNIFICANT_INDEX, // Marker index
                LibraryChecksIssueCodes.FORMAL_PARAMETERS // Issue code & data
              );
            }
            i = (i + 1);
          }
        }
      }
      final List<Integer> INTS = libraryChecksCatalog.getFormalParameters_Ints(it);
      int _size_3 = INTS.size();
      int _size_4 = expectedInts.size();
      boolean _notEquals_1 = (_size_3 != _size_4);
      if (_notEquals_1) {
        int _size_5 = INTS.size();
        String _plus_1 = ("Expected three ints, got " + Integer.valueOf(_size_5));
        // Issue diagnostic
        libraryChecksCatalog.accept(diagnosticCollector, //
          it, // context EObject
          null, // EStructuralFeature
          libraryChecksCatalog.getFormalParametersMessage(_plus_1), // Message
          libraryChecksCatalog.getFormalParametersSeverityKind(it), // Severity 
          ValidationMessageAcceptor.INSIGNIFICANT_INDEX, // Marker index
          LibraryChecksIssueCodes.FORMAL_PARAMETERS // Issue code & data
        );
      }
      i = 0;
      while ((i < INTS.size())) {
        {
          int _intValue = expectedInts.get(i).intValue();
          int _intValue_1 = INTS.get(i).intValue();
          boolean _notEquals_2 = (_intValue != _intValue_1);
          if (_notEquals_2) {
            Integer _get = expectedInts.get(i);
            String _plus_2 = ((("Integer mismatch at index " + Integer.valueOf(i)) + ":") + _get);
            String _plus_3 = (_plus_2 + " != ");
            Integer _get_1 = INTS.get(i);
            String _plus_4 = (_plus_3 + _get_1);
            // Issue diagnostic
            libraryChecksCatalog.accept(diagnosticCollector, //
              it, // context EObject
              null, // EStructuralFeature
              libraryChecksCatalog.getFormalParametersMessage(_plus_4), // Message
              libraryChecksCatalog.getFormalParametersSeverityKind(it), // Severity 
              ValidationMessageAcceptor.INSIGNIFICANT_INDEX, // Marker index
              LibraryChecksIssueCodes.FORMAL_PARAMETERS // Issue code & data
            );
          }
          i = (i + 1);
        }
      }
    }
  }

  private LibraryChecksCheckImpl.FormalParametersClass formalParametersImpl = new FormalParametersClass();

  /**
   * formalParametersGreeting.
   */
  @Check(CheckType.FAST)
  public void formalParametersGreeting(final Greeting context, final DispatchingCheckImpl.DiagnosticCollector diagnosticCollector) {
    formalParametersImpl.runGreeting(context, diagnosticCollector);
  }
}
