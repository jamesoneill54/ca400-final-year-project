package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.ui.GroundPlane;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

public class TestGroundPlane {

    @Test
    void getPlaneTest() {
        GroundPlane groundPlaneTest = new GroundPlane(5, 10, null);
        BufferedImage image = groundPlaneTest.getPlane();
        Assert.assertEquals(5, image.getWidth());
        Assert.assertEquals(10, image.getHeight());
    }
}
