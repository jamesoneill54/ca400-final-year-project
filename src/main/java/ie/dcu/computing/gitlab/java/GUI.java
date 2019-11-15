package ie.dcu.computing.gitlab.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI extends JFrame {

    private static final int CANVAS_WIDTH = 640;
    private static final int CANVAS_HEIGHT = 480;
    private AntEnvironment antEnvironment;

    GUI() {
        antEnvironment = new AntEnvironment();
        antEnvironment.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        Container contentPane = getContentPane();
        contentPane.add(antEnvironment);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("Ants");
        setVisible(true);
    }
}
