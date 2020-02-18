package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestAnthill {

    private Anthill testAnthill = new Anthill(0, 0, 20, 30);

    @Test
    void testGetAndSetX() {
        Assert.assertEquals(testAnthill.getX(), 0);
        testAnthill.setX(120);
        Assert.assertEquals(testAnthill.getX(), 120);
    }

    @Test
    void testGetAndSetY() {
        Assert.assertEquals(testAnthill.getY(), 0);
        testAnthill.setY(350);
        Assert.assertEquals(testAnthill.getY(), 350);
    }

    @Test
    void testSetLocation() {
        testAnthill.setLocation(240, 470);
        Assert.assertEquals(testAnthill.getX(), 240);
        Assert.assertEquals(testAnthill.getY(), 470);
    }

    @Test
    void testGetAndSetWidth() {
        Assert.assertEquals(testAnthill.getWidth(), 20);
        testAnthill.setWidth(30);
        Assert.assertEquals(testAnthill.getWidth(), 30);
    }

    @Test
    void testGetAndSetHeight() {
        Assert.assertEquals(testAnthill.getHeight(), 30);
        testAnthill.setHeight(40);
        Assert.assertEquals(testAnthill.getHeight(), 40);
    }
}
