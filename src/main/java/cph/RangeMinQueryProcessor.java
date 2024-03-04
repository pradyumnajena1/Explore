package cph;

import epp.array.ArrayUtils;

public class RangeMinQueryProcessor {
    private final int[][] sparseTable;

    public RangeMinQueryProcessor(int[] values) {
        int highestSetBit = 31 - Integer.numberOfLeadingZeros(values.length);
        this. sparseTable = new int[highestSetBit+1][values.length];

        System.arraycopy(values, 0, sparseTable[0], 0, values.length);
        for (int row = 1; row < sparseTable.length; row++) {
            for (int col = 0; col < sparseTable[0].length; col++) {
                int window = 1 << row;
                sparseTable[row][col] = Math.min(sparseTable[row - 1][col], col + window / 2 < sparseTable[0].length
                        ? sparseTable[row - 1][col + window/2] :
                        Integer.MAX_VALUE);
            }
        }
        ArrayUtils.print2DArray(sparseTable);

    }

    public int getMin(int a,int b){
        int diff = b-a+1;
       int  row = 31-Integer.numberOfLeadingZeros(diff);
       int windowSize = 1<< row;
       return Math.min(sparseTable[row][a],b-windowSize+1>=0?sparseTable[row][b-windowSize+1] : Integer.MAX_VALUE );

    }

    public static void main(String[] args) {
        RangeMinQueryProcessor minQueryProcessor = new RangeMinQueryProcessor(ArrayUtils.randomArray(8,1,20)) ;
        System.out.println(minQueryProcessor.getMin(2, 7));
    }
}
