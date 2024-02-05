package guidetocompetitiveprogramming;

import epp.Pair;

public class IntTriangularMatrix {
    private final int defaultValue;
    private final long totalLength;
    private int size;
    private final boolean upper;
    private int[] values;

    public IntTriangularMatrix(int size, boolean upper,int defaultValue) {
        this.size = size;
        this.upper = upper;
        this.defaultValue=defaultValue;
        this.totalLength =   (long)size * (size + 1) / 2;
        this.values = new int[(int) totalLength];
    }

    public void set(int row, int col, int value) {
        if (!upper && row >= col) {
            values[getIndex(row, col)] = value;
        } else if (upper && row <= col) {
            values[getIndex(row, col)] = value;
        }

    }

    private int getIndex(int row, int col) {
       // System.out.println("row = " + row + ", col = " + col);
        int index =-1;
        if (!upper) {
              index = (row * (row + 1)) / 2 + col;

        } else {

            long remaining = (int) (((long)(size - row) * (size - row + 1) )/ 2);
            index = (int) (totalLength - remaining + (col - row));
        }
        if(index==522632908){
            System.out.println("");
        }
        return index;
    }

    public int get(int row, int col) {
        if (row < 0 || col < 0) {
            return defaultValue;
        } else if (!upper && row >= col) {
            return values[getIndex(row, col)];
        } else if (upper && row <= col) {
            return values[getIndex(row, col)];
        }else {
            return defaultValue;
        }
    }


    public void cumulativeSum(){
        if(upper){

            cumlativeRow();
            cumulativeCol();
        }else{
            cumulativeCol();
            cumlativeRow();
        }


    }

    private void cumulativeCol() {
        for(int j=0;j<size;j++){
           for (int i = 0; i < size; i++) {
               int sum = get(i, j) + get(i - 1, j);
               set(i,j, sum);
           }
       }
    }

    private void cumlativeRow() {
        for(int i=0;i<size;i++){
            for (int j = 0; j < size; j++) {
                int sum = get(i, j) + get(i, j - 1);
                set(i,j, sum);
            }
        }
    }

    public   long rangeSum(  Pair<Integer, Integer> leftTop, Pair<Integer, Integer> bottomRight) {
        return rangeSum(  bottomRight)
                - rangeSum( new Pair<>(leftTop.getFirst() - 1, bottomRight.getSecond()))
                - rangeSum(  new Pair<>(bottomRight.getFirst(), leftTop.getSecond() - 1))
                + rangeSum(  new Pair<>(leftTop.getFirst() - 1, leftTop.getSecond() - 1));
    }

     public long rangeSum(  Pair<Integer, Integer> cell) {
         Integer row = cell.getFirst();
         Integer col = cell.getSecond();
        if(upper){
            if(row>col){
                return get(col, col);
            }
           return get(row, col);
        }else {
            if(row<col){
                return get(row, row);
            }
            return get(row, col);
        }
    }
    public void print( )
    {
        int i, j;

        // Traverse the matrix
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {

                if (!upper && i >= j) {
                    System.out.printf("%d ", values[getIndex(i,j)]);
                } else if (upper && i <= j) {
                    System.out.printf("%d ", values[getIndex(i,j)]);
                } else {
                    System.out.printf("%d ",defaultValue);
                }
            }
            System.out.printf("\n");

        }
        System.out.printf("\n");
    }

    public static void main(String[] args) {
        int size = 135000;
        IntTriangularMatrix triangularMatrix = new IntTriangularMatrix(size,true,0);
        int v = 1;
        for(int i = 0; i< size; i++){
            for(int j = 0; j< size; j++){
                triangularMatrix.set(i,j,v++);
            }
        }
        triangularMatrix.print();
        triangularMatrix.cumulativeSum();
        triangularMatrix.print();
        System.out.println(triangularMatrix.rangeSum(new Pair<>(2, 0), new Pair<>(3, 1)));
    }
}
