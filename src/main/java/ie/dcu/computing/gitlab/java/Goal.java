package ie.dcu.computing.gitlab.java;

import java.awt.*;

public class Goal implements EnvironmentObject {

    private int x;
    private int y;
    private int width;
    private int height;

    public Goal(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setX(int newX) {
        x = newX;
    }

    public int getX() {
        return x;
    }

    public void setY(int newY) {
        y = newY;
    }

    public int getY() {
        return y;
    }

    public void setLocation(int newX, int newY) {
        this.setX(newX);
        this.setY(newY);
    }

    public void setWidth(int newWidth) {
        width = newWidth;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int newHeight) {
        height = newHeight;
    }

    public int getHeight() {
        return height;
    }

    public void draw(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.drawRect(x, y, width, height);
        graphics.fillRect(x, y, width, height);
    }
}
