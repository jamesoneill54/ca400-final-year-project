package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.*;

public class TestHelloWorld {
    @Test
    void firstTest() {
        Assert.assertTrue(true);
    }

//    @Test
//    public void firstFail() {
//        Assert.fail();
//    }

    private HelloWorld testHelloAnt = new HelloWorld();

    @Test
    void classTest() {
        Assert.assertEquals("Hello, Ant!", testHelloAnt.getHello());
    }
}
