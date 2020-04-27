package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.AntColonyOptimisation;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestSimulationRunner {

    SimulationRunner simulationRunner;

    void setUpTestResources() {
        AntColonyOptimisation acoAlgorithm = new AntColonyOptimisation(1, 2, 1);
        acoAlgorithm.setHome(0, 0);
        acoAlgorithm.setGoal(0, 1);
        acoAlgorithm.setCreateResults(false);
        AntEnvironment antEnvironment = new AntEnvironment(acoAlgorithm);
        simulationRunner = new SimulationRunner(null, acoAlgorithm, antEnvironment);
    }

    @Test
    void startAndPauseSimulationRunnerTest() throws IOException {
        setUpTestResources();

        simulationRunner.start();
        Assert.assertFalse(simulationRunner.isPaused());
        simulationRunner.pause();
        Assert.assertTrue(simulationRunner.isPaused());
    }
}
