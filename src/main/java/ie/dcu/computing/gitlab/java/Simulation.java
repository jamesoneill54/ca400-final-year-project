package ie.dcu.computing.gitlab.java;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Simulation {

    private static int animationDelay = 100;
    private static int minimumSimulationWidth = 4;
    private static int minimumSimulationHeight = 4;
    private AntColonyOptimisation acoAlgorithm;
    private AntEnvironment antEnvironment;
    private int environmentWidth;
    private int environmentHeight;
    private int numberOfAnts;
    private int numberOfObstacles;
    private JFrame simulationWindow;

    Simulation() throws IOException {
        // Menu displayed and asks user to set environmentWidth,
        // environmentHeight, and numberOfAnts.

        // environmentWidth and environmentHeight refers to the number of nodes
        // in the environment, not the screen size.
        environmentWidth = 40;
        environmentHeight = 40;
        numberOfAnts = 20;
        numberOfObstacles = 5;
        Node.setSize(10);
        acoAlgorithm = new AntColonyOptimisation(environmentWidth, environmentHeight, numberOfAnts);
        acoAlgorithm.setRunningAsVisualSimulation(true);
        antEnvironment = new AntEnvironment(acoAlgorithm, environmentWidth, environmentHeight);
    }

    public static void setAnimationDelay(int delay) {
        animationDelay = delay;
    }

    public static int getAnimationDelay() {
        return animationDelay;
    }

    public void start() throws IOException {
        displaySimulationWindow();
        acoAlgorithm.startOptimization(true);
        antEnvironment.stopSimulation();
        simulationWindow.dispose();
    }

    private void displaySimulationWindow() {
        simulationWindow = new JFrame();
        simulationWindow.getContentPane().add(antEnvironment);
        simulationWindow.setResizable(false);
        simulationWindow.setTitle("Ants");
        simulationWindow.setLocationRelativeTo(null);
        simulationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simulationWindow.pack();
        simulationWindow.setVisible(true);
    }

    public void setSimulationHome(int x, int y) {
        acoAlgorithm.setHome(x, y);
    }

    public void setSimulationGoal(int x, int y) {
        acoAlgorithm.setGoal(x, y);
    }

    public void setNumberOfObstacles(int number) {
        acoAlgorithm.setNumberOfObstacles(number);
        acoAlgorithm.generateObstacles();
    }

    public static void main(String[] args) throws IOException {
        Simulation simulation = new Simulation();
        simulation.setSimulationHome(20,20);
        simulation.setSimulationGoal(35, 10);
        simulation.setNumberOfObstacles(3);
        simulation.start();
    }
}
