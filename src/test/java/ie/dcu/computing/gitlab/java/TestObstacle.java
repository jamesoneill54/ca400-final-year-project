package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestObstacle {

    private Obstacle testObstacle = new Obstacle(0, 0, 20, 30);

    @Test
    void testGetAndSetX() {
        Assert.assertEquals(testObstacle.getX(), 0);
        testObstacle.setX(120);
        Assert.assertEquals(testObstacle.getX(), 120);
    }

    @Test
    void testGetAndSetY() {
        Assert.assertEquals(testObstacle.getY(), 0);
        testObstacle.setY(350);
        Assert.assertEquals(testObstacle.getY(), 350);
    }

    @Test
    void testSetLocation() {
        testObstacle.setLocation(240, 470);
        Assert.assertEquals(testObstacle.getX(), 240);
        Assert.assertEquals(testObstacle.getY(), 470);
    }

    @Test
    void testGetAndSetWidth() {
        Assert.assertEquals(testObstacle.getWidth(), 20);
        testObstacle.setWidth(30);
        Assert.assertEquals(testObstacle.getWidth(), 30);
    }

    @Test
    void testGetAndSetHeight() {
        Assert.assertEquals(testObstacle.getHeight(), 30);
        testObstacle.setHeight(40);
        Assert.assertEquals(testObstacle.getHeight(), 40);
    }
}
