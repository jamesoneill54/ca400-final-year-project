package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestSimulation {

    @Test
    void getAndSetAnimationDelayTest() {
        Assert.assertEquals(100, Simulation.getAnimationDelay());
        Simulation.setAnimationDelay(200);
        Assert.assertEquals(200, Simulation.getAnimationDelay());
    }
}
