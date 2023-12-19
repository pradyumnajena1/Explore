package epp.array.revision;

import epp.array.ArrayUtils;

public class Rotate2DArray {
    public static void main(String[] args) {
        int[][] matrix = ArrayUtils.create2DMatrix(5);
        ArrayUtils.print2DArray(matrix);
        rotate2DArray(matrix);
        ArrayUtils.print2DArray(matrix);
    }

    private static void rotate2DArray(int[][] matrix) {
        if(matrix.length!=matrix[0].length){
            throw new IllegalArgumentException("passed matrix is not a square matrix");
        }
       // rotateLayer(matrix,0);
         for(int layer=0;layer<(matrix.length+1)/2;layer++){
            rotateLayer(matrix,layer);
        }
    }

    private static void rotateLayer(int[][] matrix, int layer) {

       int[] topRowCopy =   readTopRow(matrix,layer);
       ArrayUtils.printArray(topRowCopy);
       copyLeftColumnToTopRow(matrix,layer);
       copyBottomRowToLeftColumn(matrix,layer);
      copyRightColumnToBottomRow(matrix,layer);
       copyTopRowToRightColumn(matrix,layer,topRowCopy);
    }

    private static void copyTopRowToRightColumn(int[][] matrix, int layer, int[] topRowCopy) {
        int col = matrix[0].length-1-layer;
        int startRow = layer;
        int endRow = matrix.length-1-layer;
        writeColumn(matrix,col,startRow,endRow,topRowCopy);
    }

    private static void copyRightColumnToBottomRow(int[][] matrix, int layer ) {
        int column = matrix[0].length-1-layer;
        int startRow = layer;
        int endRow = matrix.length-1-layer;
        int[] colValues = readColumn(matrix, column, startRow, endRow);

        int row = matrix.length-1-layer;
        int startCol = matrix[0].length-1-layer;
        int endCol = layer;
        writeRow(matrix,row,startCol,endCol,colValues);
    }

    private static void copyBottomRowToLeftColumn(int[][] matrix, int layer) {
        int row =matrix.length-1- layer;
        int startCol = matrix[0].length-1-layer;
        int endCol = layer;
        int[] rowValues =  readRow(matrix,row,startCol,endCol);

        int column = layer;
        int startRow = matrix.length-1-layer;
        int endRow = layer;
        writeColumn(matrix,column,startRow,endRow,rowValues);
    }

    private static void copyLeftColumnToTopRow(int[][] matrix, int layer) {
        int column = layer;
        int startRow = matrix.length-1-layer;
        int endRow = layer;
         int[] colValues =  readColumn(matrix,column,startRow,endRow);

         int row = layer;
        int startCol = layer;
        int endCol = matrix[0].length-1-layer;
         writeRow(matrix,row,startCol,endCol,colValues);
    }

    private static int[] readColumn(int[][] matrix, int column, int startRow, int endRow) {
       int[] result = new int[Math.abs(endRow-startRow)+1];
       int writeIndex = 0;
       if(startRow<=endRow){
           for(int row = startRow;row<=endRow;row++){
               result[writeIndex++] = matrix[row][column];
           }
       }else{
           for(int row = startRow;row>=endRow;row--){
               result[writeIndex++] = matrix[row][column];
           }
       }
        return result;
    }

    private static  void writeColumn(int[][] matrix, int column, int startRow, int endRow,int[] values) {
          int readIndex = 0;
        if(startRow<=endRow){
            for(int row = startRow;row<=endRow;row++){
                  matrix[row][column] = values[readIndex++];
            }
        }else{
            for(int row = startRow;row>=endRow;row--){
                 matrix[row][column] = values[readIndex++];
            }
        }

    }

    private static int[] readTopRow(int[][] matrix, int layer) {
        int startColumn = layer;
        int endColumn = matrix[0].length-1-layer;
        int row = layer;
        return readRow(matrix,row, startColumn,endColumn);
    }

    private static int[] readRow(int[][] matrix,int row, int startColumn, int endColumn ) {
        int[] result = new int[ Math.abs( endColumn - startColumn )+1];
        int writeIndex = 0;
        if(startColumn<=endColumn){
            for(int col = startColumn; col<= endColumn; col++){
                result[writeIndex++] = matrix[row][col];
            }
        }else{
            for(int col = startColumn; col>= endColumn; col--){
                result[writeIndex++] = matrix[row][col];
            }
        }

        return result;
    }

    private static void writeRow(int[][] matrix,int row, int startColumn, int endColumn,int[] values ) {
         int readIndex = 0;
        if(startColumn<=endColumn){
            for(int col = startColumn; col<= endColumn; col++){
                  matrix[row][col] = values[readIndex++];
            }
        }else{
            for(int col = startColumn; col>= endColumn; col--){
                  matrix[row][col] = values[readIndex++];
            }
        }


    }
}
