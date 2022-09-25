package exercise5;

import java.util.Arrays;

public class Matrix {

    private final int[][] tab;

    public Matrix(int[][] tab) {
        this.tab = tab;
    }

    public Matrix(Matrix matrix) {
        this.tab = matrix.tab;
    }

    public Matrix product(Matrix matrix) {
        if (canNotMultiply(matrix)) {
            throw new IllegalArgumentException("Matrices cannot be multiplied");
        }

        Matrix multipliedMatrix = new Matrix(new int[getM()][matrix.getN()]);

        for (int i = 0; i < getM(); i++) {
            for (int j = 0; j < matrix.getN(); j++) {
                multipliedMatrix.setValue(i, j, Matrices.productFor(this, matrix,i, j));
            }
        }
        return multipliedMatrix;
    }

    public boolean canNotMultiply(Matrix matrix) {
        return tab[0].length != matrix.tab.length;
    }

    public int getM() {
        return tab.length;
    }

    public int getN() {
        return tab[0].length;
    }

    public int getValue(int i, int j) {
        return tab[i][j];
    }

    public void setValue(int i, int j, int value) {
        tab[i][j] = value;
    }

    public void addValue(int i, int j, int value) {
        tab[i][j] += value;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int[] row : tab) {
            result.append(Arrays.toString(row)).append("\n");
        }
        return result.toString();
//        return Arrays.deepToString(tab).trim();
    }
}
