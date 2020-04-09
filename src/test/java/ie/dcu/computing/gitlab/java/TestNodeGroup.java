package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestNodeGroup {

    AntColonyOptimisation testACO = new AntColonyOptimisation(10, 10, 1);

    public TestNodeGroup() throws IOException {
    }

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
        NodeGroup testNodeGroup0 = new NodeGroup(NodeType.OBSTACLE, 2, 2, 1, 2);
        Assert.assertFalse(testNodeGroup0.isValid(testACO.getHome(), testACO.getGoal()));
        testACO.setHome(3, 2);
        Assert.assertTrue(testNodeGroup0.isValid(testACO.getHome(), testACO.getGoal()));
        testACO.setGoal(2, 3);
        Assert.assertFalse(testNodeGroup0.isValid(testACO.getHome(), testACO.getGoal()));
        testACO.setGoal(2, 4);
        Assert.assertTrue(testNodeGroup0.isValid(testACO.getHome(), testACO.getGoal()));
    }
}
