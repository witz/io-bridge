package io.bridge.random.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.bridge.random.RandomSource;
import io.bridge.random.attributes.CountAttribute;

/**
 *
 * @author Charaf-Eddine SAIDI
 *
 */
public class RandomStringPropertyTest {

	private RandomSource source;
	private RandomStringProperty property;

	@Before
	public void setUp() {
		source = new RandomSource();
		property = new RandomStringProperty();
	}

	@Test
	public void testGetName() {
		assertEquals(RandomStringProperty.NAME, property.getName());
	}

	@Test
	public void testGetAttributes() {
		assertTrue(!property.getAttributes().isEmpty());
	}

	@Test
	public void testSetValue() {
		property.setValue("test");
		assertEquals("test", property.getValue());
	}

	@Test
	public void testRead() {
		property.getAttributes().get(CountAttribute.NAME).setValue("1000");
		property.read(source);
		assertNotNull(property.getValue());
		assertEquals(1000, property.getValue().length());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testWrite() {
		property.write(source);
	}

}
