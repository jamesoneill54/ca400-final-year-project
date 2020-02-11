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
    private double graph[][];
    private double trails[][];
    private List<Ant> ants = new ArrayList<>();
    private Random random = new Random();
    private double probabilities[];

    private int currentIndex;

    public int[] bestTourOrder;
    private double bestTourLength;

    public AntColonyOptimisation(int noOfNodes) {
        graph = generateRandomMatrix(noOfNodes);
        numberOfNodes = graph.length;
        numberOfAnts = (int) (numberOfNodes * antPerNode);

        trails = new double[numberOfNodes][numberOfNodes];
        probabilities = new double [numberOfNodes];
        IntStream.range(0, numberOfAnts)
                .forEach(i -> ants.add(new Ant(numberOfNodes)));
    }

    public double[][] generateRandomMatrix(int n) {
        double[][] randomMatrix = new double[n][n];
        IntStream.range(0, n)
                .forEach(i -> IntStream.range(0, n)
                    .forEach(j -> randomMatrix[i][j] = Math.abs(random.nextInt(100) + 1)));
        return randomMatrix;
    }

    public void startOptimization() {
        IntStream.rangeClosed(1, 8)
                .forEach((i -> {
                    System.out.println("Attempt #" + i);
                    solve();
                }));
    }

    public int[] solve() {
        setupAnts();
        clearTrails();
        IntStream.range(0, maxIterations)
                .forEach(i -> {
                    moveAnts();
                    updateTrails();
                    updateBest();
                });
        System.out.println("Best tour length: " + (bestTourLength - numberOfNodes));
        System.out.println("Best tour order: " + Arrays.toString(bestTourOrder));
        return bestTourOrder.clone();
    }

    public void setupAnts() {
        IntStream.range(0, numberOfAnts)
                .forEach(i -> {
                    ants.forEach(ant -> {
                        ant.clear();
                        ant.visitNode(-1, random.nextInt(numberOfNodes));
                    });
                });
        currentIndex = 0;
    }

    public void moveAnts() {
        IntStream.range(currentIndex, numberOfNodes -1)
                .forEach(i -> {
                ants.forEach(ant -> ant.visitNode(currentIndex, selectNextNode(ant)));
            currentIndex ++;
        });
    }

    private int selectNextNode(Ant ant) {
        int t = random.nextInt(numberOfNodes - currentIndex);
        if (random.nextDouble() < randomFactor) {
            OptionalInt nodeIndex = IntStream.range(0, numberOfNodes)
                    .filter(i -> i == t && !ant.visited(i))
                    .findFirst();
            if (nodeIndex.isPresent()) {
                return nodeIndex.getAsInt();
            }
        }
        calculateProbabilities(ant);
        double r = random.nextDouble();
        double total = 0;
        for (int i = 0; i < numberOfNodes; i++) {
            total += probabilities[i];
            if (total >= r) {
                return i;
            }
        }

        throw new RuntimeException("There are no other nodes");
    }

    public void calculateProbabilities(Ant ant) {
        int i = ant.trail[currentIndex];
        double pheromone = 0.0;
        for (int l = 0; l < numberOfNodes; l++) {
            if (!ant.visited(l)) {
                pheromone += Math.pow(trails[i][l], pheromoneImportance) * Math.pow(1.0 / graph[i][l], distancePriority);
            }
        }
        for (int j = 0; j < numberOfNodes; j++) {
            if (ant.visited(j)) {
                probabilities[j] = 0.0;
            } else {
                double numerator =  Math.pow(trails[i][j], pheromoneImportance) * Math.pow(1.0 / graph[i][j], distancePriority);
                probabilities[j] = numerator / pheromone;
            }
        }
    }

    private void updateTrails() {
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                trails [i][j] *= evaporationRate;
            }
        }
        for (Ant a : ants) {
            double contribution = pheromonePerAnt / a.trailLength(graph);
            for (int i = 0; i < numberOfNodes - 1; i++) {
                trails[a.trail[i]][a.trail[i + 1]] += contribution;
            }
            trails[a.trail[numberOfNodes - 1]][a.trail[0]] += contribution;
        }
    }

    private void updateBest() {
        if (bestTourOrder == null) {
            if (bestTourOrder == null) {
                bestTourOrder = ants.get(0).trail;
                bestTourLength = ants.get(0)
                        .trailLength(graph);
            }
            for (Ant a : ants) {
                if (a.trailLength(graph) < bestTourLength) {
                    bestTourLength = a.trailLength(graph);
                    bestTourOrder = a.trail.clone();
                }
            }
        }
    }

    private void clearTrails() {
        IntStream.range(0, numberOfNodes)
                .forEach(i -> {
                    IntStream.range(0, numberOfNodes)
                            .forEach((j -> trails[i][j] = initialTrails));
                });
    }

    public static void main(String[] args) {
        System.out.println("Running Ant Colony Optimisation Algorithm...");
        AntColonyOptimisation myACO = new AntColonyOptimisation(40);
        myACO.startOptimization();
    }

}
