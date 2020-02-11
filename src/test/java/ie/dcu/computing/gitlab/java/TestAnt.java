package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.*;

public class TestAnt {
    private Ant testInsect = new Ant(50);

    @Test
    void trailSizeTest() {
        Assert.assertTrue("Trail size is equal to tourSize", testInsect.trailSize == 50);
    }

    @Test
    void initTaskTest() {
        Assert.assertTrue("Task string initialised with 'searcher'", testInsect.task == "searcher");
    }

    @Test
    void switchTaskTest() {
        testInsect.switchTask("tester");
        Assert.assertFalse("Task string no longer 'searcher' when switchTask() invoked", testInsect.task == "searcher");
    }
}
