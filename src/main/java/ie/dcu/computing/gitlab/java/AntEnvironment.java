package ie.dcu.computing.gitlab.java;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AntEnvironment extends JPanel {

    private static final int ENVIRONMENT_WIDTH = 640;
    private static final int ENVIRONMENT_HEIGHT = 480;

    private AnimationAnt ant;

    public AntEnvironment() {
        initEnvironment();
    }

    private void initEnvironment() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(ENVIRONMENT_WIDTH, ENVIRONMENT_HEIGHT));
        ant = new AnimationAnt();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        ant.drawAnt(graphics);
        Toolkit.getDefaultToolkit().sync();
    }

    AnimationAnt getAnt() {
        return ant;
    }
}
