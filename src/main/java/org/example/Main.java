package org.example;

import org.example.HomeWork1.server.ServerWindow;
import org.example.HomeWork3.Calculator;
import org.example.HomeWork5;

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

        // HW4
        HomeWork4 task1 = new HomeWork4(123123, "79099059193", "Nikolay", "10 years");
        HomeWork4 task2 = new HomeWork4(123124, "79099059192", "Nikolay", "10 years");
        System.out.println(HomeWork4.getStaffByReportCardNumber(123123));
        System.out.println(HomeWork4.getPhoneNumberByName("Nikolay"));
        System.out.println(HomeWork4.getStaffByExperience("10 years"));

        // HW5
        Object[] forks = new Object[5];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }
        HomeWork5[] philosophers = new HomeWork5[5];
        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new HomeWork5(forks[i], forks[(i + 1) % 5], i+1);
            philosophers[i].start();
        }

        for (int i = 0; i < 5; i++) {
            try {
                philosophers[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    public static <T, S> boolean compareArrays(T[] array1, S[] array2) {
        if (array1.length != array2.length)
            return false;
        return array1.getClass().getName().equals(array2.getClass().getName());
    }

}
