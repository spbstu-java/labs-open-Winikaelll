package org.example.differentMovements;

import org.example.Point;

public class Swim implements IMStrategy {

    public String move(String heroName, Point from, Point to) {
        double dist = distance(from, to);
        double speed = 2.0;
        double time = dist / speed;
        return heroName + " плывет от " + from + " до " + to +
                " (расстояние: " + String.format("%.2f", dist) +
                " | время: " + String.format("%.2f", time) + " )";
    }

    private double distance(Point a, Point b) {
        int x = b.getX() - a.getX();
        int y = b.getY() - a.getY();
        return Math.sqrt(x * x + y * y);
    }
}
