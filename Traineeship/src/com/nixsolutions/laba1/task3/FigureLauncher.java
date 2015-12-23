package com.nixsolutions.laba1.task3;

import com.nixsolutions.laba1.task2.Figure;
import com.nixsolutions.laba1.task2.Point;
import com.nixsolutions.laba1.task2.Quadrangle;
import com.nixsolutions.laba1.task2.Triangle;

import java.util.Random;

/**
 * Creates random figures' array, prints it and determine a specific figure in
 * array
 * 
 * @author ponarin
 *
 */
public class FigureLauncher {

    public static void main(String[] args) {
        Figure[] figures = new Figure[15];
        for (int i = 0; i < 15; i++) {
            figures[i] = getRandomFigure();
        }

        for (Figure figure : figures) {
            figure.print();
        }

        System.out.println("***");

        printFigureNames(figures);
    }

    /**
     * Creates random figure with random coordinates
     *
     * @return Random figure
     */
    public static Figure getRandomFigure() {
        Point a = new Point(getRandomInt(), getRandomInt());
        Point b = new Point(getRandomInt(), getRandomInt());
        Point c = new Point(getRandomInt(), getRandomInt());
        if (getRandomInt() % 2 == 0) {
            Point d = new Point(getRandomInt(), getRandomInt());
            return new Quadrangle(a, b, c, d);
        } else {
            return new Triangle(a, b, c);
        }
    }

    /**
     * Prints figure's name from array
     * 
     * @param figures
     *            Figures' array
     */
    public static void printFigureNames(Figure[] figures) {
        for (int i = 0; i < figures.length; i++) {
            System.out.println(i + " " + figures[i].getClass().getSimpleName());
        }
    }

    /**
     * Generates a random integer number from 0 to 100
     *
     * @return Random integer number from 0 to 100
     */
    public static int getRandomInt() {
        return new Random().nextInt(100);
    }
}
