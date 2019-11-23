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

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.MapUtils;

import io.bridge.api.exceptions.InvalidAttributeException;

/**
 * *Internal* Interface to designate types having {@link Attribute}s.
 *
 * @author Charaf-Eddine SAIDI
 *
 */
interface IhasAttributes extends IhasValidate {

    Map<String, Attribute> getAttributes();

    /**
     * Check attributes validity. Each attribute is validated using
     * {@link Attribute#validate()} method.
     *
     * @throws InvalidAttributeException for any invalid {@link Attribute}.
     */
    @Override
    default void validate() throws InvalidAttributeException {
        Map<String, Attribute> attributes = getAttributes();
        if (MapUtils.isNotEmpty(attributes)) {
            for (Entry<String, Attribute> e : attributes.entrySet()) {
                Attribute a = e.getValue();
                if (a != null) {
                    a.validate();
                }
            }
        }
    }

}
