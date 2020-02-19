package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.*;

public class TestNode {

    private Node testNode = new Node(5, 8);

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
}
