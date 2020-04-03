package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.*;


public class TestAntColonyOptimisation {
    private AntColonyOptimisation testACO = new AntColonyOptimisation(2, 5, null);

    @Test
    void generateMatrixTest() {
        Node[][] graph = testACO.generateMatrixFromEnv(2, 1);
        Assert.assertEquals(graph[0][0].getNodeNum(), 1);
    }

    @Test
    void preTourTest() {
        Assert.assertNull(testACO.bestTour);
    }

    @Test
    void postTourTest() {
        testACO.setHome(0, 0);
        testACO.setGoal(1, 2);
        testACO.startOptimization();
        Assert.assertNotNull(testACO.bestTour);
    }

    @Test
    void setNumberOfAntsTest() {
        AntColonyOptimisation testACO1 = new AntColonyOptimisation(10, 10, 33);
        Assert.assertEquals(33, testACO1.getAnts().size());
    }

    @Test
    void defaultNumberOfAntsTest() {
        AntColonyOptimisation testACO2 = new AntColonyOptimisation(10, 10, null);
        Assert.assertEquals(30, testACO2.getAnts().size());
    }
}
