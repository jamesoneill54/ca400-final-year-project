package ie.dcu.computing.gitlab.java;

import java.util.*;

public class BreadthFirstSearch {
    private Node home;
    private Node goal;
    private List<Node> visited = new ArrayList<Node>() {};
    private Node[][] graph;

    BreadthFirstSearch(Node[][] graph, Node home, Node goal) {
        this.graph = graph;
        this.home = home;
        this.goal = goal;
    }

    public LinkedHashSet<Node> solve() {
        visited.add(home);
        int i = 0;
        System.out.println(visited);
        while(!visited.contains(goal)) {
            Node currNode = visited.get(i);
            List<Node> neighbs = currNode.getNeighbourNodes(graph);
            for (Node n : neighbs) {
                if (n != null && !visited.contains(n)) {
                    currNode.addChild(n);
                    visited.add(n);
                }
            }
            System.out.println("Iter" + i + ":" + visited);
            i ++;
        }
        goal.getDescendants(home);
        System.out.println("Best route: " + goal.getDescendants(home).size());
        goal.printDescendants();
        return goal.getDescendants(home);
    }
}
