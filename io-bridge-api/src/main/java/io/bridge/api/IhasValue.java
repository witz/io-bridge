package io.bridge.api;

/*-
 * #%L
 * IO Bridge API
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

/**
 *
 * *Internal* Interface to designate types having a Value.
 *
 * @author Charaf-Eddine SAIDI
 *
 */
interface IhasValue {

    /**
     *
     * @param value
     * @apiNote Implementers should parse the passed string to
     *          {@link #setValue(String)} to set the value.
     */
    void parseValue(String value);

    /**
     *
     * @param value
     * @apiNote Implementers should parse the passed string to
     *          {@link #setValue(String)} to set the value.
     */
    default void setValue(String value) {
        try {
            parseValue(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid attribute value: " + value);
        }
    }

    /**
     * @return the String presentation of the value
     */
    String getValue();

}
