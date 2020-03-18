package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.*;


public class TestAntColonyOptimisation {
    private AntColonyOptimisation testACO = new AntColonyOptimisation(2, 1);

    @Test
    void generateMatrixTest() {
        Node[][] graph = testACO.generateMatrixFromEnv(2, 1);
        Assert.assertEquals(graph[0][0].getNodeNum(), 1);
    }

    @Test
    void preTourTest() {
        Assert.assertNull("Best tour length is null", testACO.bestTourOrder);
    }

    @Test
    void postTourTest() {
        testACO.setHome(0, 0);
        testACO.setGoal(1, 0);
        testACO.startOptimization();
        Assert.assertNotNull("Best tour length is not null", testACO.bestTourOrder);
    }
}
