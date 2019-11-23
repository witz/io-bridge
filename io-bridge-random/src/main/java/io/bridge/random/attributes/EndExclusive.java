package io.bridge.random.attributes;

/*-
 * #%L
 * io-bridge Random
 * %%
 * Copyright (C) 2019 Witz
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import io.bridge.api.Attribute;
import io.bridge.api.exceptions.InvalidAttributeException;

public class EndExclusive implements Attribute {

    public static final String NAME = "endExclusive";
    private int value;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void parseValue(String value) {
        this.value = Integer.parseInt(value);
    }

    @Override
    public String getValue() {
        return Integer.toString(value);
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