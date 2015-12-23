package com.nixsolutions.laba1.task1;

/**
 * Class for sum, multiplication and transpose matrices
 * @author ponarin igor
 * @since 2015-12-10
 */
public class Matrix {
    private int[][] internalArray;

    /**
     * Creates an empty matrix
     */
    public Matrix() {
        internalArray = new int[0][0];
    }
    
    /**
     * Creates matrix with internal array
     * @param internalArray Inner array
     */
    public Matrix(int[][] internalArray) {
        setArray(internalArray);
    }
    
    /**
     * Sets internal array
     * @param internalArray Inner array
     */
    public void setArray(int[][] internalArray) {
        if (internalArray == null) {
            throw new IllegalArgumentException("Array can't be null");
        }
        this.internalArray = internalArray;
    }

    /**
     * Returns internal array
     * @return Internal array
     */
    public int[][] getArray() {
        return internalArray;
    }
    
    /**
     * Sums internal and external matrices
     * @param matrix The second operand of sum
     */
    public void sum(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix can't be null");
        }
        int internalArrayRows = getRowCount(internalArray);
        int internalArrayColumns = getColumnCount(internalArray);
        int externalArrayRows = getRowCount(matrix.getArray());
        int externalArrayColumns = getColumnCount(matrix.getArray());

        if (internalArrayRows != externalArrayRows
                || internalArrayColumns != externalArrayColumns) {
            throw new IllegalArgumentException(
                    "Matrix has different structure! " + "internal matrix: "
                            + this + " external matrix: " + matrix);
        }
        int[][] externalArray = matrix.getArray();

        int[][] resultArray = new int[internalArrayRows][internalArrayColumns];
        for (int row = 0; row < internalArrayRows; row++) {
            for (int column = 0; column < internalArrayColumns; column++) {
                int internalValue = internalArray[row][column];
                int externalValue = externalArray[row][column];
                resultArray[row][column] = internalValue + externalValue;
            }
        }
        internalArray = resultArray;
    }
    
    /**
     * Multiplies internal and external matrices
     * @param matrix The second operand of multiplication
     */
    public void mul(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix cant't be null");
        }

        int internalArrayRows = getRowCount(internalArray);
        int internalArrayColumns = getColumnCount(internalArray);
        int externalArrayRows = getRowCount(matrix.getArray());
        int externalArrayColumns = getColumnCount(matrix.getArray());

        if (internalArrayColumns != externalArrayRows) {
            throw new IllegalArgumentException("Internal array column count "
                    + "not equal external matrix row count " + "("
                    + internalArrayColumns + " != " + externalArrayRows + ")");
        }
        int[][] externalArray = matrix.getArray();
        int[][] resultArray = new int[internalArrayRows][externalArrayColumns];
        for (int i = 0; i < internalArrayRows; i++) {
            for (int j = 0; j < externalArrayColumns; j++) {
                int sum = 0;
                for (int k = 0; k < internalArrayColumns; k++) {
                    int value1 = internalArray[i][k];
                    int value2 = externalArray[k][j];
                    sum += (value1 * value2);
                }
                resultArray[i][j] = sum;
            }
        }
        internalArray = resultArray;
    }
    
    /**
     * Transposes inner array
     */
    public void transpose() {
        int rows = getRowCount(internalArray);
        int columns = getColumnCount(internalArray);

        int[][] resultMatrix = new int[columns][rows];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int value = internalArray[row][column];
                resultMatrix[column][row] = value;
            }
        }
        internalArray = resultMatrix;
    }
    
    /**
     * Prints matrix
     */
    public void print() {
        System.out.println(this);
    }
    
    /**
     * Generates readable string from internal matrix
     * @return String which was generated from inner matrix
     */
    @Override
    public String toString() {
        int rows = getRowCount(internalArray);
        int columns = getColumnCount(internalArray);

        StringBuilder matrixStr = new StringBuilder();
        matrixStr.append("[");
        for (int row = 0; row < rows; row++) {
            matrixStr.append("{");
            for (int column = 0; column < columns; column++) {
                matrixStr.append(internalArray[row][column]);
                matrixStr.append(", ");
            }
            int lastComaPos = matrixStr.lastIndexOf(",");
            matrixStr.replace(lastComaPos, lastComaPos + 2, "");
            matrixStr.append("}");
            matrixStr.append(", ");
        }
        int lastComaPos = matrixStr.lastIndexOf(",");
        matrixStr.replace(lastComaPos, lastComaPos + 2, "");
        matrixStr.append("]");

        return matrixStr.toString();
    }
    
    /**
     * Extract count of rows from source matrix.
     * @param matrix Source matrix
     * @return The number of rows from source matrix.
     */
    private int getRowCount(int[][] matrix) {
        return matrix.length;
    }

    /**
     * Extract count of columns from source matrix.
     * @param matrix Source matrix
     * @return The number of columns from source matrix.
     */
    private int getColumnCount(int[][] matrix) {
        return matrix[0].length;
    }
}
