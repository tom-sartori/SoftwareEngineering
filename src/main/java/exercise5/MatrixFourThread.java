package exercise5;

public class MatrixFourThread extends Matrix implements Runnable {

    private Matrix matrixToMultiplyWith;
    private Matrix multipliedMatrix;

    private final int firstRow;
    private final int lastRow;
    private final int firstColumn;
    private final int lastColumn;

    public MatrixFourThread(int[][] tab) {
        super(tab);
        firstRow = 0;
        lastRow = getM();
        firstColumn = 0;
        lastColumn = getN();
    }

    private MatrixFourThread(MatrixFourThread matrixFourThread, int firstRow, int lastRow, int firstColumn, int lastColumn) {
        super(matrixFourThread);
        this.matrixToMultiplyWith = matrixFourThread.matrixToMultiplyWith;
        this.multipliedMatrix = matrixFourThread.multipliedMatrix;
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.firstColumn = firstColumn;
        this.lastColumn = lastColumn;
    }

    @Override
    public Matrix product(Matrix matrix) {
        if (canNotMultiply(matrix)) {
            throw new IllegalArgumentException("Matrices cannot be multiplied");
        }
        matrixToMultiplyWith = matrix;
        multipliedMatrix = new Matrix(new int[getM()][matrix.getN()]);

        /// TODO : check size m n
        int middleRow = getM() / 2;
        int middleColumn = matrix.getN() / 2;

        Thread thread1 = new Thread(new MatrixFourThread(this, firstRow, middleRow, firstColumn, middleColumn));  // Top left.
        Thread thread2 = new Thread(new MatrixFourThread(this, firstRow, middleRow, middleColumn, lastColumn));  // Top right.
        Thread thread3 = new Thread(new MatrixFourThread(this, middleRow, lastRow,  firstColumn, middleColumn));  // Bottom left.
        Thread thread4 = new Thread(new MatrixFourThread(this, middleRow, lastRow, middleColumn, lastColumn));  // Bottom right.

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return multipliedMatrix;
    }

    @Override
    public void run() {
        for (int i = firstRow; i < lastRow; i++) {
            for (int j = firstColumn; j < lastColumn; j++) {
                multipliedMatrix.setValue(i, j, Matrices.productFor(this, matrixToMultiplyWith, i, j));
            }
        }
    }
}
