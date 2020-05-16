package ie.dcu.computing.gitlab.java;

import ie.dcu.computing.gitlab.java.ui.AntEnvironment;
import ie.dcu.computing.gitlab.java.ui.ScenariosPanel;
import ie.dcu.computing.gitlab.java.ui.UIButton;
import ie.dcu.computing.gitlab.java.ui.Report;
import ie.dcu.computing.gitlab.java.ui.UIContents;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Simulation {

    private static int animationDelay = 100;
    private static int minimumSimulationWidth = 4;
    private static int minimumSimulationHeight = 4;
    private static AntColonyOptimisation acoAlgorithm;
    private AntEnvironment antEnvironment;
    private static JFrame simulationOptionsWindow;
    private static JFrame simulationWindow;
    private static JFrame scenariosWindow;
    private static Integer simulationType;
    private static ArrayList<Report> reports;
    private JPanel contentPanel;

    Simulation() {
        Node.setSize(10);
        acoAlgorithm = new AntColonyOptimisation(40, 40, 20);
    }

    public static void setAnimationDelay(int delay) {
        animationDelay = delay;
    }

    public static int getAnimationDelay() {
        return animationDelay;
    }

    public void displaySimulationOptionsWindow() {
        simulationOptionsWindow = new JFrame();
        JPanel optionsPanel = new JPanel();
        simulationOptionsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UIButton visualSimulationButton = new UIButton("Visual Simulation");
        visualSimulationButton.addActionListener(pressed -> {
            simulationType = 0;
            simulationOptionsWindow.setVisible(false);
        });
        UIButton scenarioSimulationButton = new UIButton("Scenarios");
        scenarioSimulationButton.addActionListener(pressed -> {
            simulationType = 1;
            simulationOptionsWindow.setVisible(false);
        });
        JLabel title = new JLabel("Ant Colony Optimisation Simulation");
        title.setFont(new Font("Helvetica", Font.PLAIN, 20));

        GridBagConstraints constraints = new GridBagConstraints();
        optionsPanel.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        optionsPanel.add(title, constraints);
        constraints.gridy++;
        constraints.gridwidth = 1;
        optionsPanel.add(visualSimulationButton, constraints);
        constraints.gridx++;
        optionsPanel.add(scenarioSimulationButton, constraints);
        simulationOptionsWindow.add(optionsPanel);
        simulationOptionsWindow.setPreferredSize(new Dimension(350, 150));
        simulationOptionsWindow.setLocationRelativeTo(null);
        simulationOptionsWindow.pack();
        simulationOptionsWindow.setVisible(true);
    }

    private void displayScenariosWindow() {
        scenariosWindow = new JFrame();
        scenariosWindow.setResizable(true);
        scenariosWindow.setTitle("ACO Scenarios");
        ScenariosPanel scenariosPanel = new ScenariosPanel(acoAlgorithm);
        scenariosWindow.add(scenariosPanel);
        scenariosWindow.setLocationRelativeTo(null);
        scenariosWindow.pack();
        scenariosWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scenariosWindow.setVisible(true);
    }

    private void displayVisualSimulationWindow() throws IOException {
        acoAlgorithm.setRunningAsVisualSimulation(true);
        antEnvironment = new AntEnvironment(acoAlgorithm);
        simulationWindow = new JFrame();
        reports = new ArrayList<>();
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

    public static void showResultsFrame() {
        Report report = new Report(acoAlgorithm);
        reports.add(report);
        report.displayReportFrame();
    }

    public static void updateWindowSize() {
        JFrame window;
        if (simulationType == 0) {
            window = simulationWindow;
        }
        else {
            window = scenariosWindow;
        }
        int initialWidth = (int) window.getSize().getWidth();
        int initialHeight = (int) window.getSize().getHeight();
        window.pack();
        int width = (int) window.getSize().getWidth();
        int height = (int) window.getSize().getHeight();
        if (initialWidth != width || initialHeight != height) {
            window.setMinimumSize(new Dimension(width + 5, height + 5));
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Simulation simulation = new Simulation();
        simulation.displaySimulationOptionsWindow();
        while (simulationType == null) {
            Thread.sleep(100);
        }
        if (simulationType == 0) {
            simulation.displayVisualSimulationWindow();
        }
        else if (simulationType == 1) {
            simulation.displayScenariosWindow();
        }
    }
}
