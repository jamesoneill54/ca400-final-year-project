package ie.dcu.computing.gitlab.java;

import ie.dcu.computing.gitlab.java.ui.AntEnvironment;
import ie.dcu.computing.gitlab.java.ui.UIContents;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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
    private static JFrame simulationWindow;
    private JPanel contentPanel;

    Simulation() {
        environmentWidth = 40;
        environmentHeight = 40;
        numberOfAnts = 20;
        numberOfObstacles = 5;
        Node.setSize(10);
        acoAlgorithm = new AntColonyOptimisation(environmentWidth, environmentHeight, numberOfAnts);
        acoAlgorithm.setCreateResults(false);
        acoAlgorithm.setRunningAsVisualSimulation(true);
        antEnvironment = new AntEnvironment(acoAlgorithm);
    }

    public static void setAnimationDelay(int delay) {
        animationDelay = delay;
    }

    public static int getAnimationDelay() {
        return animationDelay;
    }

    private void displaySimulationWindow() throws IOException {
        simulationWindow = new JFrame();
        simulationWindow.add(new UIContents(acoAlgorithm, antEnvironment));
        simulationWindow.setResizable(true);
        simulationWindow.setMinimumSize(new Dimension(250, 250));
        simulationWindow.setTitle("ACO Simulation");
        BufferedImage image = ImageIO.read(new File("./res/ui-icons/favicon-ant.png"));
        simulationWindow.setIconImage(image);
        simulationWindow.setLocationRelativeTo(null);
        simulationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simulationWindow.setVisible(true);
        simulationWindow.pack();
        int width = (int) simulationWindow.getSize().getWidth();
        int height = (int) simulationWindow.getSize().getHeight();
        simulationWindow.setMinimumSize(new Dimension(width + 5, height + 5));
    }

    public static void showDialog(String message) {
        JOptionPane.showMessageDialog(simulationWindow, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public static void updateWindowSize() {
        int initialWidth = (int) simulationWindow.getSize().getWidth();
        int initialHeight = (int) simulationWindow.getSize().getHeight();
        simulationWindow.pack();
        int width = (int) simulationWindow.getSize().getWidth();
        int height = (int) simulationWindow.getSize().getHeight();
        if (initialWidth != width || initialHeight != height) {
            simulationWindow.setMinimumSize(new Dimension(width + 5, height + 5));
        }
    }

    public static void main(String[] args) throws IOException {
        Simulation simulation = new Simulation();
        simulation.displaySimulationWindow();
    }
}
