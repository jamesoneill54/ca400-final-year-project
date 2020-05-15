package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.AntColonyOptimisation;

import javax.swing.*;
import java.awt.*;

public class UIContents extends JPanel {

    private static RunnerControlPanel runnerControlPanel;
    private static StatusPanel statusPanel;
    private static VariableControlPanel variableControlPanel;
    private static GridBagConstraints layoutConstraints = new GridBagConstraints();

    public UIContents(AntColonyOptimisation acoAlgorithm, AntEnvironment antEnvironment) {
        statusPanel = new StatusPanel(acoAlgorithm);
        runnerControlPanel = new RunnerControlPanel(acoAlgorithm, antEnvironment, statusPanel);
        variableControlPanel = new VariableControlPanel(acoAlgorithm, antEnvironment);
        setLayout(new GridBagLayout());

        JLabel title = new JLabel("Visual Simulation");
        title.setFont(new Font("Helvetica", Font.PLAIN, 20));

        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        layoutConstraints.gridwidth = 3;
        add(title, layoutConstraints);
        layoutConstraints.gridy = 1;
        layoutConstraints.gridwidth = 1;
        add(statusPanel, layoutConstraints);
        layoutConstraints.gridx = 1;
        add(antEnvironment, layoutConstraints);
        layoutConstraints.gridx = 2;
        add(variableControlPanel, layoutConstraints);
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 2;
        add(runnerControlPanel, layoutConstraints);
    }

    public static void updateIterationNumber() {
        statusPanel.updateIterationCount();
    }

    public static void updateAttemptNumber() {
        statusPanel.updateAttemptCount();
    }

    public static void updateAntCounts() {
        statusPanel.updateAntCounts();
    }

    public static void updateOptimumTourLength() {
        statusPanel.updateOptimumTourLength();
    }

    public static void updateBestTour() {
        statusPanel.updateBestRouteLengths();
    }

    public static RunnerControlPanel getRunnerControlPanel() {
        return runnerControlPanel;
    }

    public static StatusPanel getStatusPanel() {
        return statusPanel;
    }

    public static void lockVariableControls() {
        variableControlPanel.lock();
    }

    public static void unlockVariableControls() {
        variableControlPanel.unlock();
    }
}
