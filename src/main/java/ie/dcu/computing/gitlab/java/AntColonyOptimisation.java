package ie.dcu.computing.gitlab.java;

import ie.dcu.computing.gitlab.java.ui.UIContents;

import java.awt.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;

public class AntColonyOptimisation {
    private double pheromoneImportance = 7;
    private int distancePriority = 10;

    private double pheromoneRetentionRate = 0.2;
    private double pheromonePerAnt = 500;
    private double antPerNode = 0.3;

    private int maxIterations = 5;
    private int iterationNumber = 1;
    private int maxAttempts = 4;
    private int attemptNumber = 1;

    private boolean runningAsVisualSimulation = false;
    private boolean processCancelled = false;
    private boolean processPaused = false;
    private boolean iterationSkipped = false;
    private boolean createResults = true;
    private int numberOfNodes;
    private int numberOfAnts;
    private int numberOfObstacles;
    private Node graph[][];
    private Node homeNode;
    private Node goalNode;
    private List<Ant> ants = new ArrayList<>();
    private List<NodeGroup> obstacles = new ArrayList<>();
    private HashSet<Ant> stoppedAnts = new HashSet<>();
    private HashSet<Node> trailNodes = new HashSet<>();
    private static Set<Node> globalVisited = new HashSet<>();

    private int stepsTravelled;
    private int maxSteps;
    private int successes;
    private int numberOfBests = 0;

    public List<Node> bestTour;
    public List<Node> globalBestTour;

    private String RESULTS_FOLDER = "./res/results/";
    private String runID;

    protected PerformanceLogger performanceLogger;

    public AntColonyOptimisation(int w, int h, Integer numAnts) {
        graph = generateMatrixFromEnv(w, h);
        numberOfNodes = graph.length * graph[0].length;
        generateAnts(numAnts);
        setRandomHome();
        setRandomGoal();
        generateObstacles();
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

    public void setRandomHome() {
        Random random = new Random();
        int homeX = random.nextInt(graph[0].length);
        int homeY = random.nextInt(graph.length);
        while (!validHomeGoalLocation(homeX, homeY)) {
            homeX = random.nextInt(graph[0].length);
            homeY = random.nextInt(graph.length);
        }
        setHome(homeX, homeY);
    }

    public void setRandomGoal() {
        Random random = new Random();
        int goalX = random.nextInt(graph[0].length);
        int goalY = random.nextInt(graph.length);
        while (!validHomeGoalLocation(goalX, goalY)) {
            goalX = random.nextInt(graph[0].length);
            goalY = random.nextInt(graph.length);
        }
        setGoal(goalX, goalY);
    }

    public void generateAnts(Integer numAnts) {
        if (numAnts == null) {
            numberOfAnts = (int) (numberOfNodes * antPerNode);
        }
        else {
            numberOfAnts = numAnts;
        }

        ants.clear();
        for (int antNumber = 0; antNumber < numberOfAnts; antNumber++) {
            ants.add(new Ant(numberOfNodes));
        }
    }

    public void setEnvironmentSize(int width, int height) {
        graph = generateMatrixFromEnv(width, height);
        numberOfNodes = graph.length * graph[0].length;
        setRandomHome();
        setRandomGoal();
        generateAnts(null);
    }

    public void setHome(int x, int y) {
        if (x < graph[0].length && y < graph.length) {
            if (homeNode != null) {
                homeNode.setNodeAsStandard();
            }
            graph[y][x].setNodeAsHome();
            this.homeNode = graph[y][x];
        }
        else {
            if (runningAsVisualSimulation) {
                Simulation.showDialog("Home coordinates are outside environment bounds. Randomising home node...");
            }
            else {
                System.out.println("Home coordinates are outside environment bounds. Randomising home node...");
            }
            setRandomHome();
        }
    }

    public boolean validHomeGoalLocation(int x, int y) {
        return graph[y][x].getNodeType() == NodeType.STANDARD;
    }

    public Node getHome() {
        return homeNode;
    }

    public void setGoal(int x, int y) {
        if (x < graph[0].length && y < graph.length) {
            if (goalNode != null) {
                goalNode.setNodeAsStandard();
            }
            graph[y][x].setNodeAsGoal();
            this.goalNode = graph[y][x];
        }
        else {
            if (runningAsVisualSimulation) {
                Simulation.showDialog("Goal coordinates are outside environment bounds. Randomising goal node...");
            }
            else {
                System.out.println("Goal coordinates are outside environment bounds. Randomising goal node...");
            }
            setRandomGoal();
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
        return new ArrayList<>(obstacles);
    }

    public void updateObstacles(List<NodeGroup> newObstacles) {
        obstacles = newObstacles;
    }

    public void setCreateResults(boolean createResults) {
        this.createResults = createResults;
    }

    public void generateObstacles() {
        obstacles.clear();
        for (int i = 0; i < numberOfObstacles; i++) {
            obstacles.add(new NodeGroup(NodeType.OBSTACLE, homeNode, goalNode, graph));
        }
    }

    public double getPheromoneImportance() {
        return pheromoneImportance;
    }

    public void setPheromoneImportance(double value) {
        pheromoneImportance = value;
    }

    public int getDistancePriority() {
        return distancePriority;
    }

    public void setDistancePriority(int value) {
        distancePriority = value;
    }

    public void setRunID(String id) {
        runID = id;
    }

    private String createVariableID() {
        return ants.size() + "-" + distancePriority + "-" + pheromoneImportance;
    }

    public void startOptimisation() throws IOException {
        if (runningAsVisualSimulation) {
            UIContents.lockVariableControls();
        }
        processCancelled = false;
        clearTrails();
        for (NodeGroup obstacle: obstacles) {
            obstacle.setNodesToType(graph);
        }
        if (maxSteps == 0) {
            maxSteps = (int) (numberOfNodes * 0.7);
        }
        globalBestTour = null;
        bestTour = null;
        for (attemptNumber = 1; attemptNumber <= maxAttempts; attemptNumber++) {
            if (processCancelled) {
                break;
            }
            if (runningAsVisualSimulation) {
                UIContents.updateAttemptNumber();
                runID = "visual";
            }
            if (createResults) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String ts = timestamp.toString().replace(" ", "-").replace(".", "_").replace(":", "_");
                String fileName = RESULTS_FOLDER + "adhoc_" + ts + "_ATTEMPT" + attemptNumber + ".json";
                if (runID.equals("visual")) {
                    fileName = RESULTS_FOLDER + runID + "_" + ts + "_ATTEMPT" + attemptNumber + ".json";
                }
                else if (runID.startsWith("S_")) {
                    fileName = RESULTS_FOLDER + runID + "-" + createVariableID() + "_" + ts + "_ATTEMPT" + attemptNumber + ".json";
                }
                performanceLogger = new PerformanceLogger(fileName);
                performanceLogger.formatResults(ts, attemptNumber, homeNode, goalNode, pheromoneImportance, distancePriority, obstacles);
            }
            solve();
            if (createResults) {
                performanceLogger.formatResults(globalBestTour);
                performanceLogger.close();
            }
        }
        if (runningAsVisualSimulation) {
            UIContents.unlockVariableControls();
        }
    }

    public void stopOptimisation() {
        System.out.println("Stopping AntColonyOptimisation...");
        processCancelled = true;
        resumeOptimisation();
    }

    public void pauseOptimisation() {
        System.out.println("Pausing AntColonyOptimisation...");
        processPaused = true;
    }

    public void resumeOptimisation() {
        System.out.println("Resuming Optimisation...");
        processPaused = false;
    }

    public boolean isPaused() {
        return processPaused;
    }

    public void skipIteration() {
        iterationSkipped = true;
    }

    public void solve() {
        bestTour = null;
        if (runningAsVisualSimulation) {
            UIContents.updateBestTour();
        }
        for (iterationNumber = 1; iterationNumber <= maxIterations; iterationNumber++) {
            iterationSkipped = false;
            if (processCancelled) {
                break;
            }
            if (runningAsVisualSimulation) {
                UIContents.updateIterationNumber();
            }
            setupAnts();
            constructSolutions();
            if (createResults) {
                performanceLogger.formatResults(ants, iterationNumber);
            }
            updateTrails();
            updateBest();
            if (createResults) {
                if (bestTour != null) {
                    performanceLogger.formatResults(ants, iterationNumber, maxIterations, numberOfBests, bestTour, successes);
                }
                else {
                    performanceLogger.formatResults(ants, iterationNumber, maxIterations, numberOfBests, new ArrayList<>(), successes);
                }
            }
        }
    }

    public void setupAnts() {
        for (Ant ant: ants) {
            ant.clear();
            ant.antType = AntType.UNSUCCESSFUL;
            ant.visitNode(homeNode);
            ant.setHomeNode(homeNode);
            ant.setPheromoneImportance(pheromoneImportance);
        }
        stoppedAnts.clear();
        successes = 0;
        stepsTravelled = 0;
        if (runningAsVisualSimulation) {
            UIContents.updateAntCounts();
        }
    }

    public void printAntCurrentLoc() {
        for (Ant ant : ants) {
            System.out.println("Ant " + ants.indexOf(ant) + ": " + ant.getTrail().get(ant.getTrail().size() - 1).getNodeNum());
        }
    }

    public void constructSolutions() {
        stepsTravelled = 0;
        while (stoppedAnts.size() != ants.size() && !processCancelled && stepsTravelled <= maxSteps) {
            for (Ant ant: ants) {
                while (processPaused) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        System.out.println("AntColonyOptimisation stopped while paused");
                        processPaused = false;
                    }
                }

                try {
                    if (!stoppedAnts.contains(ant)) {
                        ant.setColor(Color.BLUE);
                        Node newNode = ant.selectNextNode(stepsTravelled, graph, distancePriority);
                        ant.visitNode(newNode);
                        if (newNode == goalNode) {
                            ant.antType = AntType.SUCCESSFUL;
                            successes +=  1;
                            stoppedAnts.add(ant);
                            ant.setColor(Color.GRAY);
                            if (runningAsVisualSimulation) {
                                UIContents.updateAntCounts();
                            }
                        }
                    }
                }
                catch(RuntimeException e) {
                    ant.antType = AntType.UNSUCCESSFUL;
                    stoppedAnts.add(ant);
                    ant.setColor(Color.GRAY);
                    if (runningAsVisualSimulation) {
                        UIContents.updateAntCounts();
                    }
                }
            }

            if (runningAsVisualSimulation && !iterationSkipped) {
                try {
                    Thread.sleep(Simulation.getAnimationDelay());
                } catch (InterruptedException e) {
                    System.out.println("Sleep Interrupted");
                }
            }
            stepsTravelled += 1;
        }
    }

    private void updateTrails() {
        for (Node node: globalVisited) {
            node.pheromoneCount *= pheromoneRetentionRate;
        }
        for (Ant a: ants) {
            if (a.getTrail().contains(goalNode)) {
                double contribution = pheromonePerAnt / a.getTrail().size();
                for (Node node : a.getTrail()) {
                    node.pheromoneCount += contribution;
                    trailNodes.add(node);
                }
            }
        }
    }

    private void clearTrails() {
        for (Node node: trailNodes) {
            node.pheromoneCount = 1;
        }
        trailNodes.clear();
    }

    private void updateBest() {
        for (Ant ant: ants) {
            if (ant.getAntType() == AntType.SUCCESSFUL) {
                if (bestTour == null) {
                    bestTour = ant.getTrail();
                }
                if (globalBestTour == null) {
                    globalBestTour = bestTour;
                }
                if (ant.getTrail().size() == bestTour.size()) {
                    numberOfBests += 1;
                }
                if (ant.getTrail().size() < bestTour.size()) {
                    bestTour = ant.getTrail();
                    numberOfBests = 1;
                    if (bestTour.size() < globalBestTour.size()) {
                        globalBestTour = bestTour;
                    }
                }
            }
        }
        if (runningAsVisualSimulation) {
            UIContents.updateBestTour();
        }
    }

    public List<Ant> getAnts() {
        return ants;
    }

    public HashSet<Node> getTrailNodes() {
        return trailNodes;
    }

    public int getIterationNumber() {
        return iterationNumber;
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getNumberOfAnts() {
        return numberOfAnts;
    }

    public int getSuccesses() {
        return successes;
    }

    public HashSet<Ant> getStoppedAnts() {
        return stoppedAnts;
    }

    public List<Node> getBestTour() {
        return bestTour;
    }

    public List<Node> getGlobalBestTour() {
        return globalBestTour;
    }

    public static void main(String[] args) throws IOException {
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
        myACO.setCreateResults(false);
        myACO.startOptimisation();
        System.out.println("\n---------------\nFINAL ANT POSITIONS:\n");
        myACO.printAntCurrentLoc();
    }
}
