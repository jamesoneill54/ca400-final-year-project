package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.awt.*;

public class TestAnimationAnt {

    public AnimationAnt testAnt = new AnimationAnt();

    @Test
    void testAntInit() {
        AnimationAnt testAnt0 = new AnimationAnt(Color.BLUE, 1, 2);
        Assert.assertEquals(testAnt0.getClass(), AnimationAnt.class);
        Assert.assertEquals(testAnt0.getColor(), Color.BLUE);
        Assert.assertEquals(testAnt0.getX(), 1);
        Assert.assertEquals(testAnt0.getY(), 2);

        AnimationAnt testAnt1 = new AnimationAnt(Color.RED);
        Assert.assertEquals(testAnt1.getClass(), AnimationAnt.class);
        Assert.assertEquals(testAnt1.getColor(), Color.RED);
        Assert.assertEquals(testAnt1.getX(), 0);
        Assert.assertEquals(testAnt1.getY(), 0);

        AnimationAnt testAnt2 = new AnimationAnt(4, 5);
        Assert.assertEquals(testAnt2.getClass(), AnimationAnt.class);
        Assert.assertEquals(testAnt2.getColor(), Color.BLUE);
        Assert.assertEquals(testAnt2.getX(), 4);
        Assert.assertEquals(testAnt2.getY(), 5);

        AnimationAnt testAnt3 = new AnimationAnt();
        Assert.assertEquals(testAnt3.getClass(), AnimationAnt.class);
        Assert.assertEquals(testAnt3.getColor(), Color.BLUE);
        Assert.assertEquals(testAnt3.getX(), 0);
        Assert.assertEquals(testAnt3.getY(), 0);
    }

    @Test
    void testSetColor() {
        Assert.assertEquals(testAnt.getColor(), Color.BLUE);
        testAnt.setColor(Color.RED);
        Assert.assertEquals(testAnt.getColor(), Color.RED);
    }

    @Test
    void testSetX() {
        Assert.assertEquals(testAnt.getX(), 0);
        testAnt.setX(10);
        Assert.assertEquals(testAnt.getX(), 10);
    }

    @Test
    void testSetY() {
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
}
