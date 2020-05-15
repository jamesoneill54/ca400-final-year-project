package ie.dcu.computing.gitlab.java.ui;

import com.fasterxml.jackson.databind.node.ObjectNode;
import ie.dcu.computing.gitlab.java.AntColonyOptimisation;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Report {

    private AntColonyOptimisation acoAlgorithm;
    private ArrayList<ObjectNode> report;

    public Report(AntColonyOptimisation acoAlgorithm) {
        this.acoAlgorithm = acoAlgorithm;
    }

    private void updateReport() {
        report = new ArrayList<>();
        for (int attemptNumber = 1; attemptNumber <= acoAlgorithm.getMaxAttempts(); attemptNumber++) {
            File attemptFile = new File(acoAlgorithm.getResultsFolder() +
                    acoAlgorithm.getResultsFilePattern() +
                    "_ATTEMPT" + attemptNumber + ".json");
            try {
                BufferedReader reader = new BufferedReader(new FileReader(attemptFile));
                ObjectMapper mapper = new ObjectMapper();
                report.add(mapper.readValue(reader.readLine(), ObjectNode.class));
            }
            catch (FileNotFoundException e) {
                System.out.println("Could not find file for attempt no." + attemptNumber + ": " + e.getMessage());
                break;
            }
            catch (IOException e) {
                System.out.println("Could not read json in file '" + attemptFile.getName() + "': " + e.getMessage());
            }
        }
    }

    public void displayReportFrame() {
        updateReport();
        JFrame reportFrame = new JFrame();
        reportFrame.setMinimumSize(new Dimension(500, 500));
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setResizable(true);
        reportFrame.setTitle("Simulation Report: " + acoAlgorithm.getResultsFilePattern());
        JTabbedPane reportTabs = new JTabbedPane();
        int attemptNumber = 1;
        for (ObjectNode attemptReport: report) {
            AttemptReportTab attemptReportTab = new AttemptReportTab(attemptReport);
            reportTabs.add("Attempt " + attemptNumber, attemptReportTab);
            attemptNumber++;
        }
        reportFrame.add(reportTabs);
        reportFrame.setVisible(true);
    }
}
