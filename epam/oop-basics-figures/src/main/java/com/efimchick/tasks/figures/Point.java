package com.efimchick.tasks.figures;

class Point {
    private double x;
    private double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {return "(" + x + "," + y + ")";}

    public boolean isNotTheSame(Point other) {
        double epsilon = 0.000001d;
        return (!(Math.abs(x - other.getX()) < epsilon)|| !(Math.abs(y - other.getY()) < epsilon));
    }

}
