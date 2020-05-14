package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.Ant;
import ie.dcu.computing.gitlab.java.AntColonyOptimisation;
import ie.dcu.computing.gitlab.java.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class AntEnvironment extends JPanel implements ActionListener {

    private int numColumns;
    private int numRows;
    private int environmentWidth;
    private int environmentHeight;
    private AntColonyOptimisation acoAlgorithm;
    private GroundPlane groundPlane;
    private int currentIteration = -1;
    private boolean optimumTrailDisplayed = false;
    private Timer timer;

    public AntEnvironment(AntColonyOptimisation acoAlgorithm) {
        this.acoAlgorithm = acoAlgorithm;
        this.numColumns = acoAlgorithm.getGraph()[0].length;
        this.numRows = acoAlgorithm.getGraph().length;
        this.environmentWidth = numColumns * Node.getSize();
        this.environmentHeight = numRows * Node.getSize();
        this.timer = new Timer(10, this);
        setPreferredSize(new Dimension(environmentWidth, environmentHeight));
        groundPlane = new GroundPlane(environmentWidth, environmentHeight, acoAlgorithm);
        timer.start();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (acoAlgorithm.getIterationNumber() != currentIteration || !optimumTrailDisplayed) {
            groundPlane.update();
            currentIteration = acoAlgorithm.getIterationNumber();
            if (acoAlgorithm.getPrecalculatedOptimumTour().size() > 0) {
                optimumTrailDisplayed = true;
            }
        }
        repaint();
    }

    public void updateGroundPlane() {
        groundPlane.update();
        repaint();
    }

    public void setOptimumTrailDisplayed(boolean value) {
        optimumTrailDisplayed = value;
    }

    public void updateDimensions() {
        numRows = acoAlgorithm.getGraph().length;
        numColumns = acoAlgorithm.getGraph()[0].length;
        this.environmentWidth = numColumns * Node.getSize();
        this.environmentHeight = numRows * Node.getSize();
        setPreferredSize(new Dimension(environmentWidth, environmentHeight));
        groundPlane = new GroundPlane(environmentWidth, environmentHeight, acoAlgorithm);
        updateGroundPlane();
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
