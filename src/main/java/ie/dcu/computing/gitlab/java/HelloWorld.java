package ie.dcu.computing.gitlab.java;

public class HelloWorld {
    public static String getHello() {
        return "Hello, Ant!";
    }

    public static void main(String[] args) {
        System.out.println(getHello());

        System.out.println("Generating GUI...");
        new GUI();
        
        System.out.println("Running Ant Colony Optimisation Algorithm...");
        AntColonyOptimisation myACO = new AntColonyOptimisation(40);
        myACO.startOptimization();

    }
}