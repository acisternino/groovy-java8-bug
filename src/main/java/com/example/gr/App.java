package com.example.gr;

/**
 * Hello world!
 *
 */
public class App {

    @FunctionalInterface
    public static interface MathOperation {
        int operation(int a, int b);
    }

    public static void main(String[] args) {

        System.out.println("Hello World!");

        // Groovy classes
        GreetService gs = new GreetService();
        System.out.println(gs.sayHello());

        // Java 8 stuff
        MathOperation division = (int a, int b) -> a / b;
    }

}
