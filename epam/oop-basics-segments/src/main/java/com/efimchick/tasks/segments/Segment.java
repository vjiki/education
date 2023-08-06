package com.efimchick.tasks.segments;


public class Segment {

    public static final double EPSILON = 0.000001d;

    Point start;
    Point end;

    public Segment(Point start, Point end) {

        if (start == null || end == null) {
            throw new RuntimeException();
        }

        this.start = start;
        this.end = end;

        if (((Math.abs(start.getX() - end.getX()) < EPSILON) && (Math.abs(start.getY() - end.getY()) < EPSILON))) {
            throw new RuntimeException();
        }
    }

    private String pointToString(Point a) {
        if (a != null){
            return "(" + a.getX() + "," + a.getY() + ")";
        } else
        {
            return "null";
        }
    }


    double length() {
            double ac = Math.abs(end.getY() - start.getY());
            double cb = Math.abs(end.getX() - start.getX());

            return Math.sqrt(ac * ac + cb * cb);
    }

    Point middle() {
        return new Point((end.getX() + start.getX())/2, (end.getY() + start.getY())/2);
    }


    private boolean isValidPoint (double point_x, double point_y) {
        if (Double.isNaN(point_x) || Double.isNaN(point_y)) {
            return false;
        }

        if (point_x == Double.POSITIVE_INFINITY || point_x == Double.NEGATIVE_INFINITY) {
            return false;
        }

        if (point_y == Double.POSITIVE_INFINITY || point_y == Double.NEGATIVE_INFINITY) {
            return false;
        }
        return true;
    }

    private boolean isEqualStartStartX(Segment another) {
        return (Math.abs(start.getX() - another.start.getX()) < EPSILON);
    }

    private boolean isEqualStartEndX(Segment another) {
        return (Math.abs(start.getX() - another.end.getX()) < EPSILON);
    }

    private boolean isEqualEndEndX(Segment another) {
        return (Math.abs(end.getX() - another.end.getX()) < EPSILON);
    }

    private boolean isEqualEndStartX(Segment another) {
        return (Math.abs(end.getX() - another.start.getX()) < EPSILON);
    }

    private boolean isEqualStartStartY(Segment another) {
        return (Math.abs(start.getY() - another.start.getY()) < EPSILON);
    }

    private boolean isEqualStartEndY(Segment another) {
        return (Math.abs(start.getY() - another.end.getY()) < EPSILON);
    }

    private boolean isEqualEndEndY(Segment another) {
        return (Math.abs(end.getY() - another.end.getY()) < EPSILON);
    }

    private boolean isEqualEndStartY(Segment another) {
        return (Math.abs(end.getY() - another.start.getY()) < EPSILON);
    }

    Point intersection(Segment another) {
        Segment own = new Segment(start,end);
        double bottom = ((start.getX() - end.getX())*(another.start.getY()-another.end.getY())) - ((start.getY()-end.getY())*(another.start.getX()-another.end.getX()));
        double first = (start.getX()*end.getY() - start.getY()*end.getX())*(another.start.getX()-another.end.getX()) - (start.getX()-end.getX())*(another.start.getX()*another.end.getY() - another.start.getY()*another.end.getX());
        double second = (start.getX()*end.getY() - start.getY()*end.getX())*(another.start.getY()-another.end.getY()) - (start.getY()-end.getY())*(another.start.getX()*another.end.getY() - another.start.getY()*another.end.getX());

        double point_x = first/bottom;
        double point_y = second/bottom;

        System.out.println("start: " + pointToString(start) + " end: " + pointToString(end)
                + " another start: " + pointToString(another.start) + " another end: " + pointToString(another.end));

        System.out.println("doIntersect(start, end, another.start,another.end) " + doIntersect(start, end, another.start,another.end));

        if (doIntersect(start, end, another.start,another.end)) {
            if (isValidPoint (point_x, point_y)) {
                System.out.println("intersection point: x:" + point_x + " y: "+ point_y);
                return new Point(point_x, point_y);
            } else {
                if (((isEqualStartStartX(another)) && (isEqualStartStartY(another))) &&
                        (isEqualEndEndX(another) && isEqualEndEndY(another))) {
                    System.out.println("full intersection 1: " +  pointToString(another.start) + " " + pointToString(another.end));
                    return null;
                } else if ((isEqualStartEndX(another) && isEqualStartEndY(another)) &&
                        (isEqualEndStartX(another) && isEqualEndStartY(another))) {
                    System.out.println("full intersection 2: " +  pointToString(another.start) + " " + pointToString(another.end));
                    return null;
                } else if (isEqualStartStartX(another) && isEqualStartStartY(another)) {
                    if (onSegment(start, another.end, end)) {
                        System.out.println("1: partial intersection: " +  pointToString(another.end) +  pointToString(another.start));
                        return null;
                    }
                    System.out.println("1: intersection point: " +  pointToString(another.start));
                    return another.start;
                } else if (isEqualEndEndX(another) && isEqualEndEndY(another)) {
                    if (onSegment(start, another.start, end)) {
                        System.out.println("2: partial intersection: " +  pointToString(another.start) +  pointToString(another.end));
                        return null;
                    }
                    System.out.println("2: intersection point: " +  pointToString(another.end));
                    return another.end;
                } else if (isEqualStartEndX(another) && isEqualStartEndY(another)) {
                    if (onSegment(start, another.start, end)) {
                        System.out.println("3: partial intersection: " +  pointToString(another.start) + pointToString(another.end));
                        return null;
                    }
                    System.out.println("3: intersection point: " +  pointToString(another.end));
                    return another.end;
                } else if (isEqualEndStartX(another) && isEqualEndStartY(another)) {
                    if (onSegment(start, another.end, end)) {
                        System.out.println("4: partial intersection: " +  pointToString(another.end) + pointToString(another.start));
                        return null;
                    }
                    System.out.println("4: intersection point: " +  pointToString(another.start));
                    return another.start;
                } else {
                    System.out.println("undefined behavior");
                    return null;
                }
            }
        }
        else
        {
            return null;
        }
    }

    // Given three colinear points p, q, r, the function checks if
    // point q lies on line segment 'pr'
    private boolean onSegment(Point p, Point q, Point r)
    {
        if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) &&
                q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY()))
            return true;

        return false;
    }

    // To find orientation of ordered triplet (p, q, r).
    // The function returns following values
    // 0 --> p, q and r are colinear
    // 1 --> Clockwise
    // 2 --> Counterclockwise
    private double orientation(Point p, Point q, Point r)
    {
        // See https://www.geeksforgeeks.org/orientation-3-ordered-points/
        // for details of below formula.
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) -
                (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (val < EPSILON ) return 0; // colinear

        return (val > EPSILON)? 1: 2; // clock or counterclock wise
    }

    // The main function that returns true if line segment 'p1q1'
    // and 'p2q2' intersect.
    private boolean doIntersect(Point p1, Point q1, Point p2, Point q2)
    {
        // Find the four orientations needed for general and
        // special cases
        double o1 = orientation(p1, q1, p2);
        double o2 = orientation(p1, q1, q2);
        double o3 = orientation(p2, q2, p1);
        double o4 = orientation(p2, q2, q1);

        // General case
        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;

        // p1, q1 and q2 are colinear and q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;

        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;

        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false; // Doesn't fall in any of the above cases
    }

}
