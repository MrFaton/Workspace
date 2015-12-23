package com.nixsolutions.laba1.task2;

import java.util.Objects;

/**
 * The class is a point wrapper. It is represents a point with two coordinates:
 * x and y
 *
 * @author Mr_Faton
 * @since 12.12.2015
 */
public class Point {
    private double x;
    private double y;

    /**
     * Creates empty point
     */
    public Point() {
        x = 0.0;
        y = 0.0;
    }

    /**
     * Constructs the point
     *
     * @param x    X point's coordinate
     * @param y    Y point's coordinate
     */
    public Point(double x, double y) {
        setPoint(x, y);
    }

    /**
     * Sets point's coordinates
     *
     * @param x X point's coordinate
     * @param y Y point's coordinate
     */
    public void setPoint(double x, double y) {
        this.x = round(x);
        this.y = round(y);
    }

    /**
     * X point's coordinate
     *
     * @return X point's coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Y point's coordinate
     *
     * @return Y point's coordinate
     */
    public double getY() {
        return y;
    }

    private double round(double d) {
        return Math.round(d * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return String.format("{x=%s; y=%s}", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (Double.compare(point.x, x) != 0) return false;
        return Double.compare(point.y, y) != 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
