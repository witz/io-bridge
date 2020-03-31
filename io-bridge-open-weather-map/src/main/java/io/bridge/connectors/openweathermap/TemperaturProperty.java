package io.bridge.connectors.openweathermap;

/*-
 * #%L
 * IOB Open Weather Map module
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

import io.bridge.api.Attribute;
import io.bridge.api.IOProperty;

/**
 *
 * @author Charaf-Eddine SAIDI
 *
 */
public class TemperaturProperty implements IOProperty {

    public static final String NAME = "Temperature";
    private double value;
    private Map<String, Attribute> attributes = Map.of(QueryAttribute.NAME, new QueryAttribute());

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Map<String, Attribute> getAttributes() {
        return attributes;
    }

    @Override
    public void setValue(String value) {
        this.value = Double.parseDouble(value);
    }

    @Override
    public String getValue() {
        return Double.toString(value);
    }

}
