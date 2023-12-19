package epp.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IdentityPositionAttacked {
    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();

        int rows = 8;
        for(int i=0;i<rows*rows;i++){

            double random = Math.random();

            values.add(random < 0.15?0:1  );
        }

        int[][] mnMatrix = ArrayUtils.createMNMatrix(rows, rows, values);
        ArrayUtils.print2DArray(mnMatrix);
        identityPositionsAttacked(mnMatrix);
        System.out.println();
        System.out.println();
        ArrayUtils.print2DArray(mnMatrix);
    }

    public static void identityPositionsAttacked(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for(int i=0;i<matrix.length;i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(rows.contains(i)||cols.contains(j)){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
