package com.efimchick.tasks.figures;

//TODO
class Quadrilateral extends Figure{

    private Point a;
    private Point b;
    private Point c;
    private Point d;

    Quadrilateral(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;

        //System.out.println(this.toString());


        if (area() <= 0) {// || isDeg()) {
            throw new RuntimeException();
        }
    }

    private double calculateDistanceBetweenPoints( Point a, Point b) {
        double ac = Math.abs(b.getY() - a.getY());
        double cb = Math.abs(b.getX() - a.getX());

        return Math.sqrt(ac * ac + cb * cb);
    }

    private boolean isDeg() {
        double epsilon = 0.000001d;

        double ab = calculateDistanceBetweenPoints(a, b);
        double bc = calculateDistanceBetweenPoints(c, b);
        double cd = calculateDistanceBetweenPoints(d, c);
        double ad = calculateDistanceBetweenPoints(d, a);

        double ac = calculateDistanceBetweenPoints(a, c);
        //double bd = calculateDistanceBetweenPoints(a, c);


        if (Math.abs(ab - cd) < epsilon) return true;
        if (Math.abs(ab - ad) < epsilon) return true;
        if (Math.abs(ac - (ab + bc)) < epsilon) return true;
        //if (Math.abs(bd - (bc + cd)) < epsilon) return true;

        return false;
    }

    @Override
    public double area() {
        double ab = calculateDistanceBetweenPoints(a, b);
        double bc = calculateDistanceBetweenPoints(b, c);
        double cd = calculateDistanceBetweenPoints(c, d);
        double ad = calculateDistanceBetweenPoints(a, d);

        double s = (ab + bc + cd + ad)/2;
        double p = calculateDistanceBetweenPoints(a, c);
        double q = calculateDistanceBetweenPoints(b, d);

        double expr = (s-ab)*(s-bc)*(s-cd)*(s-ad) - 0.25 * ((ab*cd + bc*ad + p*q)*(ab*cd + bc*ad - p*q));

        double area = Math.sqrt(expr);

        //System.out.println(ab + " " +  bc + " " +  cd + " " + ad + " " + s + " " +  area );

        //double area = new Triangle(a, b, c).area() + new Triangle(a, c, d).area();

        return area;
    }

    @Override
    public Point centroid() {
        Triangle first = new Triangle(a, b ,c);
        Triangle second = new Triangle(a, d ,c);
        Point firstCentroid = first.centroid();
        Point secondCentroid = second.centroid();

        return new Point((firstCentroid.getX() + secondCentroid.getX())/2, (firstCentroid.getY() + secondCentroid.getY())/2);
    }

    protected boolean canEqual(Figure other) {
        return other instanceof Quadrilateral;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure == this) return true;
        if (!(figure instanceof Quadrilateral)) return false;
        Quadrilateral other = (Quadrilateral) figure;
        if (!other.canEqual(this)) return false;
        if (this.a.isNotTheSame(other.a)) return false;
        if (this.b.isNotTheSame(other.b)) return false;
        if (this.c.isNotTheSame(other.c)) return false;
        if (this.d.isNotTheSame(other.d)) return false;
        if (this.a.isNotTheSame(other.a) && this.a.isNotTheSame(other.b) && this.a.isNotTheSame(other.c) && this.a.isNotTheSame(other.d)) return false;
        if (this.b.isNotTheSame(other.b) && this.b.isNotTheSame(other.a) && this.b.isNotTheSame(other.c) && this.b.isNotTheSame(other.d)) return false;
        if (this.c.isNotTheSame(other.c) && this.c.isNotTheSame(other.a) && this.c.isNotTheSame(other.b) && this.c.isNotTheSame(other.d)) return false;
        if (this.d.isNotTheSame(other.d) && this.d.isNotTheSame(other.a) && this.d.isNotTheSame(other.b) && this.d.isNotTheSame(other.c)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Quadrilateral" + "[" + a.toString() + b.toString() + c.toString() + d.toString() + "]";
    }

    @Override
    public double getLeftmostHorizonalCoordinate() {
        double m = Math.min(a.getX(), b.getX());
        double e = Math.min(m, c.getX());
        return Math.min(e, d.getX());
    }
}
