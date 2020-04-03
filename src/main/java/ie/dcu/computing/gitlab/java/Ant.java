package ie.dcu.computing.gitlab.java;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;


public class Ant {

    protected Color color;
    protected int x;
    protected int y;

    protected int trailSize;
    protected List<Node> trail = new ArrayList<>();
    protected String task;
    protected Node goalNode;

    protected double antPheromones;

    private double randomFactor = 0.1;

    private Random random = new Random();
    protected double probabilities[];

    protected double pheromoneImportance = 7;
    protected double distancePriority = 0.1;

    public Ant(int tourSize) {
        this.trailSize = tourSize;
        this.task = "searcher";
        this.color = Color.BLUE;
        // X and Y should be equal to the anthill
        // Need to derive X and Y from current node-type setup
        this.x = 0;
        this.y = 0;

        probabilities = new double [tourSize + 1];

        this.antPheromones = 500;

    }

    // ACO

    protected List<Node> getTrail() {
        return trail;
    }

    public void setGoalNode(Node node) {
        goalNode = node;
    }


    protected void visitNode(Node node) {
        this.trail.add(node);
        AntColonyOptimisation.addToVisited(node);
        updateLocation(node.getX(), node.getY());
    }

    protected Node selectNextNode(int currentIndex, Node[][] graph) {

        List<Node> possibleMoves = trail.get(currentIndex).getNeighbourNodes(graph, trail.get(currentIndex));
        int randomIndex = random.nextInt(possibleMoves.size());
        if (random.nextDouble() < randomFactor) {
            // pick random node
            return possibleMoves.get(randomIndex);
        }
        calculateProbabilities(possibleMoves);
        ArrayList<Node> chosenNodes = new ArrayList<>();
        for (Node node: possibleMoves) {
            if (node.getNodeType() == NodeType.GOAL) {
                System.out.println("goalNode is a neighbour, selecting that instead");
                return goalNode;
            }

            if (chosenNodes.size() > 0) {
                if (probabilities[node.getNodeNum()] > probabilities[chosenNodes.get(0).getNodeNum()]
                        && !visitedRecently(node)) {
                    chosenNodes.clear();
                    chosenNodes.add(node);
                }
                else if (probabilities[node.getNodeNum()] == probabilities[chosenNodes.get(0).getNodeNum()]
                        && !visitedRecently(node)) {
                    chosenNodes.add(node);
                }
            }

            else if (!visitedRecently(node)) {
                chosenNodes.add(node);
            }
        }
        if (chosenNodes.size() > 0) {
            return chosenNodes.get(random.nextInt(chosenNodes.size()));
        }

        throw new RuntimeException("Unsuccessful ant: There are no other nodes");
    }

    public void calculateProbabilities(List<Node> possibleMoves) {
        double pheromone = 0.0;
        for (Node node : possibleMoves) {
            if (!visitedRecently(node)) {
                pheromone += Math.pow(node.pheromoneCount, pheromoneImportance);
                // " * Math.pow(1.0 / node.getDistanceValue(goalNode), distancePriority)" was removed from above formula
            }
        }
        for (Node node : possibleMoves) {
            if (!visitedRecently(node)) {
                double numerator = Math.pow(node.pheromoneCount, pheromoneImportance);
                // " * Math.pow(1.0 / node.getDistanceValue(goalNode), distancePriority)" was removed from above formula
                probabilities[node.getNodeNum()] = numerator / pheromone;
            }
        }
    }

    protected boolean visitedRecently(Node node) {
        int s = trail.size();
        if (s > 20) {
            return(trail.subList(s-20, s).contains(node));
        }
        return (trail.contains(node));
    }

    protected void clear() {
        trail.clear();
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

    public void updateLocation(int newX, int newY) {
        this.setX(newX);
        this.setY(newY);
    }

    public void drawAnt(Graphics graphics) {
        graphics.setColor(color);
        graphics.drawRect(x, y, Node.getSize(), Node.getSize());
        graphics.fillRect(x, y, Node.getSize(), Node.getSize());
        /*

        Not accurate enough for the simulation, now implemented where the ant is
        one pixel tall and wide.

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
        */
    }
}
