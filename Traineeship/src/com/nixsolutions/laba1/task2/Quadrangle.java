package com.nixsolutions.laba1.task2;

/**
 * The class is a representative of a specific figure. It is a quadrangle.
 *
 * @author ponarin igor
 * @see Figure
 * @since 2015-12-10
 */
public class Quadrangle extends Figure {
    private Point a;
    private Point b;
    private Point c;
    private Point d;

    /**
     * Creates an empty quadrangle
     */
    public Quadrangle() {
        a = new Point(0.0, 0.0);
        b = new Point(0.0, 0.0);
        c = new Point(0.0, 0.0);
        d = new Point(0.0, 0.0);
    }

    /**
     * Creates a quadrangle with four points
     *
     * @param a Point A
     * @param b Point B
     * @param c Point C
     * @param d Point D
     */
    public Quadrangle(Point a, Point b, Point c, Point d) {
        setCoordinates(a, b, c, d);
    }

    /**
     * Prints quadrangle's coordinates and perimeter.
     */
    @Override
    public void print() {
        System.out.println(this);
    }

    /**
     * Moves every quadrangle's point through adding x value to x coordinate and
     * y value to y coordinate.
     *
     * @param x Value that will be added to the x coordinate at the every point
     * @param y Value that will be added to the y coordinate at the every point
     */
    @Override
    public void move(double x, double y) {
        a.setPoint(a.getX() + x, a.getY() + y);
        b.setPoint(b.getX() + x, b.getY() + y);
        c.setPoint(c.getX() + x, c.getY() + y);
        d.setPoint(d.getX() + x, d.getY() + y);
    }

    /**
     * Makes a resize of a figure. Scaling is occurs around figure's gravity
     * center
     *
     * @param percent The percent for scaling a figure. For example: 40.
     */
    @Override
    public void scaling(double percent) {
        if (percent < 0) {
            throw new IllegalArgumentException("percent is negative");
        }
        if (percent == 100.0) return;

        //search figure's gravity center
        Point m = evalGravityCenter();
        //Point which displays scaling in percent
        a = evalPointPercent(a, m, percent);
        b = evalPointPercent(b, m, percent);
        c = evalPointPercent(c, m, percent);
        d = evalPointPercent(d, m, percent);
    }

    /**
     * Evaluates a figure's perimeter.
     *
     * @return perimeter of a figure.
     */
    public double getPerimeter() {
        //line from A to B
        double ab = evalLineLength(a, b);
        //line from A to D
        double ad = evalLineLength(a, d);
        //line from B to C
        double bc = evalLineLength(b, c);
        //line from D to C
        double dc = evalLineLength(d, c);
        return ab + ad + bc + dc;
    }

    /**
     * Sets quadrangle's coordinates
     *
     * @param a Point A
     * @param b Point B
     * @param c Point C
     * @param d Point D
     */
    public void setCoordinates(Point a, Point b, Point c, Point d) {
        if (a == null || b == null || c == null || d == null) {
            throw new IllegalArgumentException("One of your points is null");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Returns point A
     *
     * @return Point A
     */
    public Point getA() {
        return a;
    }

    /**
     * Returns point B
     *
     * @return Point B
     */
    public Point getB() {
        return b;
    }

    /**
     * Returns point C
     *
     * @return Point C
     */
    public Point getC() {
        return c;
    }

    /**
     * Returns point D
     *
     * @return Point D
     */
    public Point getD() {
        return d;
    }

    /**
     * Evaluates quadrangle's gravity center for scaling
     * @return Gravity center
     */
    private Point evalGravityCenter() {
        //first sub triangle gravity center
        double x1 = (a.getX() + b.getX() + c.getX()) / 3;
        double y1 = (a.getY() + b.getY() + c.getY()) / 3;

        //second sub triangle gravity center
        double x2 = (a.getX() + d.getX() + c.getX()) / 3;
        double y2 = (a.getY() + d.getY() + c.getY()) / 3;

        double xCenter = (x1 + x2) / 2;
        double yCenter = (y1 + y2) / 2;

        return new Point(xCenter, yCenter);
    }

    /**
     * Evaluates distance between two points
     * @param a First point
     * @param b Second point
     * @return Distance between two points
     */
    private double evalLineLength(Point a, Point b) {
        return Math.sqrt(Math.pow((a.getX() - b.getX()), 2)
                + Math.pow((a.getY() - b.getY()), 2));
    }

    /**
     * Evaluates line's length in percent equivalent and make a point according
     * percent equivalent. For example: we have line with length 10 sm, but we 
     * want to find the line which will be according 40% from base line, so 
     * method returns the point, which divide line on 40% and 60%
     * @param a First point
     * @param m Second point
     * @param percent Scaling percent
     * @return Point that accorded 
     */
    private Point evalPointPercent(Point a, Point m, double percent) {
        double totalLength = evalLineLength(a, m);
        double lengthBeforePoint = totalLength * percent / 100;
        double lengthAfterPoint = totalLength - lengthBeforePoint;
        double coefficient = lengthBeforePoint / lengthAfterPoint;
        double x = ((a.getX() * coefficient) + m.getX()) / (1 + coefficient);
        double y = ((a.getY() * coefficient) + m.getY()) / (1 + coefficient);
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return String.format("Quadrangle: [%s; %s; %s; %s] Perimeter = %.2f",
                a, b, c, d, getPerimeter());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quadrangle that = (Quadrangle) o;

        if (!a.equals(that.a)) return false;
        if (!b.equals(that.b)) return false;
        if (!c.equals(that.c)) return false;
        if (!d.equals(that.d)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = a.hashCode();
        result = 31 * result + b.hashCode();
        result = 31 * result + c.hashCode();
        result = 31 * result + d.hashCode();
        return result;
    }
}
