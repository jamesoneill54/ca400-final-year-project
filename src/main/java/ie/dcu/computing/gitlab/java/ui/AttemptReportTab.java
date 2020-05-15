package ie.dcu.computing.gitlab.java.ui;

import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.swing.*;
import java.awt.*;

public class AttemptReportTab extends JPanel {

    private final String[] fields = {"Attempt_Number", "HOME_NODE", "GOAL_NODE", "Pheromone_Importance",
            "Distance_Priority", "Number_of_Obstacles", "Calculated_Optimum_Tour_Length",
            "Calculated_Optimum_Length", "Global_Best_Tour_Length",
            "Iteration_When_Global_Best_Reached", "Proximity_To_Calculated_Optimum"};
    private final String[] labelStrings = {"Attempt Number:", "Home Node:", "Goal Node:", "Pheromone Importance:",
            "Distance Priority:", "Number of Obstacles:", "Calculated Optimum Tour Length:",
            "Calculated Optimum Length:", "Global Best Tour Length:",
            "Iteration When Global Best Reached:", "Proximity To Calculated Optimum:"};

    AttemptReportTab(ObjectNode attemptReport) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.setLayout(new GridBagLayout());
        for (int i = 0; i < fields.length; i++) {
            if (attemptReport.has(fields[i])) {
                UILabel fieldLabel = new UILabel(labelStrings[i], "identifier");
                fieldLabel.setPreferredSize(new Dimension(300, 20));
                UILabel fieldValue = new UILabel(attemptReport.get(fields[i]).toString(), "report");
                fieldValue.setPreferredSize(new Dimension(100, 20));
                add(fieldLabel, constraints);
                constraints.gridx += 1;
                add(fieldValue, constraints);
                constraints.gridx = 0;
                constraints.gridy++;
            }
        }
        setVisible(true);
    }
}
