package ie.dcu.computing.gitlab.java;

import java.awt.*;
import java.util.Random;

public class NodeGroup {
    private NodeType nodeTypes;
    private int x;
    private int y;
    private int width;
    private int height;
    private int xBounds;
    private int yBounds;

    public NodeGroup(NodeType type, Node home, Node goal, Node[][] graph) {
        this.nodeTypes = type;
        this.xBounds = graph[0].length;
        this.yBounds = graph.length;
        generateRandomNodeGroup(home, goal);
    }

    public NodeGroup(NodeType type, int x, int y, int width, int height, Node[][] graph) {
        this.nodeTypes = type;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xBounds = graph[0].length;
        this.yBounds = graph.length;
    }

    private void generateRandomNodeGroup(Node home, Node goal) {
        randomiseDimensions();
        while (!isValid(home, goal)) {
            randomiseDimensions();
        }
        if (x + width > xBounds) {
            width = xBounds - x;
        }
        if (y + height > yBounds) {
            height = yBounds - y;
        }
    }

    private void randomiseDimensions() {
        Random random = new Random();
        x = random.nextInt(xBounds);
        y = random.nextInt(yBounds);
        width = 1 + random.nextInt(xBounds / 3);
        height = 1 + random.nextInt(yBounds / 3);
    }

    public void setNodesToType(Node[][] graph) {
        for (int row = y; row < y + height; row++) {
            for (int column = x; column < x + width; column++) {
                graph[row][column].setNodeAsObstacle();
            }
        }
    }

    public boolean isValid(Node home, Node goal) {
        boolean withinBounds = x + width < xBounds && y + height < yBounds;
        boolean xValidWithHome = home.getMatrixIndexX() < x || home.getMatrixIndexX() >= x + width;
        boolean yValidWithHome = home.getMatrixIndexY() < y || home.getMatrixIndexY() >= y + height;
        boolean xValidWithGoal = goal.getMatrixIndexX() < x || goal.getMatrixIndexX() >= x + width;
        boolean yValidWithGoal = goal.getMatrixIndexY() < y || goal.getMatrixIndexY() >= y + height;
        return withinBounds && (xValidWithHome || yValidWithHome) && (xValidWithGoal || yValidWithGoal);
    }

    public void drawGroup(Graphics graphics) {
        Color groupColor = Color.BLACK;
        if (nodeTypes == NodeType.GOAL) {
            groupColor = Color.GREEN;
        }
        draw(groupColor, graphics);
    }

    private void draw(Color groupColor, Graphics graphics) {
        graphics.setColor(groupColor);
        graphics.fillRect(x * Node.getSize(), y * Node.getSize(), width * Node.getSize(), height * Node.getSize());
    }

    public NodeType getNodeTypes() {
        return nodeTypes;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
