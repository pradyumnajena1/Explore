package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

import java.util.List;

/**
 * a game where two players move alternatively and in each move a player can take out 1,2,3 sticks from a heap of sticks. The player who finish the heap wins.
 */
public class ComputeGameState {
    public static void main(String[] args) {
        ArrayUtils.printArray(computeWinningStates(10, List.of(1,2,3) ));
        ArrayUtils.printArray(computeWinningStates(12, List.of(1,3) ));
        System.out.println(getWinner(12, List.of(1,3) ,List.of(1,2)));

    }
    public static int getWinner(int size,List<Integer> moves,List<Integer> players ){
        boolean[] winningStates = computeWinningStates(size, moves);
        if(winningStates[size]){
            return players.get(0);
        }
        return players.get(1);

    }

    private static boolean[] computeWinningStates(int size, List<Integer> moves) {
        boolean[] winningStates = new boolean[size+1];
        winningStates[0] = false;
        for(int i=1;i<=size;i++){
            for(int move:moves){
                if( i - move >= 0 && !winningStates[i - move]){

                    winningStates[i]  = true;
                    break;
                }
            }
        }
        return winningStates;
    }
}
