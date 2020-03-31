package io.bridge.api.impl;

import io.bridge.api.Attribute;

/**
 *
 * @author Charaf-Eddine SAIDI
 *
 */
public abstract class NumericAttribute implements Attribute {

    protected int value;

    @Override
    public void setValue(String value) {
        this.value = Integer.parseInt(value);
    }

    @Override
    public String getValue() {
        return Integer.toString(value);
    }

}
