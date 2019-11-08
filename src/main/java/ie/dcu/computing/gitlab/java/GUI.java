package ie.dcu.computing.gitlab.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    private JFrame frame;
    private JButton button;
    private Panel controlPanel;

    GUI(int frameWidth, int frameHeight) {
        this.frame = new JFrame("Ants!");
        this.controlPanel = new Panel();
        createFrame(frameWidth, frameHeight);
    }

    private void createFrame(int frameWidth, int frameHeight) {
        this.button = new JButton("Click here for Ants!");
        final JLabel label = new JLabel();
        label.setBounds(50, 100, 200, 30);
        this.button.setBounds(100, 200, 200, 40);

        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Ants! Ants everywhere!");
                label.setVisible(true);
            }
        });

        createControlPanel();

        this.frame.add(button);
        this.frame.add(label);
        this.frame.add(controlPanel);
        this.frame.setSize(frameWidth, frameHeight);
        this.frame.setLayout(null);
    }

    private void createControlPanel() {
        this.controlPanel.setLayout(new FlowLayout());
        controlPanel.add(new AntEnvironment());
    }

    void displayGUI() {
        this.frame.setVisible(true);
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public JButton getButton() {
        return this.button;
    }

    public Panel getControlPanel() {
        return this.controlPanel;
    }
}
