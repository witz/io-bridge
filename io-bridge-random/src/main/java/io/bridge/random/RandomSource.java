package io.bridge.random;

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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.bridge.api.Attribute;
import io.bridge.api.IOProperty;
import io.bridge.api.IOSource;
import io.bridge.random.properties.IRandomProperty;
import io.bridge.random.properties.RandomNumberProperty;
import io.bridge.random.properties.RandomStringProperty;

/**
 *
 * @author Charaf-Eddine SAIDI
 *
 */
public class RandomSource implements IOSource {

    public static final String NAME = "random";
    private static final String PROTOCOL_NUMERIC = "numeric";
    private static final String PROTOCOL_ALPHABETIC = "alphabetic";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Map<String, Attribute> getAttributes() {
        return Collections.emptyMap();
    }

    @Override
    public Set<String> getProtocols() {
        return Set.of(PROTOCOL_NUMERIC, PROTOCOL_ALPHABETIC);
    }

    @Override
    public Map<String, IOProperty> getProperties(String protocol) {
        Map<String, IOProperty> result = new HashMap<>();
        switch (protocol) {
        case PROTOCOL_NUMERIC:
            result.put(RandomNumberProperty.NAME, new RandomNumberProperty());
            break;
        case PROTOCOL_ALPHABETIC:
            result.put(RandomStringProperty.NAME, new RandomStringProperty());
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public void read(IOProperty property) {
        if (property instanceof IRandomProperty) {
            ((IRandomProperty) property).read(this);
        }
    }

    @Override
    public void write(IOProperty property) {
        if (property instanceof IRandomProperty) {
            ((IRandomProperty) property).write(this);
        }
    }

}
