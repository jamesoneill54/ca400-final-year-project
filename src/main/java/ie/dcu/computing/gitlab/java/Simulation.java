package ie.dcu.computing.gitlab.java;

import javax.swing.*;

public class Simulation {

    private static int animationDelay = 100;
    private static int minimumSimulationWidth = 4;
    private static int minimumSimulationHeight = 4;
    private AntColonyOptimisation acoAlgorithm;
    private AntEnvironment antEnvironment;
    private int environmentWidth;
    private int environmentHeight;
    private int numberOfAnts;

    Simulation() {
        // Menu displayed and asks user to set environmentWidth,
        // environmentHeight, and numberOfAnts.
        environmentWidth = 640;
        environmentHeight = 480;
        numberOfAnts = 50;
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

    public void start() {
        displaySimulationWindow();
        acoAlgorithm.startOptimization();
    }

    private void displaySimulationWindow() {
        JFrame simulationWindow = new JFrame();
        simulationWindow.getContentPane().add(antEnvironment);
        simulationWindow.setResizable(false);
        simulationWindow.pack();
        simulationWindow.setTitle("Ants");
        simulationWindow.setLocationRelativeTo(null);
        simulationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simulationWindow.setVisible(true);
    }

    public void setSimulationHome(int x, int y) {
        acoAlgorithm.setHome(x, y);
    }

    public void setSimulationGoal(int x, int y) {
        acoAlgorithm.setGoal(x, y);
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.setSimulationHome(2,2);
        simulation.setSimulationGoal(4, 3);
        simulation.start();
    }
}
