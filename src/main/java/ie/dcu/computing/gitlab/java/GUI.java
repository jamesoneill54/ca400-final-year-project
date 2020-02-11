package ie.dcu.computing.gitlab.java;

import javax.swing.*;

class GUI extends JFrame {

    AntEnvironment antEnvironment;

    GUI() {
        antEnvironment = new AntEnvironment();
        add(antEnvironment);
        setResizable(false);
        pack();

        setTitle("Ants");
        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public AntEnvironment getAntEnvironment() {
        return antEnvironment;
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setVisible(true);
    }
}
