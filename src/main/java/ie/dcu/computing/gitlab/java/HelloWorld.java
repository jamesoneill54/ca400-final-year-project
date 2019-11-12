package ie.dcu.computing.gitlab.java;

public class HelloWorld {
    public static String getHello() {
        return "Hello, Ant!";
    }

    public static void main(String[] args) {
        System.out.println(getHello());
        System.out.println("Generating GUI...");
        GUI myGUI = new GUI(400, 500);
        myGUI.displayGUI();
        System.out.println("Running Ant Colony Optimisation Algorithm...");
        AntColonyOptimisation myACO = new AntColonyOptimisation(40);
        myACO.startOptimization();
    }
}