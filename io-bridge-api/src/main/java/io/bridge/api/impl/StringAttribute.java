package io.bridge.api.impl;

import io.bridge.api.Attribute;

/**
 *
 * @author Charaf-Eddine SAIDI
 *
 */
public abstract class StringAttribute implements Attribute {

    protected String value;

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
