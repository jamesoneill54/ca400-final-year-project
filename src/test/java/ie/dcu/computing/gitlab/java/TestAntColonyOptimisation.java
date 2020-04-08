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

    @Test
    void obstacleGenerationTest() {
        AntColonyOptimisation testACO3 = new AntColonyOptimisation(10, 10, null);
        testACO3.setNumberOfObstacles(3);
        testACO3.setGoal(3, 3);
        testACO3.setHome(4, 4);
        testACO3.generateObstacles();
        Assert.assertEquals(3, testACO3.getObstacles().size());
    }

    @Test
    void getIterationTest() {
        AntColonyOptimisation testACO4 = new AntColonyOptimisation(2, 1, 1);
        testACO4.setGoal(1, 0);
        testACO4.setHome(0, 0);
        testACO4.startOptimization();
        Assert.assertEquals(3, testACO4.getIterationNumber());

        AntColonyOptimisation testACO5 = new AntColonyOptimisation(2, 1, 1);
        Assert.assertEquals(0, testACO5.getIterationNumber());
    }

    @Test
    void getTrailNodes() {
        AntColonyOptimisation testACO6 = new AntColonyOptimisation(2, 1, 1);
        testACO6.setGoal(1, 0);
        testACO6.setHome(0, 0);
        Assert.assertEquals(0, testACO6.getTrailNodes().size());
        testACO6.startOptimization();
        Assert.assertEquals(2, testACO6.getTrailNodes().size());
    }
}
