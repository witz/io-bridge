package io.bridge.random.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.bridge.random.RandomSource;
import io.bridge.random.attributes.EndExclusive;

/**
 *
 * @author Charaf-Eddine SAIDI
 *
 */
public class RandomNumberPropertyTest {

	private RandomSource source;
	private RandomNumberProperty property;

	@Before
	public void setUp() {
		source = new RandomSource();
		property = new RandomNumberProperty();
	}

	@Test
	public void testGetName() {
		assertEquals(RandomNumberProperty.NAME, property.getName());
	}

	@Test
	public void testGetAttributes() {
		assertTrue(!property.getAttributes().isEmpty());
	}

	@Test
	public void testSetValue() {
		property.setValue("42");
		assertEquals("42", property.getValue());
	}

	@Test
	public void testRead() {
		property.getAttributes().get(EndExclusive.NAME).setValue("1000");
		property.read(source);
		assertNotNull(property.getValue());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testWrite() {
		property.write(source);
	}

}
