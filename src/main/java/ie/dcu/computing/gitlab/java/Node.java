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
        IntStream.range(currentNode.getY() - 1, currentNode.getY() + 2)
                .forEach(i -> {
                    if (i >= 0 && i < matrix.length) {
                        IntStream.range(currentNode.getX() - 1, currentNode.getX() + 2)
                                .forEach(j -> {
                                    if (j >= 0 && j < matrix[i].length) {
                                        if (matrix[i][j] != currentNode) {
                                            neighbours.add(matrix[i][j]);
                                        }
                                    }
                                });
                    }
                });
        return neighbours;
    }
}
