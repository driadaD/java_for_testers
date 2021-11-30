package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void distance(){
        Point p1 = new Point (1,2);
        Point p2 = new Point(1,3);
        Assert.assertEquals(Point.distance(p1,p2),1.0);
    }

    @Test
    public void distance2(){
        Point p1 = new Point (3,3);
        Point p2 = new Point(6,3);
        Assert.assertEquals(p1.secondDistance(p2),3.0);
    }

    @Test
    public void distance3(){
        Point p1 = new Point (10,5);
        Point p2 = new Point(5,5);
        Assert.assertEquals(DistanceProgram.distance(p1,p2),5.0);
    }
}
