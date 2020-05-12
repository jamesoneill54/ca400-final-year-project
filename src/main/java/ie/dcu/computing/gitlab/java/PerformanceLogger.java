package ie.dcu.computing.gitlab.java;

import java.io.*;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;


public class PerformanceLogger {
    private PrintWriter printWriter;
    protected int firstBestLength = 0;
    private LinkedHashMap<Integer, Integer[]> bestAntsPerIteration = new LinkedHashMap<>();
    protected int globalBestLength = 0;

    private int indentSize = 1;
    private String indent = new String(new char[indentSize]).replace("\0", "    ");

    public PerformanceLogger(String fileName) throws IOException {

        FileWriter fileWriter = new FileWriter(fileName, true);
        printWriter = new PrintWriter(fileWriter);
    }

    public void setFirstBestLength(int iterationNumber) {
        this.firstBestLength = iterationNumber;
    }

    public void setGlobalBestLength(int bestCandidate, int iterationNumber) {
        if (globalBestLength == 0) {
            globalBestLength = bestCandidate;
        }
        if (bestCandidate < globalBestLength) {
            globalBestLength = bestCandidate;
            setFirstBestLength(iterationNumber);
            for (Map.Entry<Integer, Integer[]> entry : bestAntsPerIteration.entrySet()) {
                Integer[] n = new Integer[2];
                n[0] = entry.getKey();
                n[1] = 0;
                entry.setValue(n);
            }
        }
    }

    private void printAsJson(String string, String value, boolean last) {
        if (value.equals("[") || value.equals("{")) {
            printWriter.print(" \""+string+"\": "+ value);
        }
        else if (value.equals("]") || value.equals("}")) {
            printWriter.print(indent + "\""+string+"\": "+ value + ",");
        }
        else if (last) { printWriter.print(" \""+string+"\": \""+value+"\""); }
        else { printWriter.print(" \""+string+"\": \""+value+"\"," ); }
    }

    private void printAsJson(String string, int value, boolean last) {
        if (last) { printWriter.print(" \""+string+"\": " + value ); }
        else { printWriter.print(" \""+string+"\": "+ value + ","); }
    }

    private void printAsJson(String string, double value, boolean last) {
        if (last) { printWriter.print(" \""+string+"\": " + value ); }
        else { printWriter.print(" \""+string+"\": "+ value + ","); }
    }

    public void formatResults(String timestamp, int attemptNum, Node homeNode, Node goalNode, double pheromoneImportance, double distancePriority, List<NodeGroup> obstacles) {
        /*
        Initial format; run before start of attempt
        */
        printWriter.println("{ \"index\" : { \"_index\" :  \"testresult\", \"_id\" : \"" + timestamp + "-" + attemptNum + "\" } }");
        printWriter.print("{");
        printAsJson("Attempt_Number", attemptNum, false);
        printAsJson("HOME_NODE", homeNode.toString(), false);
        printAsJson("GOAL_NODE", goalNode.toString(), false);
        printAsJson("Pheromone_Importance", pheromoneImportance, false);
        printAsJson("Distance_Priority", distancePriority, false);
        printAsJson("Number_of_Obstacles", obstacles.size(), false);
        if (obstacles.size() > 0) {
            formatObstacles(obstacles);
        }
//        printAsJson("Iterations", "[", false);
    }

    public void formatObstacles(List<NodeGroup> obstacles) {
        /*
        Objects format; run before start of attempt
        */
        for (NodeGroup obstacle : obstacles) {
            printAsJson("Obstacle_" + obstacles.indexOf(obstacle), "[", false);
            printWriter.print((int)obstacle.getX() + ",");
            printWriter.print((int)obstacle.getY() + ",");
            printWriter.print((int)obstacle.getWidth() + ",");
            printWriter.print((int)obstacle.getHeight());
            printWriter.print(" ],");
        }
    }

    public void formatResults(List<Ant> ants, int iterationNum) {
        /*
        Ants format; run throughout each iteration
        */
        printAsJson("Iteration_" + iterationNum, "[", false);
//        printAsJson("Ants", "[", false);
//        for (Ant ant : ants) {
//            printWriter.print("{ ");
//            printAsJson("Ant_Number", ants.indexOf(ant), false);
//            printAsJson("Ant_Status", ant.antType.toString(), false);
//            printAsJson("Trail_Length", ant.trail.size(), false);
//            printAsJson("Trail_Order", ant.trail.toString(), true);
//            if (ants.indexOf(ant) == ants.size() - 1) {
//                printWriter.print(indent + "} ");
//            } else { printWriter.print("}, "); };
//        }
//        printWriter.print("], ");
    }

    public void formatResults(List<Ant> ants, int iterationNum, int maxIterations, int numberOfBests, List<Node> bestTrail, int successes) {
        /*
        Iteration format; run at end of an iteration
        */
        printWriter.print((int)bestTrail.size() + ", ");
        printWriter.print((int)successes + ", ");
        printWriter.print((int)ants.size() - successes);
//        printAsJson("Iteration_Best_Trail_Order", bestTrail.toString(), false);
//        printAsJson("Iteration_Best_Trail_Size" , bestTrail.size(), false);
        setGlobalBestLength((int) bestTrail.size(), iterationNum);
//        printAsJson("Number_Of_Best_Trails_Found", numberOfBests, false);
        if (bestTrail.size() < globalBestLength) {
            firstBestLength = iterationNum;
        }
        Integer[] iterBests = new Integer[2];
        if (bestTrail.size() != Double.POSITIVE_INFINITY) {
            iterBests[0] = (int) bestTrail.size();
            iterBests[1] = numberOfBests;
        }
        bestAntsPerIteration.put(iterationNum, iterBests);
//        printAsJson("Successful_Ants", successes, false);
//        printAsJson("Unsuccessful_Ants", (ants.size() - successes), true);
//        if (iterationNum == maxIterations) { printWriter.print("} "); }
//        else { printWriter.print("}, "); }
        printWriter.print("], ");
    }

    public void formatResults(LinkedHashSet<Node> precalculatedOptimumTour, List<Node> globalBestTour) {
        /*
        Final format; run at end of an attempt, after last iteration complete
        */
//        printWriter.print("],");
        if (globalBestTour != null) {
            printAsJson("Calculated_Optimum_Tour_Order", precalculatedOptimumTour.toString(), false);
            printAsJson("Calculated_Optimum_Length", precalculatedOptimumTour.size(), false);
            printAsJson("Global_Best_Tour_Order: ", globalBestTour.toString(), false);
            printAsJson("Global_Best_Tour_Length",  globalBestLength, false);
            printAsJson("Iteration_When_Global_Best_Reached: ", firstBestLength, false);
            printAsJson("Global_Best_Tours_Per_Iteration:", "[", false);
        }
        else {
            printAsJson("Global_Best_Tour_Order: ", "Not found", false);
            printAsJson("Global_Best_Tour_Length",  "Not found", false);
            printAsJson("Iteration_When_Global_Best_Reached: ", "Not found", false);
            printAsJson("Global_Best_Tours_Per_Iteration:", "[", false);
        }
        for (Map.Entry<Integer, Integer[]> entry : bestAntsPerIteration.entrySet()) {
            if (entry.getValue()[0] != null && entry.getValue()[0] == globalBestLength) {
                printWriter.print(" " + entry.getValue()[1]);
            } else {
                printWriter.print(" " + 0);
            }
            if (entry.getKey() < bestAntsPerIteration.entrySet().size()) { printWriter.print(","); }
        }
        printWriter.print(" ], ");
        printAsJson("Proximity_To_Calculated_Optimum", Math.abs(globalBestLength - precalculatedOptimumTour.size()), true);
        printWriter.print("}\n");
    }

    public void close() {
        printWriter.close();
    }
}
