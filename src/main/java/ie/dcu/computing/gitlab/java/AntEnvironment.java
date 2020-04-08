package ie.dcu.computing.gitlab.java;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AntEnvironment extends JPanel implements Runnable {

    private int numColumns;
    private int numRows;
    private int environmentWidth;
    private int environmentHeight;
    private AntColonyOptimisation acoAlgorithm;
    private GroundPlane groundPlane;
    private int currentIteration = -1;

    private Thread animator;
    private boolean simulationRunning = false;

    public AntEnvironment(AntColonyOptimisation acoAlgorithm, int numColumns, int numRows) {
        this.acoAlgorithm = acoAlgorithm;
        this.numColumns = numColumns;
        this.numRows = numRows;
        this.environmentWidth = numColumns * Node.getSize();
        this.environmentHeight = numRows * Node.getSize();
        setPreferredSize(new Dimension(environmentWidth, environmentHeight));
        groundPlane = new GroundPlane(environmentWidth, environmentHeight, acoAlgorithm);
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

        BufferedImage environmentGround = groundPlane.getPlane();

        Node goal = acoAlgorithm.getGoal();
        for (Ant ant: acoAlgorithm.getAnts()) {
            if (ant.getX() != goal.getX() || ant.getY() != goal.getY()) {
                ant.drawAnt(environmentGround.getGraphics());
            }
        }
        graphics.drawImage(environmentGround, 0, 0, Color.WHITE, null);
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
            if (acoAlgorithm.getIterationNumber() != currentIteration) {
                groundPlane.update();
                currentIteration = acoAlgorithm.getIterationNumber();
            }
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
