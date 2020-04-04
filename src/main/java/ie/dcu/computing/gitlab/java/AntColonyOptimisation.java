package ie.dcu.computing.gitlab.java;

import java.util.*;

public class AntColonyOptimisation {
    private double pheromoneRetentionRate = 0.2;
    private double pheromonePerAnt = 500;
    private double antPerNode = 0.3;

    private int maxIterations = 3;

    private boolean runningAsVisualSimulation = false;
    private int numberOfNodes;
    private int numberOfAnts;
    private int numberOfObstacles;
    private Node graph[][];
    private Node homeNode;
    private Node goalNode;
    private List<Ant> ants = new ArrayList<>();
    private List<NodeGroup> obstacles = new ArrayList<>();
    private HashSet<Ant> stoppedAnts = new HashSet<>();
    private static Set<Node> globalVisited = new HashSet<Node>() {
    };

    private int stepsTravelled;

    public List<Node> bestTour;

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

    public Node[][] generateMatrixFromEnv(int columns, int rows) {
        Node.resetNumberOfNodes();
        Node[][] matrix = new Node[rows][columns];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
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

    public Node[][] getGraph() {
        return graph;
    }

    public void setRunningAsVisualSimulation(boolean bool) {
        runningAsVisualSimulation = bool;
    }

    public void setHome(int x, int y) {
        if (x < graph[0].length && y < graph.length) {
            graph[y][x].setNodeAsHome();
            this.homeNode = graph[y][x];
        }
        else {
            throw new IndexOutOfBoundsException(
                    "The coordinates of the home node are outside the environment boundaries.");
        }
    }

    public Node getHome() {
        return homeNode;
    }

    public void setGoal(int x, int y) {
        if (x < graph[0].length && y < graph.length) {
            graph[y][x].setNodeAsGoal();
            this.goalNode = graph[y][x];
        }
        else {
            throw new IndexOutOfBoundsException(
                    "The coordinates of the goal node are outside the environment boundaries.");
        }
    }

    public Node getGoal() {
        return goalNode;
    }

    public static void addToVisited(Node node) {
        globalVisited.add(node);
    }

    public void setNumberOfObstacles(int number) {
        this.numberOfObstacles = number;
    }

    public List<NodeGroup> getObstacles() {
        return obstacles;
    }

    public void generateObstacles() {
        for (int i = 0; i < numberOfObstacles; i++) {
            obstacles.add(new NodeGroup(NodeType.OBSTACLE, homeNode, goalNode, graph));
        }
    }

    public void startOptimization() {
        this.generateObstacles();
        for (int attemptNum = 1; attemptNum < 9; attemptNum++) {
            System.out.println("Attempt #" + attemptNum);
            solve();
        }
    }

    public List<Node> solve() {
        setupAnts();

        for (int i = 0; i < maxIterations; i++) {
            setupAnts();
            constructSolutions();
            updateTrails();
            updateBest();
        }
        System.out.println("Best tour length: " + bestTour.size());
        System.out.println("Best tour order: " + bestTour.toString());
        return bestTour;
    }

    public void setupAnts() {
        for (Ant ant: ants) {
            ant.clear();
            ant.visitNode(homeNode);
            ant.setGoalNode(goalNode);
        }
        stepsTravelled = 0;
    }

    public void printAntCurrentLoc() {
        for (Ant ant : ants) {
            System.out.println("Ant " + ants.indexOf(ant) + ": " + ant.trail.get(ant.trail.size() - 1).getNodeNum());
        }
    }

    public void constructSolutions() {
        stepsTravelled = 0;
        stoppedAnts.clear();
        while (stoppedAnts.size() != ants.size()) {
            for (Ant ant: ants) {
                try {
                    if (!stoppedAnts.contains(ant)) {
                        Node newNode = ant.selectNextNode(stepsTravelled, graph);
                        ant.visitNode(newNode);
                        if (newNode == goalNode) {
                            System.out.println("Goal node reached!");
                            stoppedAnts.add(ant);
                        }
                    }
                }

                catch(RuntimeException e) {
                    System.out.println(e.getMessage());
                    stoppedAnts.add(ant);
                }
            }

            if (runningAsVisualSimulation) {
                try {
                    Thread.sleep(Simulation.getAnimationDelay());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            stepsTravelled += 1;
        }
    }

    private void updateTrails() {
        for (Node node : globalVisited) {
            node.pheromoneCount *= pheromoneRetentionRate;
        }
        for (Ant a : ants) {
            if (a.trail.contains(a.goalNode)) {
                double contribution = pheromonePerAnt / a.trail.size();
                for (Node node : a.trail) {
                    node.pheromoneCount += contribution;
                }
            }
        }
    }

    private void updateBest() {
        if (bestTour == null) {
            bestTour = ants.get(0).trail;
        }
        for (Ant ant: ants) {
            if (ant.trail.size() < bestTour.size()) {
                bestTour = ant.trail;
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
