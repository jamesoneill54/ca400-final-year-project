package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import java.awt.*;

public class TestAnt {
    private Ant testAnt = new Ant(50);

    @Test
    void trailSizeTest() {
        Assert.assertEquals("Trail size is equal to tourSize", testAnt.trailSize, 50);
    }

    @Test
    void initTaskTest() {
        Assert.assertEquals("Task string initialised with 'searcher'", testAnt.task, "searcher");
    }

    @Test
    void switchTaskTest() {
        testAnt.switchTask("tester");
        Assert.assertNotEquals("Task string no longer 'searcher' when switchTask() invoked", testAnt.task, "searcher");
    }

    @Test
    void testGetAndSetColor() {
        Assert.assertEquals(testAnt.getColor(), Color.BLUE);
        testAnt.setColor(Color.RED);
        Assert.assertEquals(testAnt.getColor(), Color.RED);
    }

    @Test
    void testGetAndSetX() {
        Assert.assertEquals(testAnt.getX(), 0);
        testAnt.setX(10);
        Assert.assertEquals(testAnt.getX(), 10);
    }

    @Test
    void testGetAndSetY() {
        Assert.assertEquals(testAnt.getY(), 0);
        testAnt.setY(20);
        Assert.assertEquals(testAnt.getY(), 20);
    }

    @Test
    void testGetWidth() {
        Assert.assertEquals(testAnt.getWidth(), 7);
    }

    @Test
    void testGetHeight() {
        Assert.assertEquals(testAnt.getHeight(), 7);
    }

    @Test
    void testUpdateLocation() {
        testAnt.updateLocation(20, 30);
        Assert.assertEquals(testAnt.getX(), 20);
        Assert.assertEquals(testAnt.getY(), 30);
    }
}
