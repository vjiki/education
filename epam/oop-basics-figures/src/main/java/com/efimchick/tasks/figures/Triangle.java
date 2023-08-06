package com.efimchick.tasks.figures;

//TODO
class Triangle extends Figure {
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


    @Override
    public double area() {
        return Math.abs(0.5*(a.getX()*(b.getY()-c.getY())+b.getX()*(c.getY()-a.getY())+c.getX()*(a.getY()-b.getY())));
    }

    @Override
    public Point centroid() {
        return new Point((a.getX()+b.getX()+c.getX())/3,(a.getY()+b.getY()+c.getY())/3);
    }

    protected boolean canEqual(Figure other) {
        return other instanceof Triangle;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure == this) return true;
        if (!(figure instanceof Triangle)) return false;
        Triangle other = (Triangle) figure;
        if (!other.canEqual(this)) return false;
        if (this.a.isNotTheSame(other.a) && this.a.isNotTheSame(other.b) && this.a.isNotTheSame(other.c)) return false;
        if (this.b.isNotTheSame(other.b) && this.b.isNotTheSame(other.a) && this.b.isNotTheSame(other.c)) return false;
        if (this.c.isNotTheSame(other.c) && this.c.isNotTheSame(other.a) && this.c.isNotTheSame(other.b)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Triangle" + "[" + a.toString() + b.toString() + c.toString() + "]";
    }

    @Override
    public double getLeftmostHorizonalCoordinate() {
        double d = Math.min(a.getX(), b.getX());
        return Math.min(d, c.getX());
    }

}
