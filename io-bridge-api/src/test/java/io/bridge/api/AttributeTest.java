package io.bridge.api;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import io.bridge.api.exceptions.InvalidAttributeException;

/**
 *
 * @author Charaf-Eddine SAIDI
 *
 */
@RunWith(Parameterized.class)
public class AttributeTest {

    private Attribute attribute;

    public AttributeTest(Attribute attribute) {
        this.attribute = attribute;
    }

    @Test
    public void testAttributeName() {
        Assert.assertTrue(attribute.getName() instanceof String);
    }

    @Test
    public void testAttributeGetValue() {
        Assert.assertTrue(attribute.getValue() instanceof String);
    }

    @Test
    public void testAttributeSetValue() {
        String v = "10";
        attribute.setValue(v);
        Assert.assertTrue(v.equals(attribute.getValue()));
    }

    @Test(expected = InvalidAttributeException.class)
    public void testAttributeValidate() throws InvalidAttributeException {
        try {
            attribute.setValue(null);
        } catch (NumberFormatException e) {
            // ignore number format exception
        }
        attribute.validate();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(new Object[] { new NumAttribute() }, new Object[] { new StringAttribute() });
    }

}
