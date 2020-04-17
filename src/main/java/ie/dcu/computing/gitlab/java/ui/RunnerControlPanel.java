package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.AntColonyOptimisation;

import javax.swing.*;
import java.awt.*;

public class RunnerControlPanel extends JPanel {

    private SimulationRunner simulationRunner;
    private final StatusPanel statusPanel;
    private UIButton startButton;
    private UIButton pauseButton;
    private UIButton stopButton;
    private UIButton skipButton;

    RunnerControlPanel(AntColonyOptimisation acoAlgorithm, AntEnvironment antEnvironment, StatusPanel statusPanel) {
        this.statusPanel = statusPanel;
        setLayout(new GridBagLayout());

        generateButtons();

        startButton.addActionListener(pressed -> {
            if (simulationRunner == null) {
                simulationRunner = new SimulationRunner(this, acoAlgorithm, antEnvironment);
                simulationRunner.execute();
            }
            else {
                simulationRunner.resume();
            }
            startButton.setVisible(false);
            pauseButton.setVisible(true);
            statusPanel.updateStatus("Running");
            statusPanel.updateIterationCount();
        });

        pauseButton.addActionListener(pressed -> {
            simulationRunner.pause();
            pauseButton.setVisible(false);
            startButton.setVisible(true);
            statusPanel.updateStatus("Paused");
        });

        stopButton.addActionListener(pressed -> {
            if (simulationRunner != null) {
                simulationRunner.stop();
                simulationRunner = null;
                pauseButton.setVisible(false);
                startButton.setVisible(true);
                statusPanel.updateStatus("Stopped");
            }
        });

        skipButton.addActionListener(pressed -> {
            if (simulationRunner != null) {
                if (simulationRunner.isPaused()) {
                    simulationRunner.resume();
                    startButton.setVisible(false);
                    pauseButton.setVisible(true);
                }
                simulationRunner.skipIteration();
            }
        });

        GridBagConstraints layoutConstraints = new GridBagConstraints();
        // Panel Row 0
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        add(startButton, layoutConstraints);
        add(pauseButton, layoutConstraints);
        layoutConstraints.gridx = 1;
        add(stopButton, layoutConstraints);
        layoutConstraints.gridx = 2;
        add(skipButton, layoutConstraints);
    }

    private void generateButtons() {
        startButton = new UIButton("Start Simulation", "./res/ui-icons/play-button.png");
        pauseButton = new UIButton("Pause Simulation", "./res/ui-icons/pause-button.png");
        stopButton = new UIButton("Stop Simulation", "./res/ui-icons/stop-button.png");
        skipButton = new UIButton("Skip Iteration", "./res/ui-icons/skip-button.png");
        pauseButton.setVisible(false);
    }

    public void updateIterationNumber() {
        statusPanel.updateIterationCount();
    }

    public void updateAttemptNumber() {
        statusPanel.updateAttemptCount();
    }

    public void finishSimulation() {
        pauseButton.setVisible(false);
        startButton.setVisible(true);
        statusPanel.updateStatus("Finished");
        simulationRunner = null;
    }

    public SimulationRunner getSimulationRunner() {
        return simulationRunner;
    }

    public UIButton getStartButton() {
        return startButton;
    }

    public UIButton getPauseButton() {
        return pauseButton;
    }

    public UIButton getStopButton() {
        return stopButton;
    }

    public UIButton getSkipButton() {
        return skipButton;
    }
}
