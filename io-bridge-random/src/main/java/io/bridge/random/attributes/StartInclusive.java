package io.bridge.random.attributes;

import io.bridge.api.exceptions.InvalidAttributeException;
import io.bridge.api.impl.NumericAttribute;

public class StartInclusive extends NumericAttribute {

    public static final String NAME = "startInclusive";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void validate() throws InvalidAttributeException {
        try {
            if (value >= 0) {
                return;
            }
        } catch (Exception e) {
            // Ignore this exception
        }

        throw new InvalidAttributeException("Invalid startInclusive attribute value");
    }

}
