package org.example;

import org.example.differentMovements.IMStrategy;

public class Hero {
    private String name;
    private IMStrategy strategy;
    private Point position;

    public Hero(String name, Point startPosition, IMStrategy initialStrategy) {
        this.name = name;
        this.position = startPosition;
        this.strategy = initialStrategy;
    }

    public void setStrategy(IMStrategy strategy) {
        this.strategy = strategy;
        System.out.println(name + " сменил способ передвижения на " + strategy.getClass().getSimpleName());
    }

    public void moveTo(Point destination) {
        if (strategy == null) {
            System.out.println("Способ передвижения отсутстует.");
            return;
        }
        String result = strategy.move(name, position, destination);
        System.out.println(result);

        this.position = destination;
    }

    public Point getPosition() { return position; }
}
