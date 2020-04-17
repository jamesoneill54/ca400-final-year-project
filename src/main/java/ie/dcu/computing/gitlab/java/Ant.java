package ie.dcu.computing.gitlab.java;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;


public class Ant {

    protected AntType antType = AntType.UNSUCCESSFUL;
    protected Color color;
    protected int x;
    protected int y;

    protected int trailSize;
    protected List<Node> trail = new ArrayList<>();
    protected String task;
    protected Node homeNode;

    protected double antPheromones;

    private double randomFactor = 0.1;

    private Random random = new Random();
    protected double probabilities[];

    private double pheromoneImportance = 7;

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

    public void setHomeNode(Node node) {
        homeNode = node;
    }

    public void setPheromoneImportance(double alpha) {
        pheromoneImportance = alpha;
    }

    public AntType getAntType() {
        return antType;
    }

    protected void visitNode(Node node) {
        this.trail.add(node);
        AntColonyOptimisation.addToVisited(node);
        updateLocation(node.getX(), node.getY());
    }

    protected Node selectNextNode(int currentIndex, Node[][] graph, int distancePriority) {

        List<Node> possibleMoves = trail.get(currentIndex).getNeighbourNodes(graph, trail.get(currentIndex));
        int randomIndex = random.nextInt(possibleMoves.size());
        if (random.nextDouble() < randomFactor) {
            // pick random node
            return possibleMoves.get(randomIndex);
        }
        calculateProbabilities(possibleMoves, distancePriority);
        ArrayList<Node> chosenNodes = new ArrayList<>();
        for (Node node: possibleMoves) {
            if (node.getNodeType() == NodeType.GOAL) {
                System.out.println("goalNode is a neighbour, selecting that instead");
                return node;
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

    public void calculateProbabilities(List<Node> possibleMoves, int distancePriority) {
        double pheromone = 0.0;
        double normalisedDistancePriority = distancePriority * 0.00000000000000001;
        for (Node node : possibleMoves) {
            if (!visitedRecently(node)) {
                pheromone += Math.pow(node.pheromoneCount, pheromoneImportance) * Math.pow(1.0 / node.getDistanceValue(homeNode), normalisedDistancePriority);
            }
        }
        for (Node node : possibleMoves) {
            if (!visitedRecently(node)) {
                double numerator = Math.pow(node.pheromoneCount, pheromoneImportance) * Math.pow(1.0 / node.getDistanceValue(homeNode), normalisedDistancePriority);
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
        graphics.fillRect(x, y, Node.getSize(), Node.getSize());
    }
}
