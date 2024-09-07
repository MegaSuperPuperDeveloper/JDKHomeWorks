package org.example;

import org.example.HomeWork1.server.ServerWindow;
import org.example.HomeWork3.Calculator;

public class Main {
    public static void main(String[] args) {
        // HW1
        new ServerWindow();

        // HW3
        // Task1
        System.out.println(Calculator.sum(321312.32, 321312321.31));
        System.out.println(Calculator.multiply(2.2, 2.2));
        System.out.println(Calculator.divide(2.3, 2.3));
        System.out.println(Calculator.subtract(23123, 3221));

        // Task2
        Double[] array1 = {1.1, 2.0, 3.1};
        Float[] array2 = {1.1f, 2.0f, 3.1f};
        System.out.println(compareArrays(array1, array2));
    }

    public static <T, S> boolean compareArrays(T[] array1, S[] array2) {
        if (array1.length != array2.length)
            return false;
        if (array1.getClass().getName() != array2.getClass().getName())
            return false;
        return true;
    }

}
