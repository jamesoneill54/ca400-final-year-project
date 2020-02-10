package ie.dcu.computing.gitlab.java;

public class Ant {

    protected int trailSize;
    protected int trail[];
    protected boolean visited[];
    protected String task;

    public Ant (int tourSize) {
        this.trailSize = tourSize;
        this.trail = new int[tourSize];
        this.visited = new boolean[tourSize];
        this.task = "searcher";
    }

    protected int[] getTrail () {
        return trail;
    }

    protected boolean[] getVisited () {
        return visited;
    }

    protected void visitNode(int currentIndex, int node) {
        trail[currentIndex + 1] = node;
        visited[node] = true;
    }

    protected boolean visited(int i) {
        return visited[i];
    }

    protected double trailLength(double graph[] []) {
        double length = graph[trail[trailSize - 1]][trail[0]];
        for (int i = 0; i < trailSize - 1; i++) {
            length += graph[trail[i]][trail[i + 1]];
        }
        return length;
    }

    protected void clear() {
        for (int i = 0; i < trailSize; i++) {
            visited[i] = false;
        }
    }

    protected void switchTask(String newTask) {
        task = newTask;
    }
}
