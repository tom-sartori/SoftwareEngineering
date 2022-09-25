package exercise5;

public class MatrixMultiThread extends Matrix implements Runnable {

    private Matrix matrixToMultiplyWith;
    private Matrix multipliedMatrix;

    private final int firstRow;
    private final int lastRow;
    private final int firstColumn;
    private final int lastColumn;

    public MatrixMultiThread(int[][] tab) {
        super(tab);
        firstRow = 0;
        lastRow = getM();
        firstColumn = 0;
        lastColumn = getN();
    }

    private MatrixMultiThread(MatrixMultiThread matrixRunnable, int firstRow, int lastRow, int firstColumn, int lastColumn) {
        super(matrixRunnable);
        this.matrixToMultiplyWith = matrixRunnable.matrixToMultiplyWith;
        this.multipliedMatrix = matrixRunnable.multipliedMatrix;
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

        Thread thread = new Thread(this);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return multipliedMatrix;
    }

    @Override
    public void run() {
        int numberOfRow = lastRow - firstRow;
        int numberOfColumn = lastColumn - firstColumn;

        int middleRow = firstRow + (numberOfRow / 2);
        int middleColumn = firstColumn + (numberOfColumn / 2);

//        System.out.println();
//        System.out.println("firstRow : " + firstRow + " | lastRow : " + lastRow + " | numberOfRow : " + numberOfRow + " | middleRow : " + middleRow);
//        System.out.println("firstColumn : " + firstColumn + " | lastColumn : " + lastColumn + " | numberOfColumn : " + numberOfColumn + " | middleColumn : " + middleColumn);
//        System.out.println(multipliedMatrix);

        Thread[] threadArray;

        if (numberOfRow > 1 && numberOfColumn > 1) {
            // Normal case. Create 4 threads.
            threadArray = new Thread[]{
                    new Thread(new MatrixMultiThread(this, firstRow, middleRow, firstColumn, middleColumn)),  // Top left.
                    new Thread(new MatrixMultiThread(this, firstRow, middleRow, middleColumn, lastColumn)),  // Top right.
                    new Thread(new MatrixMultiThread(this, middleRow, lastRow, firstColumn, middleColumn)),  // Bottom left.
                    new Thread(new MatrixMultiThread(this, middleRow, lastRow, middleColumn, lastColumn))  // Bottom right.
            };
        }
        else if (numberOfRow == 1 && numberOfColumn > 1) {
            // There is only one row. Create 2 threads.
            threadArray = new Thread[]{
                    new Thread(new MatrixMultiThread(this, middleRow, lastRow, firstColumn, middleColumn)),  // Bottom left.
                    new Thread(new MatrixMultiThread(this, middleRow, lastRow, middleColumn, lastColumn))  // Bottom right.
            };
        }
        else if (numberOfRow > 1 && numberOfColumn == 1) {   // && middleColumn <= 1
            // There is only one column. Create 2 threads.
            threadArray = new Thread[]{
                    new Thread(new MatrixMultiThread(this, firstRow, middleRow, middleColumn, lastColumn)),  // Top right.
                    new Thread(new MatrixMultiThread(this, middleRow, lastRow, middleColumn, lastColumn))  // Bottom right.
            };
        }
        else if (numberOfRow == 1 && numberOfColumn == 1) {
            // There is only one case.
            multipliedMatrix.setValue(middleRow, middleColumn, Matrices.productFor(this, matrixToMultiplyWith, middleRow, middleColumn));
            return;
        }
        else {
            throw new RuntimeException();
        }
        for (Thread thread : threadArray) {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
