package io.bridge.api;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.bridge.api.exceptions.InvalidAttributeException;

/**
 *
 * @author Charaf-Eddine SAIDI
 *
 */
public class IOPropertyTest {

    @Test
    public void testValidate() throws InvalidAttributeException {
        Attribute a = mock(Attribute.class);
        Map<String, Attribute> attrs = new HashMap<>();
        attrs.put("attr", a);
        IOProperty p = mock(IOProperty.class);
        doReturn(attrs).when(p).getAttributes();
        doCallRealMethod().when(p).validate();
        p.validate();

        verify(a, times(1)).validate();
        verify(p, times(1)).getAttributes();
    }

}
