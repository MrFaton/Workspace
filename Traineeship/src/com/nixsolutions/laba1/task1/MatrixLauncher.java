package com.nixsolutions.laba1.task1;

/**
 * Class for launch Matrix class's methods
 *
 * @author ponarin igor
 * @since 2015-12-10
 */
public class MatrixLauncher {
    /**
     * Launches matrix class's functions
     * 
     * @param args
     *            Not uses.
     */
    public static void main(String[] args) {
        int[][] arrayA = { { 2, 3, 4 }, { 5, 6, 7 } };
        int[][] otherArrayA = { { 9, 8, 7 }, { 6, 5, 4 } };

        Matrix mainMatrix = new Matrix(arrayA);
        Matrix otherMatrix = new Matrix(otherArrayA);

        System.out.println("Sum:");
        System.out.println("main matrix: " + mainMatrix);
        mainMatrix.sum(otherMatrix);
        System.out.println("other matrix: " + otherMatrix);
        System.out.println("result: " + mainMatrix);
        System.out.println("***");

        int[][] arrayB = { { 5, 15, 1, 4 }, { 22, 16, 8, 1 }, { 5, 9, 0, 5 } };
        int[][] otherArrayB = { { 5, 2 }, { 8, 12 }, { 4, 9 }, { 7, -3 } };
        mainMatrix.setArray(arrayB);
        otherMatrix.setArray(otherArrayB);
        
        System.out.println("Mul:");
        System.out.println("main matrix: " + mainMatrix);
        mainMatrix.mul(otherMatrix);
        System.out.println("other matrix: " + otherMatrix);
        System.out.println("result: " + mainMatrix);
        System.out.println("***");
        
        System.out.println("Transpose:");
        System.out.println("main matrix: " + mainMatrix);
        mainMatrix.transpose();
        System.out.println("result: " + mainMatrix);
        System.out.println("***");
        
        System.out.println("Print:");
        mainMatrix.print();
    }
}
