package ie.dcu.computing.gitlab.java;

import javax.swing.*;
import java.awt.*;

public class AntEnvironment extends JPanel implements Runnable {

    private int environmentWidth;
    private int environmentHeight;
    private AntColonyOptimisation acoAlgorithm;

    private Thread animator;
    private long beforeTime = System.currentTimeMillis();
    // Should be changed to an array of obstacles for future updates
    private Obstacle obstacle;
    // Should be changed to an array of goals for future updates

    public AntEnvironment(AntColonyOptimisation acoAlgorithm, int environmentWidth, int environmentHeight) {
        this.acoAlgorithm = acoAlgorithm;
        this.environmentWidth = environmentWidth;
        this.environmentHeight = environmentHeight;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(environmentWidth, environmentHeight));
        obstacle = new Obstacle(100, 100, 50, 60);
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

        for (Ant ant: acoAlgorithm.getAnts()) {
            ant.drawAnt(graphics);
        }
        //obstacle.draw(graphics);
        acoAlgorithm.getGoal().drawNode(graphics);
        acoAlgorithm.getHome().drawNode(graphics);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while(true) {
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
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public int getEnvironmentWidth() {
        return environmentWidth;
    }

    public int getEnvironmentHeight() {
        return environmentHeight;
    }
}
