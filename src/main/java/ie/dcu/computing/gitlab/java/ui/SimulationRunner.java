package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.AntColonyOptimisation;

import javax.swing.*;
import java.io.IOException;

public class SimulationRunner extends SwingWorker<String, Object> {

    private final RunnerControlPanel runnerControlPanel;
    private final AntColonyOptimisation acoAlgorithm;

    SimulationRunner(RunnerControlPanel runnerControlPanel, AntColonyOptimisation acoAlgorithm) {
        this.runnerControlPanel = runnerControlPanel;
        this.acoAlgorithm = acoAlgorithm;
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
