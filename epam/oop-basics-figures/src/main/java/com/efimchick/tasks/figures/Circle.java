package com.efimchick.tasks.figures;

class Circle extends Figure {
    Point center;
    double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;

        if (area() <= 0 || radius <= 0 || center == null) {
            throw new RuntimeException();
        }
    }

    @Override
    public double area() {
        return  radius*radius*Math.PI;
    }

    @Override
    public Point centroid() {
        return center;
    }

    protected boolean canEqual(Figure other) {
        return other instanceof Circle;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        double epsilon = 0.000001d;

        if (figure == this) return true;
        if (!(figure instanceof Circle)) return false;

        Circle other = (Circle) figure;
        if (!other.canEqual(this)) return false;
        if (this.center.isNotTheSame(other.center)) return false;
        if (!(Math.abs(this.radius - other.radius) < epsilon)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Circle" + "[" + center.toString() + radius + "]";
    }

    @Override
    public double getLeftmostHorizonalCoordinate() {
        return center.getX() - radius;
    }

}
