package ie.dcu.computing.gitlab.java;

import java.awt.*;
import java.awt.event.KeyEvent;

public class AnimationAnt {

    private Color color;
    private int x;
    private int y;
    private int width = 7;
    private int height = 7;

    public AnimationAnt(Color antColor, int x, int y) {
        this.color = antColor;
        this.x = x;
        this.y = y;
    }

    public AnimationAnt(Color antColor) {
        this.color = antColor;
        this.x = 0;
        this.y = 0;
    }

    public AnimationAnt(int x, int y) {
        this.color = Color.BLUE;
        this.x = x;
        this.y = y;
    }

    public AnimationAnt() {
        this.color = Color.BLUE;
        this.x = 0;
        this.y = 0;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color newColor) {
        color = newColor;
    }

    public int getX() {
        return x;
    }

    public void setX(int newX) {
        x = newX;
    }

    public int getY() {
        return y;
    }

    public void setY(int newY) {
        y = newY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void drawAnt(Graphics graphics) {
        graphics.setColor(color);
        // The ant's head.
        graphics.drawOval(x + 2, y, 3, 2);
        graphics.fillOval(x + 2, y, 3, 2);
        // The ant's body.
        graphics.drawOval(x + 2, y + 2, 3, 4);
        graphics.fillOval(x + 2, y + 2, 3, 4);
        // The ant's left three legs.
        graphics.drawLine(x + 2, y + 2, x, y + 1);
        graphics.drawLine(x + 2, y + 4, x, y + 4);
        graphics.drawLine(x + 2, y + 6, x, y + 7);
        // The ant's right three legs.
        graphics.drawLine(x + 5, y + 2, x + 7, y + 1);
        graphics.drawLine(x + 5, y + 4, x + 7, y + 4);
        graphics.drawLine(x + 5, y + 6, x + 7, y + 7);
    }

}
