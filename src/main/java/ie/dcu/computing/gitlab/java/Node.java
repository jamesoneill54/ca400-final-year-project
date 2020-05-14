package ie.dcu.computing.gitlab.java;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Node {
    private static int numberOfNodes = 0;
    private static int size = 1;
    private int nodeNumber;
    private NodeType nodeType = NodeType.STANDARD;
    private int matrixIndexX;
    private int matrixIndexY;
    private int x;
    private int y;
    private ArrayList<Node> children;
    private boolean isPartOfOptimal = false;
    private Node parent;
    private LinkedHashSet<Node> descendants;

    protected double pheromoneCount;

    public Node(int x, int y) {
        numberOfNodes++;
        this.nodeNumber = numberOfNodes;
        this.matrixIndexX = x;
        this.matrixIndexY = y;
        this.x = x * size;
        this.y = y * size;
        this.pheromoneCount = 1;
        this.children = new ArrayList<>();
        this.parent = null;
        this.descendants = new LinkedHashSet<>();
        this.descendants.add(this);
    }

    public int getMatrixIndexX() {
        return matrixIndexX;
    }

    public int getMatrixIndexY() {
        return matrixIndexY;
    }

    public static void resetNumberOfNodes() {
        Node.numberOfNodes = 0;
    }

    public static void setSize(int length) {
        Node.size = length;
    }

    public static int getSize() {
        return size;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setCoOrdinates(int a, int b) {
        this.x = a;
        this.y = b;
    }

    public void setNodeAsHome() {
        nodeType = NodeType.HOME;
    }

    public void setNodeAsGoal() {
        nodeType = NodeType.GOAL;
    }

    public void setNodeAsStandard() {
        nodeType = NodeType.STANDARD;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeAsObstacle() {
        nodeType = NodeType.OBSTACLE;
    }

    public int getNodeNum() {
        return nodeNumber;
    }

    public void printNode() {
        System.out.print("(" + this.getMatrixIndexX() + ", " + this.getMatrixIndexY() + ")");
    }

    public void setAsPartOfOptimal() {
        isPartOfOptimal = true;
    }

    public List<Node> getNeighbourNodes(Node[][] matrix) {
        List<Node> neighbours = new ArrayList<Node>(){};
        int startY = matrixIndexY - 1;
        int endY = matrixIndexY + 1;
        int startX = matrixIndexX - 1;
        int endX = matrixIndexX + 1;

        for (int y = startY; y <= endY; y++) {
            if (y >= 0 && y < matrix.length) {
                for (int x = startX; x <= endX; x++) {
                    if (x >= 0 && x < matrix[y].length) {
                        if (matrix[y][x] != this && matrix[y][x].getNodeType() != NodeType.OBSTACLE) {
                            neighbours.add(matrix[y][x]);
                        }
                    }
                }
            }
        }
        return neighbours;
    }

    public void addChild(Node child) {
        this.children.add(child);
        child.parent = this;
    }

    public LinkedHashSet<Node> getDescendants(Node home) {
        if (this == home) {
            return this.descendants;
        }
        this.descendants.add(parent);
        this.descendants.addAll(parent.getDescendants(home));
        return this.descendants;
    }

    public void printDescendants() {
        for (Node desc : this.descendants) {
            desc.printNode();
        }
    }

    public int getDistanceValue(Node homeNode) {
        int distanceValue = numberOfNodes;
        distanceValue -= Math.abs(this.getX() - homeNode.getX());
        distanceValue -= Math.abs(this.getY() - homeNode.getY());
        return distanceValue;
    }

    public void drawNode(Graphics graphics) {
        if (nodeType == NodeType.HOME) {
            draw(graphics, Color.RED);
        }
        else if (nodeType == NodeType.GOAL) {
            draw(graphics, Color.MAGENTA);
        }
        else if (nodeType == NodeType.OBSTACLE) {
            draw(graphics, Color.BLACK);
        }
        else {
            int redAndBlueValue = 255 - (int) (pheromoneCount);
            if (redAndBlueValue < 0) {
                redAndBlueValue = 0;
            }
            Color pheromoneColor = new Color(redAndBlueValue, 255, redAndBlueValue);
            draw(graphics, pheromoneColor);
        }
    }

    @Override
    public String toString() {
        return "(" + this.getMatrixIndexX() + ", " + this.getMatrixIndexY() + ")";
    }

    private void draw(Graphics graphics, Color nodeColor) {
        graphics.setColor(nodeColor);
        graphics.fillRect(this.getX(), this.getY(), size, size);
        if (isPartOfOptimal) {
            graphics.setColor(Color.BLACK);
            graphics.drawRect(this.getX(), this.getY(), size, size);
        }
    }
}
