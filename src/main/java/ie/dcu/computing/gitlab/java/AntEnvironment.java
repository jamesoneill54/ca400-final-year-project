package ie.dcu.computing.gitlab.java;

import javax.swing.*;
import java.awt.*;

public class AntEnvironment extends JPanel {
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        setBackground(Color.WHITE);

        for (int j = 0; j < 50; j++) {
            for (int i = 0; i < 50; i++) {
                Color antColor;
                if (i % 2 == 0) {
                    antColor = Color.RED;
                }
                else {
                    antColor = Color.BLUE;
                }
                drawAnt(graphics, antColor, 20 + (i * 15), 20 + (j * 15));
            }
        }
    }

    private void drawAnt(Graphics graphics, Color antColor, int antX, int antY) {
        graphics.setColor(antColor);
        // The ant's head.
        graphics.drawOval(antX, antY - 2, 3, 2);
        graphics.fillOval(antX, antY - 2, 3, 2);
        // The ant's body.
        graphics.drawOval(antX, antY, 3, 4);
        graphics.fillOval(antX, antY, 3, 4);
        // The ant's left three legs.
        graphics.drawLine(antX - 1, antY, antX - 2, antY - 1);
        graphics.drawLine(antX - 1, antY + 2, antX - 2, antY + 2);
        graphics.drawLine(antX - 1, antY + 4, antX - 2, antY + 5);
        // The ant's right three legs.
        graphics.drawLine(antX + 4, antY, antX + 5, antY - 1);
        graphics.drawLine(antX + 4, antY + 2, antX + 5, antY + 2);
        graphics.drawLine(antX + 4, antY + 4, antX + 5, antY + 5);
    }
}
