package ie.dcu.computing.gitlab.java;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PerformanceLogger {
    private PrintWriter printWriter;
    protected int firstBestLength = 0;
    private HashMap<Integer, Integer[]> bestAntsPerIteration = new HashMap<>();
    protected int globalBestLength = 0;
    // Dummy value for optimumLength
    private int optimumLength = 10;

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
            bestAntsPerIteration.clear();
        }
    }

    private void printAsJson(String string, String value, boolean last) {
        if (value.equals("[") || value.equals("{")) {
            printWriter.println(indent + "\""+string+"\": "+ value);
            indentSize += 1;
            indent = new String(new char[indentSize]).replace("\0", "    ");
        }
        else if (value.equals("]") || value.equals("}")) {
            indentSize -= 1;
            indent = new String(new char[indentSize]).replace("\0", "    ");
            printWriter.println(indent + "\""+string+"\": "+ value + ",");
        }
        else if (last) { printWriter.println(indent + "\""+string+"\": \""+value+"\""); }
        else { printWriter.println(indent + "\""+string+"\": \""+value+"\"," ); }
    }

    private void printAsJson(String string, int value, boolean last) {
        if (last) { printWriter.println(indent + "\""+string+"\": " + value ); }
        else { printWriter.println(indent + "\""+string+"\": "+ value + ","); }
    }

    private void printAsJson(String string, double value, boolean last) {
        if (last) { printWriter.println(indent + "\""+string+"\": " + value ); }
        else { printWriter.println(indent + "\""+string+"\": "+ value + ","); }
    }

    public void formatResults(List<Ant> ants, int iterationNum) {
        printWriter.println(indent + "{");
        printAsJson("IterationNum", iterationNum, false);
        printAsJson("ants", "[", false);
        for (Ant ant : ants) {
            printWriter.println(indent +  "{");
            printAsJson("Ant Number", ants.indexOf(ant), false);
            printAsJson("Successful/Unsuccessful", ant.antType.toString(), false);
            printAsJson("Trail length", ant.trail.size(), false);
            printAsJson("Trail", ant.trail.toString(), true);
            if (ants.indexOf(ant) == ants.size() - 1) {
                printWriter.println(indent + "}");
            } else { printWriter.println(indent + "},"); };
        }
        indentSize -= 1;
        indent = new String(new char[indentSize]).replace("\0", "    ");
        printWriter.println(indent + "],");
    }

    public void formatResults(List<Ant> ants, int iterationNum, int maxIterations, int numberOfBests, List<Node> bestTrail, int successes) {
        printWriter.println();
        printAsJson("BEST TRAIL LENGTH" , bestTrail.size(), false);
        setGlobalBestLength(bestTrail.size(), iterationNum);
        printAsJson("Number of ants with the best trail", numberOfBests, false);
        if (bestTrail.size() < globalBestLength) {
            firstBestLength = iterationNum;
        }
        Integer[] iterBests = new Integer[2];
        iterBests[0] = bestTrail.size();
        iterBests[1] = numberOfBests;
        bestAntsPerIteration.put(iterationNum, iterBests);
        printAsJson("Number of successful ants", successes, false);
        printAsJson("Number of unsuccessful ants", (ants.size() - successes), true);
        if (iterationNum == maxIterations - 1) { printWriter.println(indent + "}"); }
        else { printWriter.println(indent + "},"); }
        printWriter.println();
    }

    public void initialPrint(int attemptNum, Node homeNode, Node goalNode, int numberOfObstacles, double pheromoneImportance, double distancePriority) {
        printWriter.println("{");
        printAsJson("AttemptNumber", attemptNum, false);
        printWriter.println();
        printAsJson("HOME NODE", homeNode.toString(), false);
        printAsJson("GOAL NODE", goalNode.toString(), false);
        printAsJson("OPTIMUM LENGTH", optimumLength, false);
        printAsJson("OBSTACLES", numberOfObstacles, false);
        printAsJson("Pheromone Importance", pheromoneImportance, false);
        printAsJson("Distance Priority", distancePriority, false);
        printWriter.println();
        printAsJson("Iterations", "[", false);
    }

    public void finalPrint(List<Node> globalBestTour) {
        indentSize -= 1;
        indent = new String(new char[indentSize]).replace("\0", "    ");
        printWriter.println(indent + "],");
        printAsJson("Best tour length",  globalBestLength, false);
        printAsJson("Best tour order: ", globalBestTour.toString(), false);
        printAsJson("Iteration when best reached: ", firstBestLength, false);
        printAsJson("Number of ants with the best trail per iteration:", "[", false);
        for (Map.Entry<Integer, Integer[]> entry : bestAntsPerIteration.entrySet()) {
            if (entry.getValue()[0] == globalBestLength) {
                printWriter.print(indent + entry.getValue()[1]);
            } else {
                printWriter.print(indent + 0);
            }
            if (entry.getKey() == bestAntsPerIteration.entrySet().size()) { printWriter.print("\n"); }
            else { printWriter.print(",\n"); }
        }
        indentSize -= 1;
        indent = new String(new char[indentSize]).replace("\0", "    ");
        printWriter.println(indent + "]");
        printWriter.println("}");
    }

    public void close() {
        printWriter.close();
    }
}
