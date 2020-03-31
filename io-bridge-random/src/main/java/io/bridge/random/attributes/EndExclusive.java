package io.bridge.random.attributes;

import io.bridge.api.exceptions.InvalidAttributeException;
import io.bridge.api.impl.NumericAttribute;

public class EndExclusive extends NumericAttribute {

    public static final String NAME = "endExclusive";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void validate() throws InvalidAttributeException {
        try {
            if (value <= 10000) {
                return;
            }
        } catch (Exception e) {
            // Ignore this exception
        }

        throw new InvalidAttributeException("Invalid endExclusive attribute value");
    }

}
