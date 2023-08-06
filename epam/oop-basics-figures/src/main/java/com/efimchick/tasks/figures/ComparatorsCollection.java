package com.efimchick.tasks.figures;

public class ComparatorsCollection {

    //TODO
    public static int compareByArea(Figure lhs, Figure rhs){
        double epsilon = 0.000001d;

        if (Math.abs(lhs.area() - rhs.area()) < epsilon) return 0;
        return Double.compare(lhs.area(), rhs.area());
    }

    //TODO
    public static int compareByHorizontalStartPosition(Figure lhs, Figure rhs){
        double epsilon = 0.000001d;

        if (Math.abs(lhs.getLeftmostHorizonalCoordinate() - rhs.getLeftmostHorizonalCoordinate()) < epsilon) return 0;

        return Double.compare(lhs.getLeftmostHorizonalCoordinate(), rhs.getLeftmostHorizonalCoordinate());
    }

    //TODO
    public static int compareByHorizontalCenterPosition(Figure lhs, Figure rhs){
        double epsilon = 0.000001d;

        if (Math.abs(lhs.centroid().getX() - rhs.centroid().getX()) < epsilon) return 0;

        return Double.compare(lhs.centroid().getX(), rhs.centroid().getX());
    }
}
