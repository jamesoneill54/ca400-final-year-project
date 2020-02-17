package ie.dcu.computing.gitlab.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;

public class AntColonyOptimisation {
    private double initialTrails = 1.0;
    private double pheromoneImportance = 1;
    private double distancePriority = 5;
    private double evaporationRate = 0.5;
    private double pheromonePerAnt = 500;
    private double antPerNode = 0.8;
    private double randomFactor = 0.01;

    private int maxIterations = 1000;

    private int numberOfNodes;
    private int numberOfAnts;
    private Node graph[][];
    private Node homeNode;
    private Node goalNode;
    private double trails[][];
    private List<Ant> ants = new ArrayList<>();
    private Random random = new Random();
    private double probabilities[];

    private int currentIndex;

    public int[] bestTourOrder;
    private double bestTourLength;

    public AntColonyOptimisation(int h, int w) {
        graph = generateMatrixFromEnv(h, w);
        numberOfNodes = graph.length;
        numberOfAnts = (int) (numberOfNodes * antPerNode);

        trails = new double[numberOfNodes][numberOfNodes];
        probabilities = new double [numberOfNodes];
        IntStream.range(0, numberOfAnts)
                .forEach(i -> ants.add(new Ant(numberOfNodes)));
    }

    public Node[][] generateMatrixFromEnv(int height, int width) {
        Node[][] matrix = new Node[height][width];
        IntStream.range(0, height)
                .forEach(i -> IntStream.range(0, width)
                        .forEach(j -> matrix[i][j] = new Node(i, j)));
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

    public Node setHome(int x, int y) {
        if (x <= AntEnvironment.ENVIRONMENT_HEIGHT && y <= AntEnvironment.ENVIRONMENT_WIDTH) {
            this.homeNode = graph[x][y];
            return this.homeNode.getCoOrdinates();
        }
        return null;
    }

    public Node setGoal(int x, int y) {
        if (x <= AntEnvironment.ENVIRONMENT_HEIGHT && y <= AntEnvironment.ENVIRONMENT_WIDTH) {
            this.goalNode = graph[x][y];
            return this.goalNode.getCoOrdinates();
        }
        return null;
    }

//    public void startOptimization() {
//        IntStream.rangeClosed(1, 8)
//                .forEach((i -> {
//                    System.out.println("Attempt #" + i);
//                    solve();
//                }));
//    }
//
//    public int[] solve() {
//        setupAnts();
//        clearTrails();
//        IntStream.range(0, maxIterations)
//                .forEach(i -> {
//                    moveAnts();
//                    updateTrails();
//                    updateBest();
//                });
//        System.out.println("Best tour length: " + (bestTourLength - numberOfNodes));
//        System.out.println("Best tour order: " + Arrays.toString(bestTourOrder));
//        return bestTourOrder.clone();
//    }
//
//    public void setupAnts() {
//        IntStream.range(0, numberOfAnts)
//                .forEach(i -> {
//                    ants.forEach(ant -> {
//                        ant.clear();
//                        ant.visitNode(-1, random.nextInt(numberOfNodes));
//                    });
//                });
//        currentIndex = 0;
//    }
//
//    public void moveAnts() {
//        IntStream.range(currentIndex, numberOfNodes -1)
//                .forEach(i -> {
//                ants.forEach(ant -> ant.visitNode(currentIndex, selectNextNode(ant)));
//            currentIndex ++;
//        });
//    }
//
//    private int selectNextNode(Ant ant) {
//        int t = random.nextInt(numberOfNodes - currentIndex);
//        if (random.nextDouble() < randomFactor) {
//            OptionalInt nodeIndex = IntStream.range(0, numberOfNodes)
//                    .filter(i -> i == t && !ant.visited(i))
//                    .findFirst();
//            if (nodeIndex.isPresent()) {
//                return nodeIndex.getAsInt();
//            }
//        }
//        calculateProbabilities(ant);
//        double r = random.nextDouble();
//        double total = 0;
//        for (int i = 0; i < numberOfNodes; i++) {
//            total += probabilities[i];
//            if (total >= r) {
//                return i;
//            }
//        }
//        throw new RuntimeException("There are no other nodes");
//    }
//
//    public void calculateProbabilities(Ant ant) {
//        int i = ant.trail[currentIndex];
//        double pheromone = 0.0;
//        for (int l = 0; l < numberOfNodes; l++) {
//            if (!ant.visited(l)) {
//                pheromone += Math.pow(trails[i][l], pheromoneImportance) * Math.pow(1.0 / graph[i][l], distancePriority);
//            }
//        }
//        for (int j = 0; j < numberOfNodes; j++) {
//            if (ant.visited(j)) {
//                probabilities[j] = 0.0;
//            } else {
//                double numerator =  Math.pow(trails[i][j], pheromoneImportance) * Math.pow(1.0 / graph[i][j], distancePriority);
//                probabilities[j] = numerator / pheromone;
//            }
//        }
//    }
//
//    private void updateTrails() {
//        for (int i = 0; i < numberOfNodes; i++) {
//            for (int j = 0; j < numberOfNodes; j++) {
//                trails [i][j] *= evaporationRate;
//            }
//        }
//        for (Ant a : ants) {
//            double contribution = pheromonePerAnt / a.trailLength(graph);
//            for (int i = 0; i < numberOfNodes - 1; i++) {
//                trails[a.trail[i]][a.trail[i + 1]] += contribution;
//            }
//            trails[a.trail[numberOfNodes - 1]][a.trail[0]] += contribution;
//        }
//    }
//
//    private void updateBest() {
//        if (bestTourOrder == null) {
//            if (bestTourOrder == null) {
//                bestTourOrder = ants.get(0).trail;
//                bestTourLength = ants.get(0)
//                        .trailLength(graph);
//            }
//            for (Ant a : ants) {
//                if (a.trailLength(graph) < bestTourLength) {
//                    bestTourLength = a.trailLength(graph);
//                    bestTourOrder = a.trail.clone();
//                }
//            }
//        }
//    }
//
//    private void clearTrails() {
//        IntStream.range(0, numberOfNodes)
//                .forEach(i -> {
//                    IntStream.range(0, numberOfNodes)
//                            .forEach((j -> trails[i][j] = initialTrails));
//                });
//    }


    public static void main(String[] args) {
        System.out.println("Running Ant Colony Optimisation Algorithm...");
        AntColonyOptimisation myACO = new AntColonyOptimisation(AntEnvironment.ENVIRONMENT_HEIGHT, AntEnvironment.ENVIRONMENT_WIDTH);
        try {
            myACO.setHome(2,2);
            myACO.setGoal(12, 20);
            myACO.printMatrix(myACO.graph);
            System.out.println("Goal is: ");
            myACO.goalNode.printNode();
            System.out.println();
            System.out.println("Goal's X value is: " + myACO.goalNode.getX());
            System.out.println("Goal's Y value is: " + myACO.goalNode.getY());
        } catch (NullPointerException e) {
            System.out.println("ERROR: Goal Node coordinates outside of environment scope. Respecify Goal Node value(s).");
        }
    }

}
