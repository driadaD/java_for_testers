package ru.stqa.pft.sandbox;

public class Point {
    double x;
    double y;

    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }

    public static double distance(Point point1, Point point2){
        return Math.sqrt(((point2.x - point1.x) * (point2.x - point1.x)) + ((point2.y - point1.y)*(point2.y - point1.y)));
    }

    public double secondDistance(Point dot){
        return Math.sqrt(Math.pow(dot.x - this.x,2) + Math.pow(dot.y - this.y,2));
    }

}
