package ie.dcu.computing.gitlab.java;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class TestBreadthFirstSearch {

    private AntColonyOptimisation env = new AntColonyOptimisation(60,50,0);

    private List<NodeGroup> obsts = new ArrayList<>();

    @Test
    void noObstTest1() {
        env.setHome(1,1);
        env.setGoal(5,3);
        BreadthFirstSearch bfs = new BreadthFirstSearch(env.getGraph(), env.getHome(), env.getGoal());
        LinkedHashSet<Node> optTour = bfs.solve();
        Assert.assertEquals(optTour.size(), 5);
    }

    @Test
    void noObstTest2() {
        env.setHome(2,11);
        env.setGoal(13,7);
        BreadthFirstSearch bfs = new BreadthFirstSearch(env.getGraph(), env.getHome(), env.getGoal());
        LinkedHashSet<Node> optTour = bfs.solve();
        System.out.println("Best" + optTour.toString());
        Assert.assertEquals(optTour.size(), 12);
    }

    @Test
    void ObstTest1() {
        env.setHome(1, 1);
        env.setGoal(5,3);
        NodeGroup obst = new NodeGroup(NodeType.OBSTACLE, 10, 4, 3, 3, env.getGraph());
        obst.setNodesToType(env.getGraph());
        BreadthFirstSearch bfs = new BreadthFirstSearch(env.getGraph(), env.getHome(), env.getGoal());
        LinkedHashSet<Node> optTour = bfs.solve();
        System.out.println("Best" + optTour.toString());
        Assert.assertEquals(optTour.size(), 5);
    }

    @Test
    void ObstTest2() {
        env.setHome(2, 11);
        env.setGoal(13,7);
        NodeGroup obst = new NodeGroup(NodeType.OBSTACLE, 10, 4, 3, 3, env.getGraph());
        obst.setNodesToType(env.getGraph());
        BreadthFirstSearch bfs = new BreadthFirstSearch(env.getGraph(), env.getHome(), env.getGoal());
        LinkedHashSet<Node> optTour = bfs.solve();
        System.out.println("Best" + optTour.toString());
        Assert.assertEquals(optTour.size(), 12);
    }

    @Test
    void ObstTest3() {
        env.setHome(2, 11);
        env.setGoal(13,7);
        NodeGroup obst1 = new NodeGroup(NodeType.OBSTACLE, 10, 3, 3, 9, env.getGraph());
        NodeGroup obst2 = new NodeGroup(NodeType.OBSTACLE, 4, 7, 5, 5, env.getGraph());
        obsts.add(obst1);
        obsts.add(obst2);
        for (NodeGroup obstacle: obsts) {
            obstacle.setNodesToType(env.getGraph());
        }
        env.updateObstacles(obsts);
        BreadthFirstSearch bfs = new BreadthFirstSearch(env.getGraph(), env.getHome(), env.getGoal());
        LinkedHashSet<Node> optTour = bfs.solve();
        System.out.println("Best" + optTour.toString());
        Assert.assertEquals(optTour.size(), 16);
    }

    @Test
    void noObstTest3() {
        env.setHome(5,3);
        env.setGoal(1,1);
        BreadthFirstSearch bfs = new BreadthFirstSearch(env.getGraph(), env.getHome(), env.getGoal());
        LinkedHashSet<Node> optTour = bfs.solve();
        Assert.assertEquals(optTour.size(), 5);
    }
}
