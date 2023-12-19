package epp.array.revision;

import epp.array.ArrayUtils;

public class PrintSquareMatrixInSpiralOrder {
    public static void main(String[] args) {
        int[][] matrix = ArrayUtils.create2DMatrix(6);
        ArrayUtils.print2DArray(matrix);
        printInSpiralOrder(matrix);

    }

    private static void printInSpiralOrder(int[][] matrix) {
        if(matrix.length!=matrix[0].length){
            throw new IllegalArgumentException("Matrix passed is not square matrix");
        }
        for(int layer=0;layer<=matrix.length/2;layer++){
            printLayer(matrix,layer);
        }
    }

    private static void printLayer(int[][] matrix, int layer) {
        printTopRow(matrix,layer);
        printRightColumn(matrix,layer);
        printBottomRow(matrix,layer);
        printLeftColumn(matrix,layer);
    }

    private static void printRightColumn(int[][] matrix, int layer) {
        int column = matrix[0].length-1-layer;
        int startRow = layer+1;
        int endRow = matrix.length-1 - layer-1;
        for(int row = startRow;row<=endRow;row++){
            System.out.print(matrix[row][column]+", ");
        }
    }

    private static void printBottomRow(int[][] matrix, int layer) {
        if(matrix.length%2==1 && layer==matrix.length/2){
            return;
        }
        int row =matrix.length-1-layer;
        int startColumn = matrix[0].length-1-layer;
        int endColumn =  layer;
        for(int column=startColumn;column>=endColumn;column--){
            System.out.print(matrix[row][column]+", ");
        }
    }

    private static void printLeftColumn(int[][] matrix, int layer) {
        int column =  layer;
        int endRow = layer+1;
        int startRow = matrix.length-1 - layer-1;
        for(int row = startRow;row>=endRow;row--){
            System.out.print(matrix[row][column]+", ");
        }
    }

    private static void printTopRow(int[][] matrix, int layer) {
        int row = layer;
        int startColumn = layer;
        int endColumn = matrix[0].length-1-layer;
        for(int column=startColumn;column<=endColumn;column++){
            System.out.print(matrix[row][column]+", ");
        }
    }
}
