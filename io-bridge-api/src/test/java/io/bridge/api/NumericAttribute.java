package io.bridge.api;

import org.apache.commons.lang3.StringUtils;

import io.bridge.api.exceptions.InvalidAttributeException;

public class NumericAttribute implements Attribute {

    private String value;

    @Override
    public String getName() {
        return "string";
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void validate() throws InvalidAttributeException {
        if (StringUtils.isBlank(value)) {
            throw new InvalidAttributeException("Invalid string");
        }
    }

}
