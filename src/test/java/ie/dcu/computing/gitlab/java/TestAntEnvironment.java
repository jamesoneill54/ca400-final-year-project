package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.awt.*;

public class TestAntEnvironment {

    AntEnvironment testAntEnvironment = new AntEnvironment(null, 6, 4);

    @Test
    void testInitEnvironment() {
        Assert.assertEquals(testAntEnvironment.getBackground(), Color.WHITE);
        Assert.assertEquals(testAntEnvironment.getPreferredSize(), new Dimension(6, 4));
    }

    @Test
    void testGetObstacle() {
        Assert.assertEquals(testAntEnvironment.getObstacle().getClass(), Obstacle.class);
    }

    @Test
    void testEnvironmentSize() {
        Assert.assertEquals(testAntEnvironment.getPreferredSize().width,
                testAntEnvironment.getEnvironmentWidth());
        Assert.assertEquals(testAntEnvironment.getPreferredSize().height,
                testAntEnvironment.getEnvironmentHeight());
    }
}
