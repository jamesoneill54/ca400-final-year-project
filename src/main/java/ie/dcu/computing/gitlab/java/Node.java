package ie.dcu.computing.gitlab.java;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Node {
    private static int numberOfNodes = 0;
    private int nodeNumber;
    private NodeType nodeType = NodeType.NORM;
    private int x;
    private int y;

    public Node(int x, int y) {
        numberOfNodes++;
        this.nodeNumber = numberOfNodes;
        this.x = x;
        this.y = y;
    }

    public static void resetNumberOfNodes() {
        Node.numberOfNodes = 0;
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
        int startY = currentNode.getY() - 1;
        int endY = currentNode.getY() + 1;
        int startX = currentNode.getX() - 1;
        int endX = currentNode.getX() + 1;

        for (int y = startY; y <= endY; y++) {
            if (y >= 0 && y < matrix.length) {
                for (int x = startX; x <= endX; x++) {
                    if (x >= 0 && x < matrix[y].length) {
                        neighbours.add(matrix[y][x]);
                    }
                }
            }
        }
        return neighbours;
    }

    public void drawNode(Graphics graphics) {
        if (nodeType == NodeType.HOME) {
            draw(graphics, Color.ORANGE);
        }
        else if (nodeType == NodeType.GOAL) {
            draw(graphics, Color.GREEN);
        }
        else {
            draw(graphics, Color.GRAY);
        }
    }

    private void draw(Graphics graphics, Color nodeColor) {
        graphics.setColor(nodeColor);
        graphics.drawRect(this.getX(), this.getY(), 1, 1);
        graphics.fillRect(this.getX(), this.getY(), 1, 1);
    }
}
