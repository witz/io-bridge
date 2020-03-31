package io.bridge.random.attributes;

import io.bridge.api.exceptions.InvalidAttributeException;
import io.bridge.api.impl.NumericAttribute;

public class CountAttribute extends NumericAttribute {

    public static final String NAME = "count";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void validate() throws InvalidAttributeException {
        try {
            if (value >= 0 && value <= 100) {
                return;
            }
        } catch (Exception e) {
            // Ignore this exception
        }

        throw new InvalidAttributeException("Invalid count attribute value");
    }

}
