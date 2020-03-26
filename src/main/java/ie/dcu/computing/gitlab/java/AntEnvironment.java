package ie.dcu.computing.gitlab.java;

import javax.swing.*;
import java.awt.*;

public class AntEnvironment extends JPanel implements Runnable {

    private int numColumns;
    private int numRows;
    private int environmentWidth;
    private int environmentHeight;
    private AntColonyOptimisation acoAlgorithm;

    private Thread animator;
    private long beforeTime = System.currentTimeMillis();
    private Obstacle obstacle;
    private boolean simulationRunning = false;

    public AntEnvironment(AntColonyOptimisation acoAlgorithm, int numColumns, int numRows) {
        this.acoAlgorithm = acoAlgorithm;
        this.numColumns = numColumns;
        this.numRows = numRows;
        this.environmentWidth = numColumns * Node.getSize();
        this.environmentHeight = numRows * Node.getSize();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(environmentWidth, environmentHeight));
        obstacle = new Obstacle(100, 100, 50, 60);
    }

    @Override
    public void addNotify() {
        super.addNotify();

        simulationRunning = true;
        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        for (Ant ant: acoAlgorithm.getAnts()) {
            ant.drawAnt(graphics);
        }
        acoAlgorithm.getGoal().drawNode(graphics);
        acoAlgorithm.getHome().drawNode(graphics);
        Toolkit.getDefaultToolkit().sync();
    }

    public void stopSimulation() {
        simulationRunning = false;
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (simulationRunning) {
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = Simulation.getAnimationDelay() - timeDiff;

            if (sleep < 0) {
                sleep = 0;
            }

            try {
                Thread.sleep(sleep);
            }
            catch (InterruptedException e) {
                String msg = String.format("Thread interrupted: %s", e.getMessage());

                JOptionPane.showMessageDialog(this, msg, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
        }
        setVisible(false);
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getEnvironmentWidth() {
        return environmentWidth;
    }

    public int getEnvironmentHeight() {
        return environmentHeight;
    }
}
