package ie.dcu.computing.gitlab.java;

public class Node {
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setCoOrdinates(int a, int b) {
        this.x = a;
        this.y = b;
    }

    public Node getNodeObj() {
        return this;
    }

    public int getNodeNum() {
        return (this.getY() * AntEnvironment.ENVIRONMENT_WIDTH) + this.getX() +1 ;
    }

    public void printNode() {
        System.out.print("(" + this.getX() + ", " + this.getY() + ")");
    }
}
