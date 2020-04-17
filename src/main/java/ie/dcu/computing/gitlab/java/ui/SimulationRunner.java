package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.AntColonyOptimisation;

import javax.swing.*;
import java.io.IOException;

public class SimulationRunner extends SwingWorker<String, Object> {

    private final RunnerControlPanel runnerControlPanel;
    private final AntColonyOptimisation acoAlgorithm;
    private final AntEnvironment antEnvironment;

    SimulationRunner(RunnerControlPanel runnerControlPanel, AntColonyOptimisation acoAlgorithm, AntEnvironment antEnvironment) {
        this.runnerControlPanel = runnerControlPanel;
        this.acoAlgorithm = acoAlgorithm;
        this.antEnvironment = antEnvironment;
    }

    @Override
    protected String doInBackground() throws Exception {
        this.start();
        runnerControlPanel.finishSimulation();
        this.cancel(true);
        return null;
    }

    public void start() throws IOException {
        this.acoAlgorithm.startOptimisation();
    }

    public void pause() {
        this.acoAlgorithm.pauseOptimisation();
    }

    public void resume() {
        this.acoAlgorithm.resumeOptimisation();
    }

    public void skipIteration() {
        this.acoAlgorithm.skipIteration();
    }

    public void stop() {
        this.acoAlgorithm.stopOptimisation();
    }

    public boolean isPaused() {
        return this.acoAlgorithm.isPaused();
    }
}
