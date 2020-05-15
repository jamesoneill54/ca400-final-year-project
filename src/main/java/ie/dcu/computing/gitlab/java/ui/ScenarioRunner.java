package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.AntColonyOptimisation;
import ie.dcu.computing.gitlab.java.Scenario;

import javax.swing.*;
import java.io.IOException;

public class ScenarioRunner extends SwingWorker<String, Object> {

    private final Scenario scenario;
    private final ScenariosPanel scenariosPanel;

    ScenarioRunner(ScenariosPanel scenariosPanel, Scenario scenario) {
        this.scenario = scenario;
        this.scenariosPanel = scenariosPanel;
    }

    @Override
    protected String doInBackground() {
        this.start();
        this.cancel(true);
        scenariosPanel.finishRunningScenario();
        return null;
    }

    public void start() {
        this.scenario.runScenario();
    }

    public void stop() {
        this.scenario.stopScenario();
    }
}
