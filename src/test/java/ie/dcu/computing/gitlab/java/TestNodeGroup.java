package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestNodeGroup {

    AntColonyOptimisation testACO = new AntColonyOptimisation(10, 10, 1);

    @Test
    void initialiseRandomNodeGroupTest() {
        testACO.setHome(2, 2);
        testACO.setGoal(4, 4);
        NodeGroup testNodeGroup = new NodeGroup(NodeType.OBSTACLE, testACO.getHome(), testACO.getGoal(), testACO.getGraph());
        Assert.assertNotNull(testNodeGroup);
    }

    @Test
    void isValidTest() {
        testACO.setHome(2, 2);
        testACO.setGoal(4, 4);
        NodeGroup testNodeGroup0 = new NodeGroup(NodeType.OBSTACLE, 2, 2, 1, 2, testACO.getGraph());
        Assert.assertFalse(testNodeGroup0.isValid(testACO.getHome(), testACO.getGoal()));
        testACO.setHome(3, 2);
        Assert.assertTrue(testNodeGroup0.isValid(testACO.getHome(), testACO.getGoal()));
        testACO.setGoal(2, 3);
        Assert.assertFalse(testNodeGroup0.isValid(testACO.getHome(), testACO.getGoal()));
        testACO.setGoal(2, 4);
        Assert.assertTrue(testNodeGroup0.isValid(testACO.getHome(), testACO.getGoal()));
        testNodeGroup0 = new NodeGroup(NodeType.OBSTACLE, 0, 0, 9, 1, testACO.getGraph());
        Assert.assertTrue(testNodeGroup0.isValid(testACO.getHome(), testACO.getGoal()));
        testNodeGroup0 = new NodeGroup(NodeType.OBSTACLE, 0, 0, 10, 1, testACO.getGraph());
        Assert.assertFalse(testNodeGroup0.isValid(testACO.getHome(), testACO.getGoal()));
        testNodeGroup0 = new NodeGroup(NodeType.OBSTACLE, 0, 0, 1, 9, testACO.getGraph());
        Assert.assertTrue(testNodeGroup0.isValid(testACO.getHome(), testACO.getGoal()));
        testNodeGroup0 = new NodeGroup(NodeType.OBSTACLE, 0, 0, 1, 10, testACO.getGraph());
        Assert.assertFalse(testNodeGroup0.isValid(testACO.getHome(), testACO.getGoal()));
    }

    @Test
    void setNodesToTypeTest() {
        NodeGroup testNodeGroup1 = new NodeGroup(NodeType.OBSTACLE, 5, 5, 3, 3, testACO.getGraph());
        testNodeGroup1.setNodesToType(testACO.getGraph());
        testACO.setGoal(2, 4);
        testACO.setHome(3, 2);
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (x >= 5 && x < 8 && y >= 5 && y < 8) {
                    Assert.assertEquals(NodeType.OBSTACLE, testACO.getGraph()[y][x].getNodeType());
                }
                else if (x == 3 && y == 2) {
                    Assert.assertEquals(NodeType.HOME, testACO.getGraph()[y][x].getNodeType());
                }
                else if (x == 2 && y == 4) {
                    Assert.assertEquals(NodeType.GOAL, testACO.getGraph()[y][x].getNodeType());
                }
                else {
                    Assert.assertEquals(NodeType.STANDARD, testACO.getGraph()[y][x].getNodeType());
                }
            }
        }
    }
}
