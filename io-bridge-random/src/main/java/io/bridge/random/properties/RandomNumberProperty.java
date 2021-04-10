package io.bridge.random.properties;

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

import java.util.Map;

import org.apache.commons.rng.UniformRandomProvider;

import io.bridge.api.Attribute;
import io.bridge.random.RandomSource;
import io.bridge.random.attributes.EndExclusive;

/**
 *
 * @author Charaf-Eddine SAIDI
 *
 */
public class RandomNumberProperty implements IRandomProperty {

    public static final String NAME = "number";
	private long value;
    private final Map<String, Attribute> attributes;

    public RandomNumberProperty() {
		this.attributes = Map.of(EndExclusive.NAME, new EndExclusive());
    }

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
		this.value = Long.parseLong(value);
    }

    @Override
    public String getValue() {
		return Long.toString(value);
    }

    @Override
    public void read(RandomSource source) {
        int endExclusive = Integer.parseInt(getAttributes().get(EndExclusive.NAME).getValue());
		UniformRandomProvider rng = org.apache.commons.rng.simple.RandomSource
				.create(org.apache.commons.rng.simple.RandomSource.MT);
		value = rng.nextLong(endExclusive);
    }

    @Override
    public void write(RandomSource source) {
        throw new UnsupportedOperationException();
    }

}
