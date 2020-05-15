package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.AntColonyOptimisation;
import ie.dcu.computing.gitlab.java.Node;
import ie.dcu.computing.gitlab.java.NodeGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ConcurrentModificationException;
import java.util.List;

public class GroundPlane {

    private AntColonyOptimisation acoAlgorithm;
    private int width;
    private int height;
    protected BufferedImage groundImage;

    public GroundPlane(int width, int height, AntColonyOptimisation acoAlgorithm) {
        this.width = width;
        this.height = height;
        this.acoAlgorithm = acoAlgorithm;
        this.groundImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void update() {
        groundImage.flush();
        groundImage.getGraphics().setColor(Color.WHITE);
        groundImage.getGraphics().fillRect(0, 0, width, height);
        for (NodeGroup obstacle: acoAlgorithm.getObstacles()) {
            obstacle.drawGroup(groundImage.getGraphics());
        }
        try {
            for (Node node: acoAlgorithm.getTrailNodes()) {
                node.drawNode(groundImage.getGraphics());
            }
        }
        catch (ConcurrentModificationException e) {
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException interruptedException) {
                System.out.println("Ground Plane update interrupted: " + interruptedException.getMessage());
            }
            System.out.println("Ground plane update failed, retrying...");
            update();
        }
        for (Node node: acoAlgorithm.getPrecalculatedOptimumTour()) {
            node.drawNode(groundImage.getGraphics());
        }
        acoAlgorithm.getHome().drawNode(groundImage.getGraphics());
        acoAlgorithm.getGoal().drawNode(groundImage.getGraphics());
    }

    public BufferedImage getPlane() {
        BufferedImage groundPlane = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        groundPlane.getGraphics().drawImage(groundImage, 0, 0, null);
        return groundPlane;
    }
}
