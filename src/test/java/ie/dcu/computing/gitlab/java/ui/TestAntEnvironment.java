package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.AntColonyOptimisation;
import ie.dcu.computing.gitlab.java.ui.AntEnvironment;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.awt.*;

public class TestAntEnvironment {

    AntColonyOptimisation acoAlgorithm = new AntColonyOptimisation(6, 4, 1);
    AntEnvironment testAntEnvironment = new AntEnvironment(acoAlgorithm);

    @Test
    void testInitEnvironment() {
        Assert.assertEquals(testAntEnvironment.getPreferredSize(), new Dimension(6, 4));
    }

    @Test
    void testEnvironmentSize() {
        Assert.assertEquals(testAntEnvironment.getPreferredSize().width,
                testAntEnvironment.getEnvironmentWidth());
        Assert.assertEquals(testAntEnvironment.getPreferredSize().height,
                testAntEnvironment.getEnvironmentHeight());
    }
}
