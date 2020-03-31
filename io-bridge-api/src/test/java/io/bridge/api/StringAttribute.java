package io.bridge.api;

import org.apache.commons.lang3.StringUtils;

import io.bridge.api.exceptions.InvalidAttributeException;

public class StringAttribute extends io.bridge.api.impl.StringAttribute {

    @Override
    public String getName() {
        return "string";
    }

    @Override
    public void validate() throws InvalidAttributeException {
        if (StringUtils.isBlank(value)) {
            throw new InvalidAttributeException("Invalid string");
        }
    }

}
