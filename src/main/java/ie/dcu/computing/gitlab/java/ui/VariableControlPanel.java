package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VariableControlPanel extends JPanel {

    private final AntColonyOptimisation acoAlgorithm;
    private final AntEnvironment antEnvironment;
    private UIButton saveVariables;
    private UIButton addObstacleButton;
    private UITextField environmentWidth;
    private UITextField environmentHeight;
    private UITextField numberOfAnts;
    private UITextField pheromoneImportance;
    private UITextField distancePriority;
    private JSlider distancePrioritySlider;
    private JCheckBox createResultsCheckbox;
    UITextField homeX;
    UITextField homeY;
    UITextField goalX;
    UITextField goalY;
    private ObstaclePanel obstaclePanel;
    private final int dpMin = 1;
    private final int dpMax = 1000;
    private final int dpInit = 1;

    VariableControlPanel(AntColonyOptimisation acoAlgorithm, AntEnvironment antEnvironment) {
        this.acoAlgorithm = acoAlgorithm;
        this.antEnvironment = antEnvironment;
        generatePanel();
    }

    private void generatePanel() {
        environmentWidth = new UITextField(String.valueOf(acoAlgorithm.getGraph()[0].length));
        environmentHeight = new UITextField(String.valueOf(acoAlgorithm.getGraph().length));
        numberOfAnts = new UITextField(String.valueOf(acoAlgorithm.getAnts().size()));
        pheromoneImportance = new UITextField(String.valueOf(acoAlgorithm.getPheromoneImportance()));
        distancePriority = new UITextField(String.valueOf(acoAlgorithm.getDistancePriority()));
        distancePriority.setEditable(false);
        distancePrioritySlider = new JSlider(JSlider.HORIZONTAL, dpMin, dpMax, dpInit);
        distancePrioritySlider.addChangeListener(e -> distancePriority.setText(String.valueOf(distancePrioritySlider.getValue())));
        distancePrioritySlider.setMajorTickSpacing(100);
        distancePrioritySlider.setPaintTicks(true);
        createResultsCheckbox = new JCheckBox();
        createResultsCheckbox.setSelected(true);
        UILabel environmentWidthLabel = new UILabel("Environment Width:", "identifier");
        UILabel environmentHeightLabel = new UILabel("Environment Height:", "identifier");
        UILabel numberOfAntsLabel = new UILabel("Number of ants:", "identifier");
        UILabel pheromoneLabel = new UILabel("Pheromone Importance:", "identifier");
        UILabel distancePriorityLabel = new UILabel("Distance Priority:", "identifier");
        UILabel createResultsCheckboxLabel = new UILabel("Create Results:", "identifier");
        saveVariables = new UIButton("Save");
        saveVariables.addActionListener(event -> saveVariables());

        setLayout(new GridBagLayout());
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        add(environmentWidthLabel, layoutConstraints);
        layoutConstraints.gridx = 1;
        add(environmentWidth, layoutConstraints);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy++;
        add(environmentHeightLabel, layoutConstraints);
        layoutConstraints.gridx = 1;
        add(environmentHeight, layoutConstraints);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy++;
        add(numberOfAntsLabel, layoutConstraints);
        layoutConstraints.gridx = 1;
        add(numberOfAnts, layoutConstraints);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy++;
        add(pheromoneLabel, layoutConstraints);
        layoutConstraints.gridx = 1;
        add(pheromoneImportance, layoutConstraints);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy++;
        add(distancePriorityLabel, layoutConstraints);
        layoutConstraints.gridx = 1;
        add(distancePriority, layoutConstraints);
        layoutConstraints.gridx = 2;
        add(distancePrioritySlider, layoutConstraints);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy++;
        listHomeAndGoal(layoutConstraints);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy++;
        add(createResultsCheckboxLabel, layoutConstraints);
        layoutConstraints.gridx = 1;
        add(createResultsCheckbox, layoutConstraints);
        listObstacles(layoutConstraints);
        layoutConstraints.gridwidth = 3;
        layoutConstraints.gridy++;
        layoutConstraints.gridx = 0;
        add(saveVariables, layoutConstraints);
        validate();
    }

    public void updateVariables() {
        environmentWidth.setText(String.valueOf(acoAlgorithm.getGraph()[0].length));
        environmentHeight.setText(String.valueOf(acoAlgorithm.getGraph().length));
        numberOfAnts.setText(String.valueOf(acoAlgorithm.getAnts().size()));
        pheromoneImportance.setText(String.valueOf(acoAlgorithm.getPheromoneImportance()));
        distancePriority.setText(String.valueOf(acoAlgorithm.getDistancePriority()));
        homeX.setText(String.valueOf(acoAlgorithm.getHome().getMatrixIndexX()));
        homeY.setText(String.valueOf(acoAlgorithm.getHome().getMatrixIndexY()));
        goalX.setText(String.valueOf(acoAlgorithm.getGoal().getMatrixIndexX()));
        goalY.setText(String.valueOf(acoAlgorithm.getGoal().getMatrixIndexY()));
        obstaclePanel.updateObstacles();
    }

    private void listObstacles(GridBagConstraints layoutConstraints) {
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy += 1;
        layoutConstraints.gridwidth = 3;
        JLabel obstaclesTitle = new JLabel("Obstacles");
        obstaclesTitle.setFont(new Font("Helvetica", Font.PLAIN, 18));
        obstaclePanel = new ObstaclePanel(acoAlgorithm);
        add(obstaclesTitle, layoutConstraints);
        layoutConstraints.gridx = 2;
        layoutConstraints.gridy += 1;
        layoutConstraints.gridwidth = 1;
        addObstacleButton = new UIButton("+");
        addObstacleButton.addActionListener(event -> {
            obstaclePanel.addObstacle();
            obstaclePanel.updateObstacles();
            if (obstaclePanel.getObstacles().size() <= 3) {
                Simulation.updateWindowSize();
            }
        });
        add(addObstacleButton, layoutConstraints);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy += 1;
        layoutConstraints.gridwidth = 3;
        add(obstaclePanel, layoutConstraints);
    }

    public void listHomeAndGoal(GridBagConstraints constraints) {
        UILabel homeXLabel = new UILabel("Home Node X: ", "identifier");
        UILabel homeYLabel = new UILabel("Home Node Y: ", "identifier");
        UILabel goalXLabel = new UILabel("Goal Node X: ", "identifier");
        UILabel goalYLabel = new UILabel("Goal Node Y: ", "identifier");
        homeX = new UITextField(String.valueOf(acoAlgorithm.getHome().getMatrixIndexX()));
        homeY = new UITextField(String.valueOf(acoAlgorithm.getHome().getMatrixIndexY()));
        goalX = new UITextField(String.valueOf(acoAlgorithm.getGoal().getMatrixIndexX()));
        goalY = new UITextField(String.valueOf(acoAlgorithm.getGoal().getMatrixIndexY()));
        add(homeXLabel, constraints);
        constraints.gridx++;
        add(homeX, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        add(homeYLabel, constraints);
        constraints.gridx++;
        add(homeY, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        add(goalXLabel, constraints);
        constraints.gridx++;
        add(goalX, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        add(goalYLabel, constraints);
        constraints.gridx++;
        add(goalY, constraints);
    }

    public void lock() {
        pheromoneImportance.setEditable(false);
        distancePrioritySlider.setEnabled(false);
        saveVariables.setEnabled(false);
    }

    public void unlock() {
        pheromoneImportance.setEditable(true);
        distancePrioritySlider.setEnabled(true);
        saveVariables.setEnabled(true);
    }

    public void saveVariables() {
        acoAlgorithm.setEnvironmentSize(Integer.parseInt(environmentWidth.getText()), Integer.parseInt(environmentHeight.getText()));
        antEnvironment.updateDimensions();
        acoAlgorithm.generateAnts(Integer.parseInt(numberOfAnts.getText()));
        acoAlgorithm.setPheromoneImportance(Double.parseDouble(pheromoneImportance.getText()));
        acoAlgorithm.setDistancePriority(Integer.parseInt(distancePriority.getText()));
        acoAlgorithm.setHome(Integer.parseInt(homeX.getText()), Integer.parseInt(homeY.getText()));
        acoAlgorithm.setGoal(Integer.parseInt(goalX.getText()), Integer.parseInt(goalY.getText()));
        obstaclePanel.saveObstacles();
        acoAlgorithm.setCreateResults(createResultsCheckbox.isSelected());
        updateVariables();
        antEnvironment.updateGroundPlane();
        Simulation.updateWindowSize();
    }
}
