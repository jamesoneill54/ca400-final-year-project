package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestScenario {

    @Test
    void initialisationTest() {
        AntColonyOptimisation acoAlgorithm = new AntColonyOptimisation(5, 5, 20);
        acoAlgorithm.setNumberOfObstacles(20);
        acoAlgorithm.generateObstacles();
        // Totally random
        Scenario testScenario = new Scenario(acoAlgorithm);
        testScenario.setupScenario();
        Assert.assertTrue(acoAlgorithm.getGraph().length > 5);
        Assert.assertTrue(acoAlgorithm.getGraph()[0].length > 5);
        Assert.assertNotNull(acoAlgorithm.getGoal());
        Assert.assertNotNull(acoAlgorithm.getHome());
        Assert.assertTrue(acoAlgorithm.getObstacles().size() <= 10);

        // Environment Size and Number of Obstacles set
        testScenario = new Scenario(acoAlgorithm, Scenario.MEDIUM_ENV, 7);
        testScenario.setupScenario();
        Assert.assertEquals(Scenario.MEDIUM_ENV, acoAlgorithm.getGraph().length);
        Assert.assertEquals(Scenario.MEDIUM_ENV, acoAlgorithm.getGraph()[0].length);
        Assert.assertNotNull(acoAlgorithm.getGoal());
        Assert.assertNotNull(acoAlgorithm.getHome());
        Assert.assertEquals(7, acoAlgorithm.getObstacles().size());

        // Everything set
        int[][] obstacles = {{0, 0, 2, 2}, {4, 4, 1, 1}};
        testScenario = new Scenario(acoAlgorithm, Scenario.MEDIUM_ENV, new int[] {5, 10}, new int[] {10, 20}, obstacles);
        testScenario.setupScenario();
        Assert.assertEquals(30, acoAlgorithm.getGraph().length);
        Assert.assertEquals(30, acoAlgorithm.getGraph()[0].length);
        Assert.assertEquals(5, acoAlgorithm.getHome().getMatrixIndexX());
        Assert.assertEquals(10, acoAlgorithm.getHome().getMatrixIndexY());
        Assert.assertEquals(10, acoAlgorithm.getGoal().getMatrixIndexX());
        Assert.assertEquals(20, acoAlgorithm.getGoal().getMatrixIndexY());
        Assert.assertEquals(2, acoAlgorithm.getObstacles().size());
    }

    @Test
    void setupScenarioTest() {
        AntColonyOptimisation acoAlgorithm = new AntColonyOptimisation(5, 5, 10);
        acoAlgorithm.setHome(3, 3);
        acoAlgorithm.setGoal(4, 4);
        Scenario[] scenarios = {
                new Scenario(acoAlgorithm,
                        Scenario.SMALL_ENV,
                        new int[] {0, 0},
                        new int[] {9, 9},
                        new int[][] {{3, 3, 1, 1}}),
                new Scenario(acoAlgorithm,
                        Scenario.MEDIUM_ENV,
                        new int[] {1, 1},
                        new int[] {29, 29},
                        new int[][] {{10, 10, 5, 5}}),
                new Scenario(acoAlgorithm,
                        Scenario.LARGE_ENV,
                        new int[] {2, 2},
                        new int[] {49, 49},
                        new int[][] {{5, 5, 4, 4}, {20, 20, 6, 6}})
        };

        // Compare before setup
        for (Scenario scenario: scenarios) {
            Assert.assertNotEquals(scenario.getEnvironmentSize(), acoAlgorithm.getGraph().length);
            Assert.assertNotEquals(scenario.getEnvironmentSize(), acoAlgorithm.getGraph()[0].length);
            Assert.assertNotEquals(scenario.getGoal()[0], acoAlgorithm.getGoal().getMatrixIndexX());
            Assert.assertNotEquals(scenario.getGoal()[1], acoAlgorithm.getGoal().getMatrixIndexY());
            Assert.assertNotEquals(scenario.getHome()[0], acoAlgorithm.getHome().getMatrixIndexX());
            Assert.assertNotEquals(scenario.getHome()[1], acoAlgorithm.getHome().getMatrixIndexY());
            Assert.assertNotEquals(scenario.getObstacles().length, acoAlgorithm.getObstacles().size());
            if (scenario.getObstacles().length == acoAlgorithm.getObstacles().size()) {
                int i = 0;
                for (int[] obstacleValues: scenario.getObstacles()) {
                    Assert.assertNotEquals(obstacleValues[0], acoAlgorithm.getObstacles().get(i).getX());
                    Assert.assertNotEquals(obstacleValues[1], acoAlgorithm.getObstacles().get(i).getY());
                    Assert.assertNotEquals(obstacleValues[2], acoAlgorithm.getObstacles().get(i).getWidth());
                    Assert.assertNotEquals(obstacleValues[3], acoAlgorithm.getObstacles().get(i).getHeight());
                    i++;
                }
            }
        }

        // Compare after setup
        for (Scenario scenario: scenarios) {
            scenario.setupScenario();
            Assert.assertEquals(scenario.getEnvironmentSize(), acoAlgorithm.getGraph().length);
            Assert.assertEquals(scenario.getEnvironmentSize(), acoAlgorithm.getGraph()[0].length);
            Assert.assertEquals(scenario.getGoal()[0], acoAlgorithm.getGoal().getMatrixIndexX());
            Assert.assertEquals(scenario.getGoal()[1], acoAlgorithm.getGoal().getMatrixIndexY());
            Assert.assertEquals(scenario.getHome()[0], acoAlgorithm.getHome().getMatrixIndexX());
            Assert.assertEquals(scenario.getHome()[1], acoAlgorithm.getHome().getMatrixIndexY());
            Assert.assertEquals(scenario.getObstacles().length, acoAlgorithm.getObstacles().size());
            if (scenario.getObstacles().length == acoAlgorithm.getObstacles().size()) {
                int i = 0;
                for (int[] obstacleValues: scenario.getObstacles()) {
                    Assert.assertEquals(obstacleValues[0], acoAlgorithm.getObstacles().get(i).getX());
                    Assert.assertEquals(obstacleValues[1], acoAlgorithm.getObstacles().get(i).getY());
                    Assert.assertEquals(obstacleValues[2], acoAlgorithm.getObstacles().get(i).getWidth());
                    Assert.assertEquals(obstacleValues[3], acoAlgorithm.getObstacles().get(i).getHeight());
                    i++;
                }
            }
        }
    }
}
