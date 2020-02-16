package ie.dcu.computing.gitlab.java;

import java.awt.*;

public class Ant {

    protected Color color;
    protected int x;
    protected int y;
    protected static final int WIDTH = 7;
    protected static final int HEIGHT = 7;

    protected int trailSize;
    protected int[] trail;
    protected boolean[] visited;
    protected String task;

    public Ant(int tourSize, Color antColor) {
        this.trailSize = tourSize;
        this.trail = new int[tourSize];
        this.visited = new boolean[tourSize];
        this.task = "searcher";
        this.color = antColor;
        // X and Y should be equal to the anthill
        // Need to derive X and Y from current node-type setup
        this.x = 0;
        this.y = 0;
    }

    public Ant(int tourSize) {
        this.trailSize = tourSize;
        this.trail = new int[tourSize];
        this.visited = new boolean[tourSize];
        this.task = "searcher";
        this.color = Color.BLUE;
        // X and Y should be equal to the anthill
        // Need to derive X and Y from current node-type setup
        this.x = 0;
        this.y = 0;
    }

    protected int[] getTrail() {
        return trail;
    }

    protected boolean[] getVisited() {
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

    // ANIMATION

    public Color getColor() {
        return color;
    }

    public void setColor(Color newColor) {
        color = newColor;
    }

    public int getX() {
        return x;
    }

    public void setX(int newX) {
        x = newX;
    }

    public int getY() {
        return y;
    }

    public void setY(int newY) {
        y = newY;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public void updateLocation(int newX, int newY) {
        this.setX(newX);
        this.setY(newY);
    }

    public void drawAnt(Graphics graphics) {
        graphics.setColor(color);
        // The ant's head.
        graphics.drawOval(x + 2, y, 3, 2);
        graphics.fillOval(x + 2, y, 3, 2);
        // The ant's body.
        graphics.drawOval(x + 2, y + 2, 3, 4);
        graphics.fillOval(x + 2, y + 2, 3, 4);
        // The ant's left three legs.
        graphics.drawLine(x + 2, y + 2, x, y + 1);
        graphics.drawLine(x + 2, y + 4, x, y + 4);
        graphics.drawLine(x + 2, y + 6, x, y + 7);
        // The ant's right three legs.
        graphics.drawLine(x + 5, y + 2, x + 7, y + 1);
        graphics.drawLine(x + 5, y + 4, x + 7, y + 4);
        graphics.drawLine(x + 5, y + 6, x + 7, y + 7);
    }
}
