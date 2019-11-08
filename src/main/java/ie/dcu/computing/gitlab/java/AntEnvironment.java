package ie.dcu.computing.gitlab.java;

import java.awt.*;

public class AntEnvironment extends Canvas {

    public AntEnvironment() {
        setBackground(Color.BLACK);
        setSize(300, 300);
    }

    public void paint (Graphics graphics) {
        Graphics2D graphics2D;
        graphics2D = (Graphics2D) graphics;
        graphics2D.drawString("Ant environment for the ants to frolic in.", 70, 70);
    }

}
