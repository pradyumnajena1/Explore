package epp.array.revision;

import epp.array.ArrayUtils;

import java.util.HashSet;
import java.util.Set;

public class PositionAttackedByRocksInChess {
    public static void main(String[] args) {
        int[][] board = new int[8][8];
        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[0].length;j++){
                board[i][j]=1;
            }
        board[0][1] = 0;
        board[3][5] = 0;
        board[4][3] = 0;
        board[6][0] = 0;
        board[6][5] = 0;
                ArrayUtils.print2DArray(board);
        erasePositionAttackedByRocks(board);
        ArrayUtils.print2DArray(board);
    }

    private static void erasePositionAttackedByRocks(int[][] board) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]==0){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++) {
                if(rows.contains(i)||cols.contains(j)){
                    board[i][j] = 0;
                }
            }
        }
    }
}
