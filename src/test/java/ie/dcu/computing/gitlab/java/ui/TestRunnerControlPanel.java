package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.AntColonyOptimisation;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestRunnerControlPanel {

    RunnerControlPanel runnerControlPanel;

    void setUpTestResources(int numberOfAnts) {
        AntColonyOptimisation acoAlgorithm = new AntColonyOptimisation(1, 2, numberOfAnts);
        acoAlgorithm.setGoal(0, 1);
        acoAlgorithm.setHome(0, 0);
        AntEnvironment antEnvironment = new AntEnvironment(acoAlgorithm);
        StatusPanel statusPanel = new StatusPanel(acoAlgorithm);
        runnerControlPanel = new RunnerControlPanel(acoAlgorithm, antEnvironment, statusPanel);
    }

    @Test
    void instantiationTest() {
        setUpTestResources(1);

        Assert.assertNotNull(runnerControlPanel.getStartButton());
        Assert.assertNotNull(runnerControlPanel.getPauseButton());
        Assert.assertNotNull(runnerControlPanel.getStopButton());
        Assert.assertNotNull(runnerControlPanel.getSkipButton());
        Assert.assertFalse(runnerControlPanel.getPauseButton().isVisible());
        Assert.assertNull(runnerControlPanel.getSimulationRunner());
    }

    @Test
    void playButtonPressedTest() {
        setUpTestResources(5);

        runnerControlPanel.getStartButton().doClick();
        Assert.assertNotNull(runnerControlPanel.getSimulationRunner());
    }
}
