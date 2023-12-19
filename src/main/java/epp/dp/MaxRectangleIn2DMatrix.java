package epp.dp;

import epp.array.ArrayUtils;

public class MaxRectangleIn2DMatrix {
    public static void main(String[] args) {
        int[][] matrix = ArrayUtils.createRandomMNMatrix(8, 8, 0, 2);
        ArrayUtils.print2DArray(matrix);
        int area = findMaxRectangleWith1s(matrix);
        System.out.println("Max rectangle "+ area);

        area = findMaxSquareWith1s(matrix);
        System.out.println("Max Square "+area);
    }

    private static int findMaxRectangleWith1s(int[][] matrix) {
        Dim[][] dims = getDimMatrix(matrix);
       // ArrayUtils.print2DArray(dims);
        int maxSize = 0;
        for (int i = 0; i < dims.length; i++) {
            for (int j = 0; j < dims[0].length; j++) {

                Dim currentCell = dims[i][j];
                if (currentCell.left > 0 && currentCell.top > 0) {
                    int probableArea = currentCell.left * currentCell.top;
                    //System.out.println(" currentCell " + currentCell + " probableArea " + probableArea + " maxSize
                    // " + maxSize);
                    if (probableArea > maxSize) {

                        int minWidth = getMinWidth(dims, i, j);
                        int minHeight = getMinHeight(dims, i, j);

                        //System.out.println("minWidth " + minWidth + " minHeight " + minHeight);
                        maxSize = Math.max(maxSize, minHeight * minWidth);

                    }
                }
            }
        }
        return maxSize;
    }

    private static int findMaxSquareWith1s(int[][] matrix) {
        Dim[][] dims = getDimMatrix(matrix);
        //ArrayUtils.print2DArray(dims);
        int maxSize = 0;
        for (int i = 0; i < dims.length; i++) {
            for (int j = 0; j < dims[0].length; j++) {

                Dim currentCell = dims[i][j];
                if (currentCell.left > 0 && currentCell.top > 0) {
                    int probableArea = currentCell.left * currentCell.top;
                   /* System.out.println("(i="+i+",j="+j+")"+ " currentCell " + currentCell + " probableArea " +
                     probableArea +
                            " " +
                            "maxSize " + maxSize);*/
                    if (probableArea > maxSize) {

                        int minWidth = getMinWidth(dims, i, j);
                        int minHeight = getMinHeight(dims, i, j);
                        int side = Math.min(minWidth,minHeight);

                        //System.out.println("side " + side  );
                        maxSize = Math.max(maxSize, side * side);

                    }
                }
            }
        }
        return maxSize;
    }

    private static int getMinHeight(Dim[][] dims, int i, int j) {
        Dim currentCell = dims[i][j];
        int minHeight = Integer.MAX_VALUE;
        for (int k = 0; k < currentCell.left; k++) {
            minHeight = Math.min(dims[i][j - k].top, minHeight);

        }
        return minHeight;
    }

    private static int getMinWidth(Dim[][] dims, int i, int j) {
        Dim currentCell = dims[i][j];
        int minWidth = Integer.MAX_VALUE;
        for (int k = 0; k < currentCell.top; k++) {
            minWidth = Math.min(dims[i - k][j].left, minWidth);

        }
        return minWidth;
    }

    private static Dim[][] getDimMatrix(int[][] matrix) {
        Dim[][] dims = new Dim[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {


                if (matrix[i][j] == 0) {
                    dims[i][j] = new Dim(0, 0);
                } else {
                    Dim dim = new Dim(1, 1);
                    dims[i][j] = dim;
                    if (i > 0 && matrix[i - 1][j] == 1) {
                        dim.top += dims[i - 1][j].top;
                    }
                    if (j > 0 && matrix[i][j - 1] == 1) {
                        dim.left += dims[i][j - 1].left;
                    }
                }
            }
        }
        return dims;
    }

    private static class Dim {
        int left;
        int top;

        public Dim(int left, int top) {
            this.left = left;
            this.top = top;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("(");
            sb.append("left=").append(left);
            sb.append("/ top=").append(top);
            sb.append(')');
            return sb.toString();
        }
    }
}
