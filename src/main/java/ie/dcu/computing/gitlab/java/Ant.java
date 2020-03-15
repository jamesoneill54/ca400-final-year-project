package ie.dcu.computing.gitlab.java;

import java.awt.*;
import java.util.*;
import java.util.List;


public class Ant {

    protected Color color;
    protected int x;
    protected int y;
    protected static final int WIDTH = 7;
    protected static final int HEIGHT = 7;

    protected int trailSize;
    protected List<Node> trail = new ArrayList<>();
    protected String task;
    protected Node goalNode;

    private Random random = new Random();
    protected double probabilities[];

    public Ant(int tourSize) {
        this.trailSize = tourSize;
        this.task = "searcher";
        this.color = Color.BLUE;
        // X and Y should be equal to the anthill
        // Need to derive X and Y from current node-type setup
        this.x = 0;
        this.y = 0;

        probabilities = new double [tourSize + 1];
    }

    // ACO

    protected List<Node> getTrail() {
        return trail;
    }

    protected void visitNode(Node node) {
        this.trail.add(node);
        // add pheromone to node?
    }

    protected Node selectNextNode(int currentIndex, Node[][] graph) {
        //System.out.println("Node.java: Finding neighbour nodes for (" + ant.trail[currentIndex].getX() + ", " + ant.trail[currentIndex].getY() + ")..");
        List<Node> possibleMoves = this.trail.get(currentIndex).getNeighbourNodes(graph, this.trail.get(currentIndex));
        int t = random.nextInt(possibleMoves.size());
//        if (random.nextDouble() < randomFactor) {
//            OptionalInt nodeIndex = IntStream.range(0, possibleMoves.size())
//                    .filter(i -> i == t && !ant.visited(getNodeFromIndex(graph, i)))
//                    .findFirst();
//            if (nodeIndex.isPresent()) {
//                return nodeIndex.getAsInt();
//            }
//        }
        calculateProbabilities(possibleMoves);
        double r = random.nextDouble();
        double total = 0;
        for (Node node: possibleMoves) {
            if (node == goalNode) {
                //System.out.println("goalNode is a neighbour, selecting that instead");
                return goalNode;
            }
            total += probabilities[node.getNodeNum()];
            if (total >= r) {
                return node;
            }
        }

        throw new RuntimeException("There are no other nodes");
    }

    public void calculateProbabilities(List<Node> possibleMoves) {
        //System.out.println("Calculating probabilities for ant " + ants.indexOf(ant));
        double pheromone = 0.0;
        for (Node node : possibleMoves) {
            double evenChances = possibleMoves.size();
            //if (!ant.visited(node)) {
            probabilities[node.getNodeNum()] = 1.0 / evenChances;
            //}
        }
//        for (int l = 0; l < possibleMoves.size(); l++) {
//            int nodeYCoord = possibleMoves.get(l).getY();
//            if (!ant.visited(node)) {
//                System.out.println("graph[i][l] = " + graph[nodeYCoord][i].getNodeNum());
//                pheromone += Math.pow(trails[i][l], pheromoneImportance) * Math.pow(1.0 / graph[l][i].getNodeNum(), distancePriority);
//            }
//        }
//        for (int j = 0; j < possibleMoves.size(); j++) {
//            if (ant.visited(j)) {
//                probabilities[j] = 0.0;
//            } else {
//                double numerator =  Math.pow(trails[i][j], pheromoneImportance) * Math.pow(1.0 / graph[j][i].getNodeNum(), distancePriority);
//                probabilities[j] = numerator / pheromone;
//            }
//        }
    }

    protected boolean visited(Node node) {
        return (trail.contains(node));
    }

    protected double trailLength() {
        return this.trail.size();
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
