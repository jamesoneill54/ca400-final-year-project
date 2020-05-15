package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.AntColonyOptimisation;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestScenariosPanel {

    @Test
    void runScenarioTest() {
        AntColonyOptimisation acoAlgorithm = new AntColonyOptimisation(10, 10 , 2);
        ScenariosPanel scenariosPanel = new ScenariosPanel(acoAlgorithm);
        scenariosPanel.runScenario();
        Assert.assertNotNull(scenariosPanel.getScenarioRunner());
        scenariosPanel.stopScenario();
        Assert.assertNull(scenariosPanel.getScenarioRunner());
    }
}
