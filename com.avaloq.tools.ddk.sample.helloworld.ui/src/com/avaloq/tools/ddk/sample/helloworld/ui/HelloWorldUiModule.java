/*
 * generated by Xtext 2.19.0
 */
package com.avaloq.tools.ddk.sample.helloworld.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor;
import org.eclipse.xtext.ide.editor.contentassist.antlr.FollowElementCalculator;
import org.eclipse.xtext.ide.editor.contentassist.antlr.FollowElementComputer;

import com.avaloq.tools.ddk.check.runtime.configuration.CheckConfigurationStore;
import com.avaloq.tools.ddk.check.runtime.configuration.ICheckConfigurationStore;
import com.avaloq.tools.ddk.xtext.ide.contentAssist.AnnotationAwareFollowElementCalculator;
import com.avaloq.tools.ddk.xtext.ide.contentAssist.AnnotationAwareFollowElementComputer;

/**
 * Use this class to register components to be used within the Eclipse IDE.
 */
@FinalFieldsConstructor
public class HelloWorldUiModule extends AbstractHelloWorldUiModule {

    /**
     * Constructor for base parameter definition specific UI module.
     *
     * @param plugin the UI plugin
     */
    public HelloWorldUiModule(final AbstractUIPlugin plugin) {
        super(plugin);
    }

    /**
     * Adds a binding to the context aware configuration store. This enables using
     * the check configuration scope.
     *
     * @return the check configuration store
     */
    public Class<? extends ICheckConfigurationStore> bindICheckConfigurationStore() {
        return CheckConfigurationStore.class;
    }

    public Class<? extends FollowElementComputer> bindFollowElementComputer() {
        return AnnotationAwareFollowElementComputer.class;
    }
    public Class<? extends FollowElementCalculator> bindFollowElementCalculator() {
        return AnnotationAwareFollowElementCalculator.class;
    }
}
