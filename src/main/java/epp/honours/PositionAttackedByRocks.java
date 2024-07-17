package epp.honours;

import epp.array.ArrayUtils;

import java.util.HashSet;
import java.util.Set;

public class PositionAttackedByRocks {

    public static void main(String[] args){
        int[][] matrix = {
                {1, 1, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 1},
                {1, 1, 1, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1}
        };
         markPositionsAttackedByRocks(matrix);
        ArrayUtils.print2DArray(matrix);
    }

    private static void markPositionsAttackedByRocks(int[][] matrix) {
        Set<Integer> rowPositions = new HashSet<>( );
        Set<Integer> colPositions = new HashSet<>( );
        for (int i = 0; i < matrix.length;i++) {
            for(int j = 0; j < matrix[0].length;j++) {
                if(matrix[i][j]==0){
                    rowPositions.add(i);
                    colPositions.add(j);
                }
            }
        }

        for (int i = 0; i < matrix.length;i++) {
            for(int j = 0; j < matrix[0].length;j++) {
                if(rowPositions.contains(i) || colPositions.contains(j)){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
