package com.efimchik.tasks.triangle;

class Triangle {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;

        if (area() <= 0.000001d) {
            throw new RuntimeException();
        }

    }

    public double area() {
        return Math.abs(0.5*(a.getX()*(b.getY()-c.getY())+b.getX()*(c.getY()-a.getY())+c.getX()*(a.getY()-b.getY())));
    }

    public Point centroid() {
        return new Point((a.getX()+b.getX()+c.getX())/3,(a.getY()+b.getY()+c.getY())/3);
    }


    public String toString() {
        return "Triangle" + "[" + a.toString() + b.toString() + c.toString() + "]";
    }

    public static void main(String[] args) {

    }
}
