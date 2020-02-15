package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.awt.*;

public class TestAntEnvironment {

    AntEnvironment testAntEnvironment = new AntEnvironment();

    @Test
    void testInitEnvironment() {
        Assert.assertEquals(testAntEnvironment.getBackground(), Color.WHITE);
        Assert.assertEquals(testAntEnvironment.getPreferredSize(), new Dimension(640, 480));
    }

    @Test
    void testGetAnt() {
        Assert.assertEquals(testAntEnvironment.getAnt().getClass(), Ant.class);
    }

    @Test
    void testEnvironmentSize() {
        Assert.assertEquals(testAntEnvironment.getPreferredSize().width,
                testAntEnvironment.getEnvironmentWidth());
        Assert.assertEquals(testAntEnvironment.getPreferredSize().height,
                testAntEnvironment.getEnvironmentHeight());
    }
}
