package org.example;

import org.example.differentMovements.Walk;
import org.example.differentMovements.RoadCar;
import org.example.differentMovements.Swim;
import org.example.differentMovements.IMStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Point spawn = new Point(0, 0);
        Point B = new Point(100, 30);
        Point C = new Point(250, 80);
        Point D = new Point(1000, 540);

        IMStrategy walk = new Walk();
        IMStrategy swim = new Swim();
        IMStrategy road = new RoadCar();

        System.out.println("Введи нейм:");
        String name = scanner.nextLine();
        Hero hero = new Hero(name, spawn, walk);

        while (true) {
            System.out.println("Выбери способ передвижения:");
            System.out.println("1 - Ходьба");
            System.out.println("2 - Плавание");
            System.out.println("3 - Машина");
            System.out.println("0 - Выйти");

            int strategyChoice;
            if (scanner.hasNextInt()) {
                strategyChoice = scanner.nextInt();
            } else {
                System.out.println("Введено не число, выбери одно из предложенных.");
                scanner.next();
                continue;
            }

            if (strategyChoice == 0) break;

            if (strategyChoice == 1) hero.setStrategy(walk);
            else if (strategyChoice == 2) hero.setStrategy(swim);
            else if (strategyChoice == 3) hero.setStrategy(road);
            else {
                System.out.println("Введено число не из предолеженных, выбери верное");
                continue;
            }

            while (true) {
                System.out.println("Выбери локацию");
                System.out.println("1 - Точка B (100, 30)");
                System.out.println("2 - Точка C (250, 80)");
                System.out.println("3 - Точка D (1000, 540)");
                System.out.println("0 - Выйти");

                int pointChoice;

                if (scanner.hasNextInt()) {
                    pointChoice = scanner.nextInt();
                } else {
                    System.out.println("Введено не число, выбери одно из предложенных.");
                    scanner.next();
                    continue;
                }

                if (pointChoice == 0) {
                    System.out.println("Финальная позиция: " + hero.getPosition());
                    return;
                }

                if (pointChoice == 1) hero.moveTo(B);
                else if (pointChoice == 2) hero.moveTo(C);
                else if (pointChoice == 3) hero.moveTo(D);
                else System.out.println("Введено число не из предолеженных, выбери верное");

                System.out.println("Текущая позиция: " + hero.getPosition());
            }
        }
        System.out.println("Финальная позиция: " + hero.getPosition());
        scanner.close();
    }
}
