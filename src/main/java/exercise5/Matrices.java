package exercise5;

public final class Matrices {

    private Matrices() {
    }

    /**
     * For two matrices, calculates the product of the i-th row of the first matrix and the j-th column of the second matrix.
     *
     * @param matrix1 the first matrix to multiply.
     * @param matrix2 the second matrix to multiply.
     * @param i the row of the result matrix.
     * @param j the column of the result matrix.
     * @return the product of the i-th row of the first matrix and the j-th column of the second matrix.
     */
    public static int productFor(Matrix matrix1, Matrix matrix2, int i, int j) {
        int product = 0;
        for (int k = 0; k < matrix1.getN(); k++) {
            product += matrix1.getValue(i, k) * matrix2.getValue(k, j);
        }
        return product;
    }
}
