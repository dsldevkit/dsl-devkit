package com.avaloq.tools.ddk.xtext.scope.generator

import com.avaloq.tools.ddk.xtext.expression.expression.Expression
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall
import com.avaloq.tools.ddk.xtext.expression.expression.IntegerLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall
import com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral
import com.avaloq.tools.ddk.xtext.expression.generator.ExpressionExtensions
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX
import com.avaloq.tools.ddk.xtext.scope.jvmmodel.ScopeExpressionCompiler
import com.avaloq.tools.ddk.xtext.scope.jvmmodel.ScopeExpressionTranslator
import com.avaloq.tools.ddk.xtext.scope.scope.Naming
import com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel
import com.google.inject.Inject
import org.eclipse.emf.ecore.EClass

class ScopeNameProviderGenerator {

  @Inject extension GeneratorUtilX
  @Inject extension ScopeProviderX

  @Inject ScopeExpressionTranslator translator
  @Inject ScopeExpressionCompiler compiler

  extension GenModelUtilX genModelUtil

  /**
   * Configures the collaborators required by the extracted body method. Used by the Xbase based
   * {@code ScopeJvmModelInferrer} which attaches the body method directly to an inferred JVM operation rather than
   * generating a full compilation unit through {@link #generate}.
   *
   * @param genModelUtil
   *          the gen model utility, must not be {@code null}
   */
  def void configure(GenModelUtilX genModelUtil) {
    this.genModelUtil = genModelUtil
  }

  /**
   * Produces the body of the {@code internalGetNameFunctions(EClass)} method. Extracted so the Xbase based
   * {@code ScopeJvmModelInferrer} can attach it directly as a method body.
   *
   * @param it
   *          the scope model, must not be {@code null}
   * @return the method body, never {@code null}
   */
  def internalGetNameFunctionsBody(ScopeModel it) '''
    «IF it.naming !== null»
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
  '''

  def nameFunctions(Naming it, ScopeModel model) {
    nameFunctions(it, model, null, null)
  }

  def nameFunctions(Naming it, ScopeModel model, String contextName, EClass contextType) {
    '''java.util.Arrays.asList(«FOR n : names SEPARATOR ", "»«nameFunction(n, model, contextName, contextType)»«ENDFOR»)'''
  }

  def dispatch String nameFunction(NamingExpression it, ScopeModel model, String contextName, EClass contextType) {
    if (factory) {
      if (contextName === null || contextType === null) {
        compiler.javaExpression(expression, translator.newCompilationContext('UNEXPECTED_THIS', null, #[], it))
      } else {
        compiler.javaExpression(expression, translator.newCompilationContext('UNEXPECTED_THIS', null, #[contextName -> contextType.instanceClassName], it))
      }
    } else if (export) {
      'com.avaloq.tools.ddk.xtext.scoping.NameFunctions.exportNameFunction()'
    } else {
      nameFunction(expression, model, contextName, contextType)
    }
  }

  def dispatch String nameFunction(Expression it, ScopeModel model, String contextName, EClass contextType) {
    'EXPRESSION_NOT_SUPPORTED("' + ExpressionExtensions.serialize(it) + '")'
  }

  def dispatch String nameFunction(StringLiteral it, ScopeModel model, String contextName, EClass contextType) {
    'com.avaloq.tools.ddk.xtext.scoping.NameFunctions.fromConstant("' + ^val + '")'
  }

  def dispatch String nameFunction(IntegerLiteral it, ScopeModel model, String contextName, EClass contextType) {
    'com.avaloq.tools.ddk.xtext.scoping.NameFunctions.fromConstant(String.valueOf(' + ^val + '))'
  }

  def dispatch String nameFunction(FeatureCall it, ScopeModel model, String contextName, EClass contextType) '''
    «val currentContext = if (contextName === null) translator.newCompilationContext('obj', scopeType(), #[], it) else translator.newCompilationContext('obj', scopeType(), #[contextName -> contextType.instanceClassName], it)»
    «IF (target === null || target.isThisCall()) && compiler.isSimpleFeatureCall(it, currentContext)»com.avaloq.tools.ddk.xtext.scoping.NameFunctions.fromFeature(«literalIdentifier(feature())»)«
    ELSEIF compiler.isSimpleNavigation(it, currentContext)»
      object -> {
          final «scopeType().instanceClassName()» obj = («scopeType().instanceClassName()») object;
          return toQualifiedName(«compiler.javaExpression(it, currentContext)»);
        }
      «
    ELSE»EXPRESSION_NOT_SUPPORTED("«ExpressionExtensions.serialize(it)»")«ENDIF
  »'''

  def dispatch String nameFunction(OperationCall it, ScopeModel model, String contextName, EClass contextType) '''
    «val currentContext = if (contextName === null) translator.newCompilationContext('obj', scopeType(), #[], it) else translator.newCompilationContext('obj', scopeType(), #[contextName -> contextType.instanceClassName], it)»
    «IF compiler.isCompilable(it, currentContext)»
      object -> {
          final «scopeType().instanceClassName()» obj = («scopeType().instanceClassName()») object;
          return toQualifiedName(«compiler.javaExpression(it, currentContext)»);
        }
    «
    ELSE»EXPRESSION_NOT_SUPPORTED("«ExpressionExtensions.serialize(it)»")«ENDIF
  »'''

  def private dispatch boolean isThisCall(Expression it) {
    false
  }

  def private dispatch boolean isThisCall(FeatureCall it) {
    name === null && type !== null && type.id !== null && type.id.size == 1 && type.id.head == 'this'
  }

}
