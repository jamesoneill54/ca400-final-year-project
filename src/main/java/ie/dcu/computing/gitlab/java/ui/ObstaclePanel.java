package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.AntColonyOptimisation;
import ie.dcu.computing.gitlab.java.NodeGroup;
import ie.dcu.computing.gitlab.java.NodeType;
import ie.dcu.computing.gitlab.java.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ObstaclePanel extends JScrollPane {

    private AntColonyOptimisation acoAlgorithm;
    private List<NodeGroup> obstacles;
    private ArrayList<ArrayList<UITextField>> obstacleValues;

    public ObstaclePanel(AntColonyOptimisation acoAlgorithm) {
        this.acoAlgorithm = acoAlgorithm;
        this.obstacles = acoAlgorithm.getObstacles();
        updateObstacles();
    }

    public void updateObstacles() {
        JPanel view = new JPanel();
        view.setLayout(new GridBagLayout());
        setViewportView(view);
        obstacleValues = new ArrayList<>();
        GridBagConstraints constraints = new GridBagConstraints();
        obstacles.removeIf(obstacle -> !obstacle.isValid(acoAlgorithm.getHome(), acoAlgorithm.getGoal()));
        for (int i = 0; i < obstacles.size(); i++) {
            JLabel obstacleNumber = new JLabel(String.valueOf(i));
            ArrayList<JLabel> labels = new ArrayList<>();
            ArrayList<UITextField> values = new ArrayList<>();
            labels.add(new JLabel(" | X: "));
            labels.add(new JLabel(" | Y: "));
            labels.add(new JLabel(" | Width: "));
            labels.add(new JLabel(" | Height: "));
            values.add(new UITextField(String.valueOf(obstacles.get(i).getX())));
            values.add(new UITextField(String.valueOf(obstacles.get(i).getY())));
            values.add(new UITextField(String.valueOf(obstacles.get(i).getWidth())));
            values.add(new UITextField(String.valueOf(obstacles.get(i).getHeight())));
            obstacleValues.add(values);
            JButton removeObstacleButton = new UIButton("-");
            removeObstacleButton.addActionListener(new DeleteObstacleActionListener(i));
            constraints.gridx = 0;
            constraints.gridy = i;
            view.add(obstacleNumber, constraints);
            constraints.gridx = 1;
            populateObstacleVariables(labels, values, constraints, view);
            view.add(removeObstacleButton, constraints);
        }
        view.revalidate();
    }

    private void populateObstacleVariables(ArrayList<JLabel> labels, ArrayList<UITextField> values, GridBagConstraints constraints, JPanel view) {
        for (int i = 0; i < labels.size(); i++) {
            view.add(labels.get(i), constraints);
            constraints.gridx++;
            view.add(values.get(i), constraints);
            constraints.gridx++;
        }
    }

    public void addObstacle() {
        obstacles.add(new NodeGroup(NodeType.OBSTACLE, acoAlgorithm.getHome(), acoAlgorithm.getGoal(), acoAlgorithm.getGraph()));
    }

    public void saveObstacles() {
        ArrayList<NodeGroup> newObstacles = new ArrayList<>();
        int i = 0;
        for (ArrayList<UITextField> values: obstacleValues) {
            int xVal = Integer.parseInt(values.get(0).getText());
            int yVal = Integer.parseInt(values.get(1).getText());
            int widthVal = Integer.parseInt(values.get(2).getText());
            int heightVal = Integer.parseInt(values.get(3).getText());
            NodeGroup newObstacle = new NodeGroup(NodeType.OBSTACLE, xVal, yVal, widthVal, heightVal, acoAlgorithm.getGraph());
            if (newObstacle.isValid(acoAlgorithm.getHome(), acoAlgorithm.getGoal())) {
                newObstacles.add(newObstacle);
            }
            else {
                Simulation.showDialog("Obstacle no." + i + " dimensions are invalid.");
            }
            i++;
        }
        acoAlgorithm.updateObstacles(newObstacles);
        obstacles = newObstacles;
    }

    private class DeleteObstacleActionListener implements ActionListener {

        private int obstacleIndex;

        DeleteObstacleActionListener(int obstacleIndex) {
            this.obstacleIndex = obstacleIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            obstacles.remove(obstacleIndex);
            updateObstacles();
        }
    }
}
