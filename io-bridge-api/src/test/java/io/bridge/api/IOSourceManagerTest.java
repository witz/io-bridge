package io.bridge.api;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Charaf-Eddine SAIDI
 *
 */
public class IOSourceManagerTest {

    @Test
    public void testIOSourceManagerGetSource() {
        Optional<IOSource> source = IOSourceManager.getIOSource("source");
        Assert.assertNotNull(source);
    }

}
