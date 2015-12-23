package com.nixsolutions.laba1;

import com.nixsolutions.laba1.task1.Matrix;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

/**
 * Testing class for class Matrix
 *
 * @author Mr_Faton
 * @since 20.12.2015
 */
public class MatrixTest {
    private Matrix matrixA;
    private Matrix matrixB;

    @Before
    public void setUp() {
        matrixA = new Matrix();
        matrixB = new Matrix();
    }

    @Test
    public void testSetArray() {
        int[][] original = {{1, 2}, {3, 4}};
        matrixA.setArray(original);
        int[][] actual = matrixA.getArray();
        assertArrayEquals("Arrays must equals", original, actual);

        try {
            matrixA.setArray(null);
        } catch (IllegalArgumentException argumentEx) {
        } catch (Exception ex) {
            fail("Must throw IllegalArgumrntException");
        }
    }

    @Test
    public void testGetArray() {
        int[][] original = {{1, 2}, {3, 4}};
        matrixA.setArray(original);
        int[][] actual = matrixA.getArray();
        assertArrayEquals("Arrays must equals", original, actual);
    }

    @Test
    public void testSum() {
        int[][] arrayA = {{1, 2}, {3, 4}};
        int[][] arrayB = {{5, 6}, {7, 8}};

        matrixA.setArray(arrayA);
        matrixB.setArray(arrayB);

        matrixA.sum(matrixB);

        int[][] expected = {{6, 8}, {10, 12}};
        int[][] actual = matrixA.getArray();

        assertArrayEquals("Arrays must equals", expected, actual);

        try {
            matrixA.sum(null);
        } catch (IllegalArgumentException badArgument) {
        } catch (Exception ex) {
            fail("Must throw IllegalArgumrntException");
        }
    }

    @Test
    public void testMul() {
        int[][] arrayA = {{1, -9, 0}, {2, 5, 8}, {-1, -5, -4}};
        int[][] arrayB = {{2, -4}, {5, -7}, {-3, 11}};
        int[][] badArray = {{7, -3, 5}, {4, -8, 10}};

        matrixA.setArray(arrayA);
        matrixB.setArray(badArray);

        try {
            matrixA.mul(matrixB);
            fail("Colums of matrixA different than rows matrixB");
        } catch (IllegalArgumentException ex) {
        }

        matrixB.setArray(arrayB);
        matrixA.mul(matrixB);

        int[][] expected = {{-43, 59}, {5, 45}, {-15, -5}};
        int[][] actual = matrixA.getArray();

        assertArrayEquals("Arrays must equals", expected, actual);
    }

    @Test
    public void testTranspose() {
        int[][] array = {{2, 8, 3}, {2, 5, 8}};
        matrixA.setArray(array);
        matrixA.transpose();

        int[][] expected = {{2, 2}, {8, 5}, {3, 8}};
        int[][] actual = matrixA.getArray();

        assertArrayEquals("Arrays must equals", expected, actual);
    }
}
