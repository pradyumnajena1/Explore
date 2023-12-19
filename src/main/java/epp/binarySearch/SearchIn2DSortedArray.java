package epp.binarySearch;

import epp.array.ArrayUtils;

public class SearchIn2DSortedArray {
    public static void main(String[] args) {
        int[][] matrix = ArrayUtils.get2DSortedArray(5);
        ArrayUtils.print2DArray(matrix);
        int value = matrix[3][1];
        boolean found  = searchIn2DArray(matrix,value);
        System.out.println(found);
    }

    private static boolean searchIn2DArray(int[][] matrix, int value) {

        int row =0;
        int column = matrix[0].length-1;
        while (row<matrix.length && column>=0){

            if(matrix[row][column] == value){
                return true;
            }else if(matrix[row][column] < value){
                row++;
            }else{
                column--;
            }
        }

        return false;
    }

}
