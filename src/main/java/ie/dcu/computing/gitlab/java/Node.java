package ie.dcu.computing.gitlab.java;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Node {
    private static int numberOfNodes = 0;
    private static int size = 1;
    private int nodeNumber;
    private NodeType nodeType = NodeType.NORM;
    private int matrixIndexX;
    private int matrixIndexY;
    private int x;
    private int y;

    protected double pheromoneCount;

    public Node(int x, int y) {
        numberOfNodes++;
        this.nodeNumber = numberOfNodes;
        this.matrixIndexX = x;
        this.matrixIndexY = y;
        this.x = x * size;
        this.y = y * size;
        this.pheromoneCount = 1;
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

    public NodeType getNodeType() {
        return nodeType;
    }

    public int getNodeNum() {
        return nodeNumber;
    }

    public void printNode() {
        System.out.print("(" + this.getX() + ", " + this.getY() + ")");
    }

    public List<Node> getNeighbourNodes(Node[][] matrix, Node currentNode) {
        List<Node> neighbours = new ArrayList<>();
        int startY = matrixIndexY - 1;
        int endY = matrixIndexY + 1;
        int startX = matrixIndexX - 1;
        int endX = matrixIndexX + 1;

        for (int y = startY; y <= endY; y++) {
            if (y >= 0 && y < matrix.length) {
                for (int x = startX; x <= endX; x++) {
                    if (x >= 0 && x < matrix[y].length) {
                        if (matrix[y][x] != currentNode) {
                            neighbours.add(matrix[y][x]);
                        }
                    }
                }
            }
        }
        return neighbours;
    }

    public int getDistanceValue (Node goalNode) {
        int distanceValue = 0;
        distanceValue += Math.abs(this.getX() - goalNode.getX());
        distanceValue += Math.abs(this.getY() - goalNode.getY());
        return distanceValue;
    }

    public void drawNode(Graphics graphics) {
        if (nodeType == NodeType.HOME) {
            draw(graphics, Color.RED);
        }
        else if (nodeType == NodeType.GOAL) {
            draw(graphics, Color.GREEN);
        }
        else {
            draw(graphics, Color.GRAY);
        }
    }

    @Override
    public String toString() {
        return "(" + nodeType + ", " + x + ", " + y + ")";
    }

    private void draw(Graphics graphics, Color nodeColor) {
        graphics.setColor(nodeColor);
        graphics.drawRect(this.getX(), this.getY(), size, size);
        graphics.fillRect(this.getX(), this.getY(), size, size);
    }
}
