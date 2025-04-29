package com.tosin.investire.commons.jooq;

import org.jibx.schema.codegen.extend.DefaultNameConverter;
import org.jibx.schema.codegen.extend.NameConverter;
import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;
import org.jooq.meta.EnumDefinition;

public class NamingStrategy extends DefaultGeneratorStrategy {

    private final NameConverter nameTools = new DefaultNameConverter();

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {

        final String javaClassName = super.getJavaClassName(definition, mode);
        if (definition instanceof EnumDefinition) {
            return javaClassName;
        }
        if (mode == Mode.DEFAULT) {
            return javaClassName;
        }
        return nameTools.depluralize(javaClassName);
    }


}
