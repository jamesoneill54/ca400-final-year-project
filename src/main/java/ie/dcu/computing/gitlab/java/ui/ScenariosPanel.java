package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.AntColonyOptimisation;
import ie.dcu.computing.gitlab.java.NodeGroup;
import ie.dcu.computing.gitlab.java.Scenario;
import ie.dcu.computing.gitlab.java.Simulation;

import javax.swing.*;
import java.awt.*;

public class ScenariosPanel extends JPanel {

    private AntColonyOptimisation acoAlgorithm;
    private UITextField environmentSizeValue;
    private UITextField homeXValue;
    private UITextField homeYValue;
    private UITextField goalXValue;
    private UITextField goalYValue;
    private ObstaclePanel obstaclePanel;
    private UIButton addObstacleButton;
    private UIButton runScenarioButton;
    private UIButton stopScenarioButton;
    private ScenarioRunner scenarioRunner;
    private JTextArea status;

    public ScenariosPanel(AntColonyOptimisation acoAlgorithm) {
        this.acoAlgorithm = acoAlgorithm;
        setMinimumSize(new Dimension(400, 400));
        JLabel title = new JLabel("Scenarios");
        title.setFont(new Font("Helvetica", Font.PLAIN, 20));
        UILabel environmentSizeLabel = new UILabel("Environment Size:", "identifier");
        UILabel homeXLabel = new UILabel("Home X:", "identifier");
        UILabel homeYLabel = new UILabel("Home Y:", "identifier");
        UILabel goalXLabel = new UILabel("Goal X:", "identifier");
        UILabel goalYLabel = new UILabel("Goal Y:", "identifier");
        UILabel[] labels = {environmentSizeLabel, homeXLabel, homeYLabel, goalXLabel, goalYLabel};
        environmentSizeValue = new UITextField(String.valueOf(acoAlgorithm.getGraph().length));
        homeXValue = new UITextField(String.valueOf(acoAlgorithm.getHome().getMatrixIndexX()));
        homeYValue = new UITextField(String.valueOf(acoAlgorithm.getHome().getMatrixIndexY()));
        goalXValue = new UITextField(String.valueOf(acoAlgorithm.getGoal().getMatrixIndexX()));
        goalYValue = new UITextField(String.valueOf(acoAlgorithm.getGoal().getMatrixIndexY()));
        UITextField[] values = {environmentSizeValue, homeXValue, homeYValue, goalXValue, goalYValue};
        JLabel obstaclesTitle = new JLabel("Obstacles");
        obstaclesTitle.setFont(new Font("Helvetica", Font.PLAIN, 18));
        obstaclePanel = new ObstaclePanel(acoAlgorithm);
        addObstacleButton = new UIButton("+");
        addObstacleButton.addActionListener(event -> {
            obstaclePanel.addObstacle();
            obstaclePanel.updateObstacles();
            if (obstaclePanel.getObstacles().size() <= 3) {
                Simulation.updateWindowSize();
            }
        });
        runScenarioButton = new UIButton("Run Scenario");
        runScenarioButton.addActionListener(pressed -> runScenario());
        stopScenarioButton = new UIButton("Stop Scenario");
        stopScenarioButton.setVisible(false);
        stopScenarioButton.addActionListener(pressed -> stopScenario());
        status = new JTextArea("Ready");
        status.setColumns(60);
        status.setRows(5);

        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 5;
        add(title, constraints);
        constraints.gridwidth = 1;
        for (int i = 0; i < labels.length; i++) {
            constraints.gridx = 0;
            constraints.gridy++;
            add(labels[i], constraints);
            constraints.gridx++;
            add(values[i], constraints);
        }
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 3;
        add(obstaclesTitle, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 1;
        add(addObstacleButton, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 3;
        add(obstaclePanel, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 1;
        add(runScenarioButton, constraints);
        add(stopScenarioButton, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 3;
        add(status, constraints);
    }

    public void runScenario() {
        obstaclePanel.saveObstacles();
        int[][] obstacles = new int[obstaclePanel.getObstacles().size()][];
        for (int i = 0; i < obstaclePanel.getObstacles().size(); i++) {
            NodeGroup obstacle = obstaclePanel.getObstacles().get(i);
            obstacles[i] = new int[] {obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight()};
        }
        Scenario scenario = new Scenario(acoAlgorithm, Integer.parseInt(environmentSizeValue.getText()),
                new int[] {Integer.parseInt(homeXValue.getText()), Integer.parseInt(homeYValue.getText())},
                new int[] {Integer.parseInt(goalXValue.getText()), Integer.parseInt(goalYValue.getText())},
                obstacles);
        scenario.setScenariosPanel(this);
        scenarioRunner = new ScenarioRunner(this, scenario);
        scenarioRunner.execute();
        runScenarioButton.setVisible(false);
        stopScenarioButton.setVisible(true);
    }

    public void finishRunningScenario() {
        scenarioRunner = null;
        stopScenarioButton.setVisible(false);
        runScenarioButton.setVisible(true);
    }

    public void stopScenario() {
        if (scenarioRunner != null) {
            scenarioRunner.stop();
        }
        scenarioRunner = null;
        stopScenarioButton.setVisible(false);
        runScenarioButton.setVisible(true);
    }

    public ScenarioRunner getScenarioRunner() {
        return scenarioRunner;
    }

    public void updateStatus(String message) {
        status.setText(message);
    }
}
