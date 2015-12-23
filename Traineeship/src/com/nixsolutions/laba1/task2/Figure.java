package com.nixsolutions.laba1.task2;

/**
 * An abstract class which represents an abstract figure. 
 * 
 * @author ponarin
 * @since 2015-12-10
 */
public abstract class Figure {
    /**
     * Prints figure's coordinates and perimeter
     */
    public abstract void print();
    
    /**
     * Moves every figure's point through adding x value to x coordinate and
     * y value to y coordinate.
     * @param x Value that will be added to the x coordinate in every point
     * @param y Value that will be added to the y coordinate in every point
     */
    public abstract void move(double x, double y);
    
    /**
     * Makes a resize of a figure through multiplying every point's coordinate
     * on source value.
     * @param percent The percent for scaling a figure. For example: 60.
     *                The base point is gravity center.
     * parameter
     */
    public abstract void scaling(double percent);
}
