package ie.dcu.computing.gitlab.java;

import javax.swing.*;
import java.awt.*;

public class AntEnvironment extends JPanel implements Runnable {

    protected final int DELAY = 20;
    private static final int ENVIRONMENT_WIDTH = 640;
    private static final int ENVIRONMENT_HEIGHT = 480;

    protected Thread animator;
    // Should be changed to an array of ants for future updates
    private Ant ant;
    // Should be changed to an array of obstacles for future updates
    private Obstacle obstacle;
    // Should be changed to an array of goals for future updates
    private Goal goal;
    // Can be an array if we choose to implement multiple colonies
    private Anthill anthill;

    public AntEnvironment() {
        initEnvironment();
    }

    private void initEnvironment() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(ENVIRONMENT_WIDTH, ENVIRONMENT_HEIGHT));
        ant = new Ant(0);
        obstacle = new Obstacle(100, 100, 50, 60);
        goal = new Goal(500, 200, 10, 10);
        anthill = new Anthill(200, 300, 10, 10);
    }

    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        ant.drawAnt(graphics);
        obstacle.draw(graphics);
        goal.draw(graphics);
        anthill.draw(graphics);
        Toolkit.getDefaultToolkit().sync();
    }

    private void step() {

        // This is where the ant's x and y variables should be updated.
        // For every ant, update x and y.

        // The code present here should be removed once the ant's coordinates
        // can be derived from the ACO algorithm.

        int antX = ant.getX() + 2;
        int antY = ant.getY() + 2;

        if (antY > ENVIRONMENT_HEIGHT) {
            antX = 0;
            antY = 0;
        }

        ant.updateLocation(antX, antY);
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while(true) {
            step();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
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
    }

    public Ant getAnt() {
        return ant;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public Goal getGoal() {
        return goal;
    }

    public Anthill getAnthill() {
        return anthill;
    }

    public int getEnvironmentWidth() {
        return ENVIRONMENT_WIDTH;
    }

    public int getEnvironmentHeight() {
        return ENVIRONMENT_HEIGHT;
    }
}
