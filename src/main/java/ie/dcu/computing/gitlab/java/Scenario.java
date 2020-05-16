package ie.dcu.computing.gitlab.java;

import ie.dcu.computing.gitlab.java.ui.ScenariosPanel;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Scenario {

    public static final int SMALL_ENV = 10;
    public static final int MEDIUM_ENV = 30;
    public static final int LARGE_ENV = 50;
    public static final int HUGE_ENV = 70;
    private static final int[] ENVIRONMENT_SIZES = {SMALL_ENV, MEDIUM_ENV, LARGE_ENV, HUGE_ENV};

    private AntColonyOptimisation acoAlgorithm;
    private int environmentSize;
    private boolean randomiseHomeAndGoal = true;
    private int[] home;
    private int[] goal;
    private boolean randomiseObstacles = true;
    private int numberOfObstacles;
    private int[][] obstacles;
    private boolean randomScenario = false;
    private boolean stopScenario = false;
    private ScenariosPanel scenariosPanel;

    Scenario(AntColonyOptimisation acoAlgorithm) {
        this.acoAlgorithm = acoAlgorithm;
        randomiseAll();
        randomScenario = true;
    }

    Scenario(AntColonyOptimisation acoAlgorithm, int environmentSize, int numberOfObstacles) {
        this.acoAlgorithm = acoAlgorithm;
        this.environmentSize = environmentSize;
        this.numberOfObstacles = numberOfObstacles;
    }

    public Scenario(AntColonyOptimisation acoAlgorithm, int environmentSize, int[] home, int[] goal, int[][] obstacles) {
        this.acoAlgorithm = acoAlgorithm;
        this.environmentSize = environmentSize;
        this.home = home;
        this.goal = goal;
        this.obstacles = obstacles;
        this.randomiseObstacles = false;
        this.randomiseHomeAndGoal = false;
    }

    private String generateScenarioID() {
        String size = acoAlgorithm.getGraph().length + "x" + acoAlgorithm.getGraph()[0].length;
        String obstacles = String.valueOf(acoAlgorithm.getObstacles().size());
        String home = "h" + acoAlgorithm.getHome().getMatrixIndexX() + "~" + acoAlgorithm.getHome().getMatrixIndexY();
        String goal = "g" + acoAlgorithm.getGoal().getMatrixIndexX() + "~" + acoAlgorithm.getGoal().getMatrixIndexY();
        if (randomScenario) { return "RS_" + String.join("-", new String[] {size, obstacles, home, goal}); }
        else { return "S_" + String.join("-", new String[] {size, obstacles, home, goal}); }
    }

    private void randomiseAll() {
        Random random = new Random();
        this.environmentSize = ENVIRONMENT_SIZES[random.nextInt(ENVIRONMENT_SIZES.length)];
        this.numberOfObstacles = random.nextInt(10);
    }

    public void setupScenario() {
        acoAlgorithm.setRunID(this.generateScenarioID());
        acoAlgorithm.setEnvironmentSize(environmentSize, environmentSize);
        if (randomiseHomeAndGoal) {
            acoAlgorithm.setRandomHome();
            acoAlgorithm.setRandomGoal();
        }
        else {
            acoAlgorithm.setHome(home[0], home[1]);
            acoAlgorithm.setGoal(goal[0], goal[1]);
        }
        if (randomiseObstacles) {
            acoAlgorithm.setNumberOfObstacles(numberOfObstacles);
            acoAlgorithm.generateObstacles();
        }
        else {
            ArrayList<NodeGroup> generatedObstacles = new ArrayList<>();
            for (int[] obstacle: obstacles) {
                generatedObstacles.add(new NodeGroup(
                        NodeType.OBSTACLE,
                        obstacle[0],
                        obstacle[1],
                        obstacle[2],
                        obstacle[3],
                        acoAlgorithm.getGraph()));
            }
            acoAlgorithm.updateObstacles(generatedObstacles);
        }
    }

    public void runScenario() {
        setupScenario();
        System.out.println("Running Scenario " + generateScenarioID());
        long startTime = System.currentTimeMillis();
        int i = 0;
        for (int pheromoneImportance = 0; pheromoneImportance <= 400; pheromoneImportance += 100) {
            for (int distancePriority = 0; distancePriority <= 800; distancePriority += 100) {
                for (int numberOfAnts = 10; numberOfAnts <= 100; numberOfAnts += 20) {
                    if (stopScenario) {
                        System.out.println("Stopping Scenario");
                        break;
                    }
                    acoAlgorithm.setPheromoneImportance(pheromoneImportance);
                    acoAlgorithm.setDistancePriority(distancePriority);
                    acoAlgorithm.generateAnts(numberOfAnts);
                    try {
                        acoAlgorithm.startOptimisation();
                    }
                    catch (IOException e) {
                        System.out.println("Error writing to file in Scenario: " + e.getMessage());
                    }
                    if (scenariosPanel != null) {
                        scenariosPanel.updateStatus("Currently running the following scenario variables:\n" +
                                "Pheromone Importance: " + pheromoneImportance + "\n" +
                                "Distance Priorities: 0 -> 800 in steps of 100\n" +
                                "Number of Ants: 10 -> 100 in steps of 10\n" +
                                "Number of simulations run: " + i);
                    }
                    System.out.print(".");
                    i++;
                }
                if (stopScenario) {
                    break;
                }
            }
            System.out.println();
            if (stopScenario) {
                break;
            }
        }
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date runTime = new Date(System.currentTimeMillis() - startTime);
        System.out.println("Scenario finished in " + formatter.format(runTime));
        if (scenariosPanel != null) {
            scenariosPanel.updateStatus("Scenario finished in "+ formatter.format(runTime) + "\n" +
                    "Output to file pattern: " + acoAlgorithm.getResultsFolder() + acoAlgorithm.getResultsFilePattern());
        }
    }

    public void stopScenario() {
        stopScenario = true;
    }

    public static void runMultipleScenarios() {
        AntColonyOptimisation acoAlgorithm = new AntColonyOptimisation(10, 10, 10);
        Scenario[] scenarios = {
                new Scenario(acoAlgorithm,
                        Scenario.SMALL_ENV,
                        new int[] {0, 0},
                        new int[] {Scenario.SMALL_ENV - 1, Scenario.SMALL_ENV - 1},
                        new int[][] {}),
                new Scenario(acoAlgorithm,
                        Scenario.SMALL_ENV,
                        new int[] {0, 0},
                        new int[] {Scenario.SMALL_ENV - 1, Scenario.SMALL_ENV - 1},
                        new int[][] {{2, 2, 3, 4}}),
                new Scenario(acoAlgorithm,
                        Scenario.MEDIUM_ENV,
                        new int[] {5, 15},
                        new int[] {25, 15},
                        new int[][] {}),
                new Scenario(acoAlgorithm,
                        Scenario.MEDIUM_ENV,
                        new int[] {5, 15},
                        new int[] {25, 15},
                        new int[][] {{7, 10, 10, 5}, {7, 18, 10, 5}}),
                new Scenario(acoAlgorithm,
                        Scenario.LARGE_ENV,
                        new int[] {10, 24},
                        new int[] {40, 24},
                        new int[][] {}),
                new Scenario(acoAlgorithm,
                        Scenario.LARGE_ENV,
                        new int[] {10, 25},
                        new int[] {40, 25},
                        new int[][] {{25, 20, 10, 30}}),
                new Scenario(acoAlgorithm,
                        Scenario.HUGE_ENV,
                        new int[] {10, 35},
                        new int[] {60, 35},
                        new int[][] {}),
                new Scenario(acoAlgorithm,
                        Scenario.HUGE_ENV,
                        new int[] {10, 35},
                        new int[] {60, 35},
                        new int[][] {{25, 25, 20, 20}, {25, 50, 20, 20}})
        };
        for (Scenario scenario: scenarios) {
            scenario.runScenario();
        }
    }

    public static void runMultipleRandomScenarios() {
        AntColonyOptimisation acoAlgorithm = new AntColonyOptimisation(10, 10, 10);
        for (int i = 0; i < 8; i++) {
            Scenario randomScenario = new Scenario(acoAlgorithm);
            randomScenario.runScenario();
        }
    }

    public int getEnvironmentSize() {
        return environmentSize;
    }

    public int[] getHome() {
        return home;
    }

    public int[] getGoal() {
        return goal;
    }

    public int[][] getObstacles() {
        return obstacles;
    }

    public void setScenariosPanel(ScenariosPanel scenariosPanel) {
        this.scenariosPanel = scenariosPanel;
    }

    public static void main(String[] args) {
        runMultipleScenarios();
        runMultipleRandomScenarios();
        //Scenario scenario = new Scenario(new AntColonyOptimisation(10, 10, null));
        //scenario.runScenario();
    }
}
