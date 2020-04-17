package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.AntColonyOptimisation;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {

    private final AntColonyOptimisation acoAlgorithm;
    private final UILabel status;
    private final UILabel iterationCount;
    private final UILabel attemptCount;
    private final UILabel aliveAntsCount;
    private final UILabel successfulAntsCount;
    private final UILabel unsuccessfulAntsCount;
    private final UILabel bestRouteLength;
    private final UILabel globalBestRouteLength;

    StatusPanel(AntColonyOptimisation acoAlgorithm) {
        this.acoAlgorithm = acoAlgorithm;
        status = new UILabel("Ready", "status");
        iterationCount = new UILabel("0/0", "status");
        attemptCount = new UILabel("0/0", "status");
        aliveAntsCount = new UILabel("0/0", "status");
        successfulAntsCount = new UILabel("0/0", "status");
        unsuccessfulAntsCount = new UILabel("0/0", "status");
        bestRouteLength = new UILabel("Not found", "status");
        globalBestRouteLength = new UILabel("Not found", "status");
        UILabel[] statuses = {status, iterationCount, attemptCount, aliveAntsCount,
                successfulAntsCount, unsuccessfulAntsCount, bestRouteLength, globalBestRouteLength};
        UILabel statusLabel = new UILabel("Status:", "identifier");
        UILabel iterationLabel = new UILabel("Iteration:", "identifier");
        UILabel attemptLabel = new UILabel("Attempt:", "identifier");
        UILabel aliveAntsLabel = new UILabel("Ants Currently Alive:", "identifier");
        UILabel successfulAntsLabel = new UILabel("Successful Ants:", "identifier");
        UILabel unsuccessfulAntsLabel = new UILabel("Unsuccessful Ants:", "identifier");
        UILabel bestRouteLengthLabel = new UILabel("Iteration Best Route:", "identifier");
        UILabel globalBestRouteLengthLabel = new UILabel("Overall Best Route:", "identifier");
        UILabel[] statusIdentifiers = {statusLabel, iterationLabel, attemptLabel, aliveAntsLabel,
                successfulAntsLabel, unsuccessfulAntsLabel, bestRouteLengthLabel, globalBestRouteLengthLabel};

        populatePanel(statusIdentifiers, statuses);
    }

    public void updateStatus(String newStatus) {
        /*
        * Current set of statuses:
        *   - "Ready"
        *   - "Running"
        *   - "Paused"
        *   - "Stopped"
        *   - "Finished"
        */
        switch (newStatus) {
            case "Ready":
            case "Finished":
                status.setBackground(Color.WHITE);
                break;
            case "Running":
                status.setBackground(Color.GREEN);
                break;
            case "Paused":
                status.setBackground(Color.YELLOW);
                break;
            case "Stopped":
                status.setBackground(Color.PINK);
                break;
            default:
                throw new IllegalArgumentException("Status '" + newStatus + "' not recognised.");
        }
        status.setText(newStatus);
    }

    public void updateIterationCount() {
        iterationCount.setText(this.acoAlgorithm.getIterationNumber() + "/" + this.acoAlgorithm.getMaxIterations());
    }

    public void updateAttemptCount() {
        attemptCount.setText(acoAlgorithm.getAttemptNumber() + "/" + acoAlgorithm.getMaxAttempts());
    }

    public void updateAntCounts() {
        aliveAntsCount.setText((acoAlgorithm.getNumberOfAnts() - acoAlgorithm.getStoppedAnts().size()) + "/" + acoAlgorithm.getNumberOfAnts());
        successfulAntsCount.setText(acoAlgorithm.getSuccesses() + "/" + acoAlgorithm.getNumberOfAnts());
        unsuccessfulAntsCount.setText((acoAlgorithm.getStoppedAnts().size() - acoAlgorithm.getSuccesses()) + "/" + acoAlgorithm.getNumberOfAnts());
    }

    public void updateBestRouteLengths() {
        if (acoAlgorithm.getBestTour() != null) {
            bestRouteLength.setText(String.valueOf(acoAlgorithm.getBestTour().size()));
            globalBestRouteLength.setText(String.valueOf(acoAlgorithm.getGlobalBestTour().size()));
        }
        else {
            bestRouteLength.setText("Not found");
            globalBestRouteLength.setText("Not found");
        }
    }

    private void populatePanel(UILabel[] statusIdentifiers, UILabel[] statuses) {
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        setLayout(new GridBagLayout());
        for (int i = 0; i < statusIdentifiers.length; i++) {
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = i;
            layoutConstraints.gridwidth = 1;
            add(statusIdentifiers[i], layoutConstraints);
            layoutConstraints.gridx = 1;
            add(statuses[i], layoutConstraints);
        }
    }

    public UILabel getStatus() {
        return status;
    }

    public UILabel getIterationCount() {
        return iterationCount;
    }

    public UILabel getAttemptCount() {
        return attemptCount;
    }

    public UILabel getAliveAntsCount() {
        return aliveAntsCount;
    }

    public UILabel getSuccessfulAntsCount() {
        return successfulAntsCount;
    }

    public UILabel getUnsuccessfulAntsCount() {
        return unsuccessfulAntsCount;
    }

    public UILabel getBestRouteLength() {
        return bestRouteLength;
    }

    public UILabel getGlobalBestRouteLength() {
        return globalBestRouteLength;
    }
}
