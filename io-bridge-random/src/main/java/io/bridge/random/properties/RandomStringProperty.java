package io.bridge.random.properties;

import java.security.SecureRandom;

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

import io.bridge.api.Attribute;
import io.bridge.random.RandomSource;
import io.bridge.random.attributes.CountAttribute;

/**
 *
 * @author Charaf-Eddine SAIDI
 *
 */
public class RandomStringProperty implements IRandomProperty {

    public static final String NAME = "string";
    private String value;
    private final Map<String, Attribute> attributes;

    public RandomStringProperty() {
        this.attributes = Map.of(CountAttribute.NAME, new CountAttribute());
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void setValue(String value) {
        this.value = value;

    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Map<String, Attribute> getAttributes() {
        return attributes;
    }

    @Override
    public void read(RandomSource source) {
        int count = Integer.parseInt(attributes.get(CountAttribute.NAME).getValue());
		this.value = randomAlphanumeric(count);
    }

    @Override
    public void write(RandomSource source) {
        throw new UnsupportedOperationException();
    }

	/*
	 * Helper
	 */

	private String randomAlphanumeric(int count) {

		if (count < 1) {
			throw new IllegalArgumentException("Invalid String lenght");
		}

		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String alphanumeric = alphabet + alphabet.toUpperCase() + "0123456789";
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder(count);
		for (int i = 0; i < count; i++) {
			sb.append(alphanumeric.charAt(random.nextInt(alphanumeric.length())));
		}

		return sb.toString();
	}

}
