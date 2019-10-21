package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.*;

public class TestHelloWorld {
    @Test
    public void firstTest() {
        Assert.assertTrue(true);
    }

    @Test
    public void firstFail() {
        Assert.assertTrue(false);
    }
}
