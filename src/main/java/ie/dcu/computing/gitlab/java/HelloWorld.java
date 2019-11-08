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
    }
}