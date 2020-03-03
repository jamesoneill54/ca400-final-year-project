package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.stream.IntStream;

public class TestNode {

    private Node testNode = new Node(5, 8);

    Node[][] generateTestMatrix(int width, int height) {
        Node[][] m = new Node[width][height];
        IntStream.range(0, width)
                .forEach(i -> IntStream.range(0, height)
                        .forEach(j -> m[i][j] = new Node(j, i)));
        return m;
    }

    private Node[][] matrix = generateTestMatrix(10, 15);

    @Test
    void getXTest() {
        Assert.assertEquals("Correct X value returned", testNode.getX(), 5);
    }

    @Test
    void getYTest() {
        Assert.assertEquals("Correct Y value returned", testNode.getY(), 8);
    }

    @Test
    void setCoOrdTest() {
        testNode.setCoOrdinates(5, 6);
        Assert.assertNotEquals(testNode.getY(), 8);
        Assert.assertEquals(testNode.getY(), 6);
    }

    @Test
    void neighbourTest() {
        List<Node> neighbourList =testNode.getNeighbourNodes(matrix, testNode);
        Assert.assertEquals(neighbourList.get(0).getX(), 4);
        Assert.assertEquals(neighbourList.get(neighbourList.size()-1).getY(), 9);
        Assert.assertFalse(neighbourList.contains(testNode));
    }

}
