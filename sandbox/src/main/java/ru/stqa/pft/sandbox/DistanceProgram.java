package ru.stqa.pft.sandbox;

public class DistanceProgram {

    public static void main (String[] args ){

        Point p1 = new Point(4,8);
        Point p2 = new Point(3,7);

        System.out.println("Расстояние между точками p1[" + p1.x + ";" +p1.y + "] и p2[" + p2.x + ";" +p2.y + "] = " + Point.distance(p1,p2));
        System.out.println("Расстояние между точками p1[" + p1.x + ";" +p1.y + "] и p2[" + p2.x + ";" +p2.y + "] = " + distance(p1,p2));
        System.out.println("Расстояние между точками p1[" + p1.x + ";" +p1.y + "] и p2[" + p2.x + ";" +p2.y + "] = " + p1.secondDistance(p2));
    }

    public static double distance (Point point1, Point point2) {
        return Math.sqrt(((point2.x - point1.x) * (point2.x - point1.x)) + ((point2.y - point1.y)*(point2.y - point1.y)));

    }


}
