package com.nixsolutions.laba1.task2;

/**
 * The class is a representative of a specific figure. It is a triangle.
 *
 * @author ponarin igor
 * @see Figure
 * @since 2015-12-10
 */
public class Triangle extends Figure {
    private Point a;
    private Point b;
    private Point c;

    /**
     * Creates an empty triangle
     */
    public Triangle() {
        a = new Point(0.0, 0.0);
        b = new Point(0.0, 0.0);
        c = new Point(0.0, 0.0);
    }

    /**
     * Creates a triangle with three points
     *
     * @param a Point A
     * @param b Point B
     * @param c Point C
     */
    public Triangle(Point a, Point b, Point c) {
        setCoordinates(a, b, c);
    }

    /**
     * Prints a triangle's coordinates and perimeter.
     */
    @Override
    public void print() {
        System.out.println(this.toString());
    }

    /**
     * Moves every triangle's point through adding x value to x coordinate and
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
    }

    /**
     * Makes a resize of a figure. Scaling is occurs around figure's gravity
     * center
     *
     * @param percent The percent for scaling a figure. For example: 60.
     *                The base point is gravity center.
     */
    @Override
    public void scaling(double percent) {
        Point m = evalGravityCenter();
        a = evalPointPercent(a, m, percent);
        b = evalPointPercent(b, m, percent);
        c = evalPointPercent(c, m, percent);
    }

    /**
     * Evaluates a figure's perimeter.
     *
     * @return perimeter of a figure.
     */
    public double getPerimeter() {
        //line from A to B
        double ab = evalLineLength(a, b);
        //line from A to C
        double ac = evalLineLength(a, c);
        //line from B to C
        double bc = evalLineLength(b, c);

        return ab + ac + bc;
    }

    /**
     * Sets triangle's coordinates
     *
     * @param a Point A
     * @param b Point B
     * @param c Point C
     */
    public void setCoordinates(Point a, Point b, Point c) {
        if (a == null || b == null || c == null) {
            throw new IllegalArgumentException("One of your points is null");
        }
        this.a = a;
        this.b = b;
        this.c = c;
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
     * Evaluates triangle's gravity center for scaling
     * @return Gravity center
     */
    private Point evalGravityCenter() {
        double x = (a.getX() + b.getX() + c.getX()) / 3;
        double y = (a.getY() + b.getY() + c.getY()) / 3;
        return new Point(x, y);
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
        return String.format("Triangle: [%s; %s; %s] Perimeter = %.2f",
                a, b, c, getPerimeter());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triangle triangle = (Triangle) o;

        if (!a.equals(triangle.a)) return false;
        if (!b.equals(triangle.b)) return false;
        return c.equals(triangle.c);

    }

    @Override
    public int hashCode() {
        int result = a.hashCode();
        result = 31 * result + b.hashCode();
        result = 31 * result + c.hashCode();
        return result;
    }
}
