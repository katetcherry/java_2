package ua.qa.java.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("Katya");

        double l = 5;
        System.out.println("Square area with the side " + l + " = " + area(l));
    }

    public static void hello(String somebody) {

        System.out.println("Hello, " + somebody + "!");
    }

    public static double area(double len) {
        return len * len;
    }
}
