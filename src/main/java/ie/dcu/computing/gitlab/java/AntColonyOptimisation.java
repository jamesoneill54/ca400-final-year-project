package ie.dcu.computing.gitlab.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AntColonyOptimisation {
    private double initialTrails = 1.0;
    private double pheromoneImportance = 1;
    private double distancePriority = 5;
    private double evaporationRate = 0.5;
    private double pheromonePerAnt = 500;
    private double antPerNode = 0.8;
    private double randomFactor = 0.01;

    private int maxIterations = 10;

    private boolean runningAsVisualSimulation = false;
    private int numberOfNodes;
    private int numberOfAnts;
    private Node graph[][];
    private Node homeNode;
    private Node goalNode;
    private List<Ant> ants = new ArrayList<>();
    private Random random = new Random();

    private int currentIndex;

    public List<Node> bestTourOrder;
    private double bestTourLength;

    public AntColonyOptimisation(int w, int h, Integer numAnts) {
        graph = generateMatrixFromEnv(w, h);
        numberOfNodes = graph.length * graph[0].length;

        if (numAnts == null) {
            numberOfAnts = (int) (numberOfNodes * antPerNode);
        }
        else {
            numberOfAnts = numAnts;
        }

        for (int antNumber = 0; antNumber < numberOfAnts; antNumber++) {
            ants.add(new Ant(numberOfNodes));
        }
    }

    public Node[][] generateMatrixFromEnv(int width, int height) {
        Node.resetNumberOfNodes();
        Node[][] matrix = new Node[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                matrix[y][x] = new Node(x, y);
            }
        }
        return matrix;
    }

    public void printMatrix(Node[][] m) {

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == homeNode) {
                    System.out.print("( HOME )");
                } else if (m[i][j] == goalNode) {
                    System.out.print("( GOAL )");
                } else {
                    m[i][j].printNode();
                }
                System.out.print(" ");
            }
            System.out.println(" ");
        }
    }

    public void setRunningAsVisualSimulation(boolean bool) {
        runningAsVisualSimulation = bool;
    }

    public Node setHome(int x, int y) {
        if (x <= graph[0].length && y <= graph.length) {
            graph[y][x].setNodeAsHome();
            this.homeNode = graph[y][x];
            return this.homeNode;
        }
        return null;
    }

    public Node getHome() {
        return homeNode;
    }

    public Node setGoal(int x, int y) {
        if (x <= graph[0].length && y <= graph.length) {
            graph[y][x].setNodeAsGoal();
            this.goalNode = graph[y][x];
            return this.goalNode;
        }
        return null;
    }

    public Node getGoal() {
        return goalNode;
    }

    public void startOptimization() {
        for (int attemptNum = 1; attemptNum < 9; attemptNum++) {
            System.out.println("Attempt #" + attemptNum);
            solve();
        }
    }

    public List<Node> solve() {
        setupAnts();
        clearTrails();
        for (int i = 0; i < maxIterations; i++) {
            constructSolutions();
            //updateTrails();
            updateBest();
        }
        System.out.println("Best tour length: " + bestTourLength);
        System.out.println("Best tour order: " + bestTourOrder.toString());
        return bestTourOrder;
    }

    public void setupAnts() {
        for (Ant ant: ants) {
            ant.clear();
            ant.visitNode(homeNode);
        }
        currentIndex = 0;
    }

    public void printAntCurrentLoc() {
        for (Ant ant : ants) {
            System.out.println("Ant " + ants.indexOf(ant) + ": " + ant.trail.get(ant.trail.size() - 1).getNodeNum());
        }
    }

    public void constructSolutions() {
        for (Ant ant: ants) {
            currentIndex = 0;
            while (ant.trail.get(currentIndex) != goalNode) {
                Node newNode = ant.selectNextNode(currentIndex, graph);
                ant.visitNode(newNode);
                if (runningAsVisualSimulation) {
                    try {
                        Thread.sleep(Simulation.getAnimationDelay());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //System.out.println("NODE VISITED: " + newNode + " : (" + getNodeFromIndex(graph, newNode).getX() + ", " + getNodeFromIndex(graph, newNode).getY() + ")\n");
                currentIndex ++;
            }
            //System.out.println("Goal node reached! Onto next ant..");
            //System.out.print("\n-----------------------------------------------\n\n");
        }
    }

    private void updateTrails() {
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                //trails [i][j] *= evaporationRate;
            }
        }
        for (Ant a : ants) {
            double contribution = pheromonePerAnt / a.trailLength();
            for (int i = 0; i < numberOfNodes - 1; i++) {
                //trails[a.trail.get(i).getNodeNum()][a.trail.get(i + 1).getNodeNum()] += contribution;
            }
            //trails[a.trail.get(numberOfNodes - 1).getNodeNum()][a.trail.get(0).getNodeNum()] += contribution;
        }
    }

    private void updateBest() {
        if (bestTourOrder == null) {
            bestTourOrder = ants.get(0).trail;
            bestTourLength = ants.get(0)
                    .trail.size();
            for (Ant ant: ants) {
                if (ant.trail.size() < bestTourLength) {
                    bestTourLength = ant.trail.size();
                    bestTourOrder = ant.trail;
                }
            }
        }
    }

    private void clearTrails() {
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                //trails[i][j] = initialTrails;
            }
        }
    }

    public List<Ant> getAnts() {
        return ants;
    }

    public static void main(String[] args) {
        System.out.println("Running Ant Colony Optimisation Algorithm...");
        AntColonyOptimisation myACO = new AntColonyOptimisation(6, 4, null);
        try {
            myACO.setHome(2,2);
            myACO.setGoal(4, 3);
            myACO.printMatrix(myACO.graph);
            System.out.println("Goal is: ");
            myACO.goalNode.printNode();
        } catch (NullPointerException e) {
            System.out.println("ERROR: Goal Node coordinates outside of environment scope. Respecify Goal Node value(s).");
        }
        System.out.println();
        myACO.setupAnts();
        System.out.println("Number of Ants: " + myACO.numberOfAnts);
        myACO.printAntCurrentLoc();
        myACO.startOptimization();
        System.out.println("\n---------------\nFINAL ANT POSITIONS:\n");
        myACO.printAntCurrentLoc();
    }
}
