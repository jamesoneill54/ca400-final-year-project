package ie.dcu.computing.gitlab.java;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerformanceLogger {
    private FileWriter fileWriter;
    private PrintWriter printWriter;
    protected int firstBestLength = 0;
    private HashMap<Integer, Integer[]> bestAntsPerIteration = new HashMap<>();
    protected int globalBestLength = 0;

    public PerformanceLogger(String fileName) throws IOException {
        fileWriter = new FileWriter(fileName, true);
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

    public void formatResults(List<Ant> ants, int iterationNum) {
        printWriter.println("Iteration: " + (iterationNum));
        printWriter.println("|\tANT\t|\tSUCCESSFUL/UNSUCCESSFUL\t|\tTRAIL LENGTH\t|\tTRAIL\t");
        for (Ant ant : ants) {
            printWriter.println("|-------|---------------------------|-------------------|-----------------");
            printWriter.print("|\t" + ants.indexOf(ant) + "\t");
            printWriter.print("|\t" + ant.antType + "       \t\t");
            printWriter.print("|\t" + ant.trail.size() + "\t\t\t\t");
            printWriter.print("|\t" + ant.trail + "\t\n");
        }
        printWriter.println("|-------|---------------------------|-------------------|-----------------\n");
    }

    public void formatResults(List<Ant> ants, int iterationNum, int numberOfBests, List<Node> bestTrail, int successes) {
        printWriter.println("End of iteration " + (iterationNum));
        printWriter.println("BEST TRAIL LENGTH: " + bestTrail.size());
        setGlobalBestLength(bestTrail.size(), iterationNum);
        printWriter.println("Number of ants with the best trail: " + numberOfBests);
        if (bestTrail.size() < globalBestLength) {
            firstBestLength = iterationNum;
        }
        Integer[] iterBests = new Integer[2];
        iterBests[0] = bestTrail.size();
        iterBests[1] = numberOfBests;
        bestAntsPerIteration.put(iterationNum, iterBests);
        printWriter.println("Number of successful ants: " + successes);
        printWriter.println("Number of unsuccessful ants: " + (ants.size() - successes));
        printWriter.println();
    }

    public void initialPrint(Node homeNode, Node goalNode, int numberOfObstacles) {
        printWriter.println("Results for Run number 1");
        printWriter.println("HOME NODE\t|\t" + homeNode.toString());
        printWriter.println("GOAL NODE\t|\t" + goalNode.toString());
        printWriter.println("OBSTACLES\t|\t" + numberOfObstacles);
    }

    public void finalPrint() {
        printWriter.println("END OF ATTEMPT - SUMMARY");
        printWriter.println("Best tour length: " + globalBestLength);
        printWriter.println("Iteration when best reached: " + firstBestLength);
        printWriter.println("Number of ants with the best trail per iteration:");
        for (Map.Entry<Integer, Integer[]> entry : bestAntsPerIteration.entrySet()) {
            if (entry.getValue()[0] == globalBestLength) {
                printWriter.println("Iteration " + entry.getKey() + ": " + entry.getValue()[1]);
            } else {
                printWriter.println("Iteration " + entry.getKey() + ": 0");
            }
        }
        printWriter.println("====================================================");
    }

    public void close() {
        printWriter.close();
    }
}
