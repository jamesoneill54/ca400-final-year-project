package ie.dcu.computing.gitlab.java;

import java.awt.*;

/*
* If we use a combination of node and coordinate movement, then obstacles could
* be represented to the ACO algorithm as a lack of nodes for the position they
* fill.
*
* IE. ACO algorithm starts out with a full board of nodes, each node
* representing a coordinate. The obstacles are then generated, and compared
* against the board of nodes, and the nodes that the obstacle overlays are
* removed from the board.
*/

public class Obstacle implements EnvironmentObject {

    private int x;
    private int y;
    private int width;
    private int height;

    public Obstacle(int x, int y, int width, int height) {
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
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, width, height);
        graphics.fillRect(x, y, width, height);
    }
}
