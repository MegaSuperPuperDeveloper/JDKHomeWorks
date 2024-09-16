package org.example;

public class HomeWork5 extends Thread{

    private final Object leftFork;
    private final Object rightFork;
    private int philosopherID;
    private int countOfEats = 3;

    public HomeWork5(Object leftFork, Object rightFork, int philosopherID) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.philosopherID = philosopherID;
    }

    public void think() {
        System.out.println("Философ " + philosopherID + " думает");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void eat() {
        System.out.println("Философ " + philosopherID + " кушает");
        countOfEats--;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < countOfEats; i++) {
            think();
            synchronized (leftFork) {
                System.out.println("Философ " + philosopherID + " взял левую вилку");
                synchronized (rightFork) {
                    System.out.println("Философ " + philosopherID + " взял правую вилку");
                    eat();
                }
                System.out.println("Философ " + philosopherID + " положил правую вилку");
            }
            System.out.println("Философ " + philosopherID + " положил левую вилку");
        }
    }
}