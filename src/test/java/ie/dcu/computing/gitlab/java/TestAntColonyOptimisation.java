package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
    void postTourTest() throws IOException {
        testACO.setHome(0, 0);
        testACO.setGoal(1, 2);
        testACO.setCreateResults(false);
        testACO.startOptimisation();
        Assert.assertNotNull(testACO.bestTour);
    }

    @Test
    void setNumberOfAntsTest() throws IOException {
        AntColonyOptimisation testACO1 = new AntColonyOptimisation(10, 10, 33);
        Assert.assertEquals(33, testACO1.getAnts().size());
    }

    @Test
    void defaultNumberOfAntsTest() throws IOException {
        AntColonyOptimisation testACO2 = new AntColonyOptimisation(10, 10, null);
        Assert.assertEquals(30, testACO2.getAnts().size());
    }

    @Test
    void obstacleGenerationTest() throws IOException {
        AntColonyOptimisation testACO3 = new AntColonyOptimisation(10, 10, null);
        testACO3.setNumberOfObstacles(3);
        testACO3.setGoal(3, 3);
        testACO3.setHome(4, 4);
        testACO3.generateObstacles();
        Assert.assertEquals(3, testACO3.getObstacles().size());
    }

    @Test
    void getIterationTest() throws IOException {
        AntColonyOptimisation testACO4 = new AntColonyOptimisation(2, 1, 1);
        testACO4.setGoal(1, 0);
        testACO4.setHome(0, 0);
        testACO4.setCreateResults(false);
        testACO4.startOptimisation();
        Assert.assertEquals(6, testACO4.getIterationNumber());

        AntColonyOptimisation testACO5 = new AntColonyOptimisation(2, 1, 1);
        Assert.assertEquals(1, testACO5.getIterationNumber());
    }

    @Test
    void getTrailNodesTest() throws IOException {
        AntColonyOptimisation testACO6 = new AntColonyOptimisation(2, 1, 1);
        testACO6.setGoal(1, 0);
        testACO6.setHome(0, 0);
        Assert.assertEquals(0, testACO6.getTrailNodes().size());
        testACO6.setCreateResults(false);
        testACO6.startOptimisation();
        Assert.assertEquals(2, testACO6.getTrailNodes().size());
    }

    @Test
    void setEnvironmentSizeTest() {
        AntColonyOptimisation testACO7 = new AntColonyOptimisation(20, 20, 1);
        testACO7.setGoal(15, 15);
        testACO7.setHome(16, 16);
        testACO7.setEnvironmentSize(10, 10);
        Assert.assertNotEquals(15, testACO7.getGoal().getMatrixIndexX());
        Assert.assertNotEquals(15, testACO7.getGoal().getMatrixIndexY());
        Assert.assertNotEquals(16, testACO7.getHome().getMatrixIndexX());
        Assert.assertNotEquals(16, testACO7.getHome().getMatrixIndexY());
        Assert.assertEquals(30, testACO7.getAnts().size());
        Assert.assertEquals(10, testACO7.getGraph().length);
        Assert.assertEquals(10, testACO7.getGraph()[0].length);
    }

    @Test
    void setHomeTest() {
        AntColonyOptimisation testACO8 = new AntColonyOptimisation(20, 20, 1);
        testACO8.setHome(10, 10);
        Node originalHome = testACO8.getHome();
        testACO8.setHome(5, 5);
        Assert.assertEquals(NodeType.STANDARD, originalHome.getNodeType());
        Assert.assertEquals(5, testACO8.getHome().getMatrixIndexX());
        Assert.assertEquals(5, testACO8.getHome().getMatrixIndexY());
        testACO8.setHome(50, 50);
        Assert.assertNotEquals(50, testACO8.getHome().getMatrixIndexX());
        Assert.assertNotEquals(50, testACO8.getHome().getMatrixIndexY());
    }

    @Test
    void setGoalTest() {
        AntColonyOptimisation testACO9 = new AntColonyOptimisation(20, 20, 1);
        testACO9.setGoal(10, 10);
        Node originalGoal = testACO9.getGoal();
        testACO9.setGoal(5, 5);
        Assert.assertEquals(NodeType.STANDARD, originalGoal.getNodeType());
        Assert.assertEquals(5, testACO9.getGoal().getMatrixIndexX());
        Assert.assertEquals(5, testACO9.getGoal().getMatrixIndexY());
        testACO9.setGoal(50, 50);
        Assert.assertNotEquals(50, testACO9.getGoal().getMatrixIndexX());
        Assert.assertNotEquals(50, testACO9.getGoal().getMatrixIndexY());
    }

    @Test
    void updateObstaclesTest() {
        AntColonyOptimisation testACO10 = new AntColonyOptimisation(20, 20, 1);
        List<NodeGroup> originalObstacles = testACO10.getObstacles();
        ArrayList<NodeGroup> newObstacles = new ArrayList<>();
        newObstacles.add(new NodeGroup(NodeType.OBSTACLE, testACO10.getHome(), testACO10.getGoal(), testACO10.getGraph()));
        testACO10.updateObstacles(newObstacles);
        Assert.assertNotEquals(originalObstacles.size(), testACO10.getObstacles().size());
    }
}
