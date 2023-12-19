package epp.array.revision;

public class BoardGame {
    public static void main(String[] args) {
        int[] board = new int[]{3,2,0,0,2,0,1};
        boolean canWin = canWin(board);
        System.out.println(canWin);
    }

    private static boolean canWin(int[] board) {
        return canWin(board,0);
    }

    private static boolean canWin(int[] board, int index) {
        if(index>=board.length || board[index]+index>=board.length ){
            return true;
        }
        for(int i= 1;i<= board[index];i++){
            if(canWin(board,index+i)){
                return true;
            }
        }
        return false;
    }
}
