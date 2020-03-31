package io.bridge.api;

import io.bridge.api.exceptions.InvalidAttributeException;

public class NumAttribute implements Attribute {

    private Integer value;

    @Override
    public String getName() {
        return "number";
    }

    @Override
    public void setValue(String value) {
        this.value = Integer.parseInt(value);
    }

    @Override
    public String getValue() {
        return Integer.toString(value);
    }

    @Override
    public void validate() throws InvalidAttributeException {
        if (value != null) {
            throw new InvalidAttributeException("Invalid number");
        }
    }

}
