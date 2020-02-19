package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestGoal {

    private Goal testGoal = new Goal(0, 0, 20, 30);

    @Test
    void testGetAndSetX() {
        Assert.assertEquals(testGoal.getX(), 0);
        testGoal.setX(120);
        Assert.assertEquals(testGoal.getX(), 120);
    }

    @Test
    void testGetAndSetY() {
        Assert.assertEquals(testGoal.getY(), 0);
        testGoal.setY(350);
        Assert.assertEquals(testGoal.getY(), 350);
    }

    @Test
    void testSetLocation() {
        testGoal.setLocation(240, 470);
        Assert.assertEquals(testGoal.getX(), 240);
        Assert.assertEquals(testGoal.getY(), 470);
    }

    @Test
    void testGetAndSetWidth() {
        Assert.assertEquals(testGoal.getWidth(), 20);
        testGoal.setWidth(30);
        Assert.assertEquals(testGoal.getWidth(), 30);
    }

    @Test
    void testGetAndSetHeight() {
        Assert.assertEquals(testGoal.getHeight(), 30);
        testGoal.setHeight(40);
        Assert.assertEquals(testGoal.getHeight(), 40);
    }
}
