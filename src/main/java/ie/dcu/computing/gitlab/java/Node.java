package ie.dcu.computing.gitlab.java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Node {
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
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

    public Node getNodeObj() {
        return this;
    }

    public int getNodeNum() {
        return (this.getY() * AntEnvironment.ENVIRONMENT_WIDTH) + this.getX() +1 ;
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
}
