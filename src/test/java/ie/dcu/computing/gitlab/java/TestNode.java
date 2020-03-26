package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.stream.IntStream;

public class TestNode {

    private Node testNode = new Node(5, 8);

    Node[][] generateTestMatrix(int width, int height) {
        Node[][] m = new Node[width][height];
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                m[y][x] = new Node(x, y);
            }
        }
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
        List<Node> neighbourList = testNode.getNeighbourNodes(matrix);
        Assert.assertEquals(neighbourList.get(0).getX(), 4);
        Assert.assertEquals(neighbourList.get(neighbourList.size()-1).getY(), 9);
        Assert.assertFalse(neighbourList.contains(testNode));
    }

    @Test
    void resetNumberOfNodesTest() {
        Node testNode1 = new Node(0, 0);
        Assert.assertNotEquals(1, testNode1.getNodeNum());
        Node.resetNumberOfNodes();
        Node testNode2 = new Node(0, 0);
        Assert.assertEquals(1, testNode2.getNodeNum());
    }

    @Test
    void setNodeAsHomeTest() {
        testNode.setNodeAsHome();
        Assert.assertEquals(testNode.getNodeType(), NodeType.HOME);
    }

    @Test
    void setNodeAsGoalTest() {
        testNode.setNodeAsGoal();
        Assert.assertEquals(testNode.getNodeType(), NodeType.GOAL);
    }

    @Test
    void nodeSetAsNormTest() {
        Node testNode3 = new Node(10, 10);
        Assert.assertEquals(testNode3.getNodeType(), NodeType.NORM);
    }
}
