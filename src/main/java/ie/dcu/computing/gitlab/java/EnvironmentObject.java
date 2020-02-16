package ie.dcu.computing.gitlab.java;

import java.awt.*;

public interface EnvironmentObject {

    // For now, all EnvironmentObjects will be rectangular

    void setLocation(int newX, int newY);

    void setX(int newX);

    int getX();

    void setY(int newY);

    int getY();

    void setWidth(int newWidth);

    int getWidth();

    void setHeight(int newHeight);

    int getHeight();

    void draw(Graphics graphics);
}
