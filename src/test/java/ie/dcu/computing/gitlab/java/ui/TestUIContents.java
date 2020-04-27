package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.AntColonyOptimisation;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestUIContents {

    @Test
    void instantiationTest() {
        AntColonyOptimisation acoAlgorithm = new AntColonyOptimisation(1, 2, 1);
        acoAlgorithm.setGoal(0, 1);
        acoAlgorithm.setHome(0, 0);
        AntEnvironment antEnvironment = new AntEnvironment(acoAlgorithm);
        new UIContents(acoAlgorithm, antEnvironment);

        Assert.assertEquals(StatusPanel.class, UIContents.getStatusPanel().getClass());
        Assert.assertEquals(RunnerControlPanel.class, UIContents.getRunnerControlPanel().getClass());
    }
}
