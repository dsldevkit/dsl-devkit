[DSL Developer Kit and Runtime Library](https://github.com/dsldevkit/dsl-devkit)
===================================
![Build Status](https://github.com/dsldevkit/dsl-devkit/actions/workflows/verify.yml/badge.svg?branch=master)

Copyright 2016 Avaloq Group AG

# Documentation

You can find more detailed documentation and examples at [ddk.tools.avaloq.com](https://ddk.tools.avaloq.com/).

# Installation

* Take [Eclipse IDE for Java Developers](https://www.eclipse.org/downloads/packages/release/2020-06) (i.e. 2020-06)
* Install [Xtext SDK 2.25](http://download.eclipse.org/modeling/tmf/xtext/updates/composite/releases/) (currenlty DDK is based on Xtext 2.25)
* There is no public update site for DDK yet, so 
  * import all plugins from this repository into a new Eclipse workspace
  * change current target to ddk.target
  * compile and export the four features available in the workspace (runtime, and the DDK itself)
* Install the exported features into your Eclipse

# Repository Structure


| Plugin                                 | Description                                                |
|----------------------------------------|------------------------------------------------------------|
| com.avaloq.tools.ddk.workflow          | Mwe2 workflows to regenerate DDK languages                 |
| com.avaloq.tools.ddk.xtext.generator   | Mwe2 workflow fragements for generating DSL implementation |
| com.avaloq.tools.ddk.xtext             | Extensions to Xtext runtime library                        |
| com.avaloq.tools.ddk.xtext.common.\*   | Optional extensions to Xtext runtime library               |
| com.avaloq.tools.ddk.xtext.ui          | Eclipse UI part of runtime library extensions              |
| com.avaloq.tools.ddk.xtext.builder     | Extensions to Xtext builder                                |
| com.avaloq.tools.ddk.xtext.format.\*   | Format DSL                                                 |
| com.avaloq.tools.ddk.xtext.check.\*    | Check DSL                                                  |
| com.avaloq.tools.ddk.xtext.checkcfg.\* | Check Configuration DSL                                    |
| com.avaloq.tools.ddk.xtext.scope.\*    | Scope DSL                                                  |
| com.avaloq.tools.ddk.xtext.export.\*   | Export DSL                                                 |
| com.avaloq.tools.ddk.xtext.valid.\*    | obsolete and is replaced by Check DSL                      |
| com.avaloq.tools.ddk.typesystem        | Basics for building a typesystem for a DSL                 |
| com.avaloq.tools.ddk.test.\*           | Support for writing tests for DSLs                         |
| com.avaloq.tools.ddk.xtextspy.\*       | View to support debugging a DSL                            |

| Feature                                | Description                                                          |
|----------------------------------------|----------------------------------------------------------------------|
| com.avaloq.tools.ddk.runtime.feature   | Runtime extension to Xtext                                           |
| com.avaloq.tools.ddk.feature           | Toolkit to install into Eclipse on top of Xtext for DSL development  |


# Overview

DSL Developer Kit is an extension layer to Xtext built for active Xtext users. It extends Xtext runtime to handle more sophisticated cases in DSL design, to support scaling for larger models and better monitoring and recovery in headless frameworks based on Xtext. DDK comes with a few handy small DSLs helping to standardize implementations of tools for DSLs. Read the [introduction](https://ddk.tools.avaloq.com/overview.html).
