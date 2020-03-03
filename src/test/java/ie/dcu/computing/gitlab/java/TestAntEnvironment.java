package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.awt.*;

public class TestAntEnvironment {

    AntEnvironment testAntEnvironment = new AntEnvironment();

    @Test
    void testInitEnvironment() {
        Assert.assertEquals(testAntEnvironment.getBackground(), Color.WHITE);
        Assert.assertEquals(testAntEnvironment.getPreferredSize(), new Dimension(6, 4));
    }

    @Test
    void testGetAnt() {
        Assert.assertEquals(testAntEnvironment.getAnt().getClass(), Ant.class);
    }

    @Test
    void testGetObstacle() {
        Assert.assertEquals(testAntEnvironment.getObstacle().getClass(), Obstacle.class);
    }

    @Test
    void testGetGoal() {
        Assert.assertEquals(testAntEnvironment.getGoal().getClass(), Goal.class);
    }

    @Test
    void testGetAnthill() {
        Assert.assertEquals(testAntEnvironment.getAnthill().getClass(), Anthill.class);
    }

    @Test
    void testEnvironmentSize() {
        Assert.assertEquals(testAntEnvironment.getPreferredSize().width,
                testAntEnvironment.getEnvironmentWidth());
        Assert.assertEquals(testAntEnvironment.getPreferredSize().height,
                testAntEnvironment.getEnvironmentHeight());
    }
}
