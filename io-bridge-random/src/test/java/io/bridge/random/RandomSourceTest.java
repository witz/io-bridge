package io.bridge.random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.bridge.random.attributes.EndExclusive;
import io.bridge.random.properties.RandomNumberProperty;

/**
 *
 * @author Charaf-Eddine SAIDI
 *
 */
public class RandomSourceTest {

	private RandomSource source;
	private RandomNumberProperty property;

	@Before
	public void setUp() {
		source = new RandomSource();
		property = new RandomNumberProperty();
	}

	@Test
	public void testGetName() {
		assertEquals(RandomSource.NAME, source.getName());
	}

	@Test
	public void testGetAttributes() {
		assertTrue(source.getAttributes().isEmpty());
	}

	@Test
	public void testGetProtocols() {
		assertTrue(!source.getProtocols().isEmpty());
	}

	@Test
	public void testGetProperties() {
		assertTrue(!source.getProperties(source.getProtocols().iterator().next()).isEmpty());
	}

	@Test
	public void testRead() {
		property.getAttributes().get(EndExclusive.NAME).setValue("1000");
		source.read(property);
		assertNotNull(property.getValue());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testWrite() {
		source.write(property);
	}

}
