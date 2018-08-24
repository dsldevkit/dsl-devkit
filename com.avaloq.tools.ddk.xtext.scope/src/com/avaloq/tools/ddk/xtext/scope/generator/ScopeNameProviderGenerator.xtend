package com.avaloq.tools.ddk.xtext.scope.generator

import com.avaloq.tools.ddk.xtext.expression.expression.Expression
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall
import com.avaloq.tools.ddk.xtext.expression.expression.IntegerLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall
import com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral
import com.avaloq.tools.ddk.xtext.expression.generator.CodeGenerationX
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext
import com.avaloq.tools.ddk.xtext.expression.generator.ExpressionExtensionsX
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX
import com.avaloq.tools.ddk.xtext.scope.scope.Naming
import com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel
import com.google.inject.Inject
import org.eclipse.emf.ecore.EClass

class ScopeNameProviderGenerator {

  @Inject extension CodeGenerationX
  @Inject extension ExpressionExtensionsX
  @Inject extension com.avaloq.tools.ddk.xtext.expression.generator.Naming
  @Inject extension GeneratorUtilX
  @Inject extension ScopeProviderX

  extension GenModelUtilX genModelUtil
  CompilationContext compilationContext

  def generate(ScopeModel it, CompilationContext compilationContext, GenModelUtilX genModelUtil) {
    this.compilationContext = compilationContext
    this.genModelUtil = genModelUtil
    '''
      package «getScopeNameProvider().toJavaPackage()»;

      import java.util.Arrays;

      import org.eclipse.emf.ecore.EClass;
      import org.eclipse.emf.ecore.EObject;

      import org.eclipse.xtext.naming.QualifiedName;

      import com.avaloq.tools.ddk.xtext.scoping.AbstractNameFunction;
      import com.avaloq.tools.ddk.xtext.scoping.AbstractScopeNameProvider;
      import com.avaloq.tools.ddk.xtext.scoping.INameFunction;
      import com.avaloq.tools.ddk.xtext.scoping.NameFunctions;

      import com.google.common.base.Function;
      import com.google.inject.Singleton;

      @SuppressWarnings("all")
      @Singleton
      public class «getScopeNameProvider().toSimpleName()» extends AbstractScopeNameProvider {

        /** {@inheritDoc} */
        @Override
        public Iterable<INameFunction> internalGetNameFunctions(final EClass eClass) {
          «IF it !== null && it.naming !== null»
            «FOR p : it.naming.namings.map[type.EPackage].toSet()»
              if («p.qualifiedPackageInterfaceName()».eINSTANCE == eClass.getEPackage()) {
                switch (eClass.getClassifierID()) {

                «FOR n : it.naming.namings.filter(n|n.type.EPackage == p)»
                  case «n.type.classifierIdLiteral()»:
                    «javaContributorComment(n.location())»
                    return «nameFunctions(n.naming, it)»;
                «ENDFOR»

                default:
                  return !eClass.getESuperTypes().isEmpty() ? getNameFunctions(eClass.getESuperTypes().get(0)) : null;
                }
              }
            «ENDFOR»
          «ENDIF»
          return !eClass.getESuperTypes().isEmpty() ? getNameFunctions(eClass.getESuperTypes().get(0)) : null;
        }

      }
    '''
  }

  def nameFunctions(Naming it, ScopeModel model) {
    nameFunctions(it, model, null, null)
  }

  def nameFunctions(Naming it, ScopeModel model, String contextName, EClass contextType) {
    '''Arrays.<INameFunction> asList(«FOR n : names SEPARATOR ", "»«nameFunction(n, model, contextName, contextType)»«ENDFOR»)'''
  }

  def dispatch String nameFunction(NamingExpression it, ScopeModel model, String contextName, EClass contextType) {
    if (factory) {
      if (contextName === null || contextType === null) {
        expression.javaExpression(compilationContext.clone('UNEXPECTED_THIS'))
      } else {
        expression.javaExpression(compilationContext.clone('UNEXPECTED_THIS', null, contextName, contextType))
      }
    } else if (export) {
      'NameFunctions.exportNameFunction()'
    } else {
      nameFunction(expression, model, contextName, contextType)
    }
  }

  def dispatch String nameFunction(Expression it, ScopeModel model, String contextName, EClass contextType) {
    'EXPRESSION_NOT_SUPPORTED("' + serialize() + '")'
  }

  def dispatch String nameFunction(StringLiteral it, ScopeModel model, String contextName, EClass contextType) {
    'NameFunctions.fromConstant("' + ^val + '")'
  }

  def dispatch String nameFunction(IntegerLiteral it, ScopeModel model, String contextName, EClass contextType) {
    'NameFunctions.fromConstant(String.valueOf(' + ^val + '))'
  }

  def dispatch String nameFunction(FeatureCall it, ScopeModel model, String contextName, EClass contextType) '''
    «val currentContext = if (contextName === null) compilationContext.clone('obj', scopeType()) else compilationContext.clone('obj', scopeType(), 'ctx', contextType)»
    «IF (target === null || target.isThisCall()) && isSimpleFeatureCall(currentContext)»NameFunctions.fromFeature(«literalIdentifier(feature())»)«
    ELSEIF isSimpleNavigation(currentContext)»new AbstractNameFunction() {
      public QualifiedName apply(final EObject object) {
        final «scopeType().instanceClassName()» obj = («scopeType().instanceClassName()») object;
          return toQualifiedName(«javaExpression(currentContext)»);
        }
      }«
    ELSE»EXPRESSION_NOT_SUPPORTED("«serialize()»")«ENDIF
  »'''

  def dispatch String nameFunction(OperationCall it, ScopeModel model, String contextName, EClass contextType) '''
    «val currentContext = if (contextName === null) compilationContext.clone('obj', scopeType()) else compilationContext.clone('obj', scopeType(), 'ctx', contextType)»
    «IF isCompilable(currentContext)»new AbstractNameFunction() {
      public QualifiedName apply(final EObject object) {
        final «scopeType().instanceClassName()» obj = («scopeType().instanceClassName()») object;
        return toQualifiedName(«javaExpression(currentContext)»);
      }
    }«
    ELSE»EXPRESSION_NOT_SUPPORTED("«serialize()»")«ENDIF
  »'''

}
