package ie.dcu.computing.gitlab.java;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
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
        System.out.println("Painting ground");
        groundImage.getGraphics().setColor(Color.WHITE);
        groundImage.getGraphics().fillRect(0, 0, width, height);
        acoAlgorithm.getHome().drawNode(groundImage.getGraphics());
        acoAlgorithm.getGoal().drawNode(groundImage.getGraphics());
        for (NodeGroup obstacle: acoAlgorithm.getObstacles()) {
            obstacle.drawGroup(groundImage.getGraphics());
        }
        for (Node node: acoAlgorithm.getTrailNodes()) {
            System.out.println("Drawing pheromones");
            node.drawNode(groundImage.getGraphics());
        }
    }

    public BufferedImage getPlane() {
        BufferedImage groundPlane = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        groundPlane.getGraphics().drawImage(groundImage, 0, 0, null);
        return groundPlane;
    }
}
