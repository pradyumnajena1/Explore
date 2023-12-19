package epp.dp.revision;

import epp.Pair;
import epp.Triplet;
import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PickUpCoinsForMaxGain {
    public static void main(String[] args) {
        int[] coins = ArrayUtils.randomArray(4, 10, 50);
        ArrayUtils.printArray(coins);
        Pair<Integer, Integer> maxWin = getMaxDiffWinForFirstPlayer(coins);
        System.out.println(maxWin);

        getMaxDiffWinForFirstPlayer2(coins);
    }

    private static int getMaxDiffWinForFirstPlayer2(int[] coins) {
        int[][] maxScore = new int[coins.length][coins.length];
        for (int i = 0; i < maxScore.length; i++) {

            Arrays.fill(maxScore[i], -1);
        }
        getMaxDiffWinForFirstPlayer2(coins, 0, coins.length - 1, maxScore);
        ArrayUtils.print2DArray(maxScore);
        return 0;

    }

    private static int getMaxDiffWinForFirstPlayer2(int[] coins, int start, int end, int[][] maxScore) {
        if (start > end) {
            return 0;
        }
        if(maxScore[start][end]==-1){
            maxScore[start][end] = Math.max(
                    coins[start] + Math.min(
                            getMaxDiffWinForFirstPlayer2(coins, start + 1, end - 1, maxScore),
                            getMaxDiffWinForFirstPlayer2(coins, start + 2, end, maxScore)
                    ),

                    coins[end] + Math.min(
                            getMaxDiffWinForFirstPlayer2(coins, start + 1, end - 1, maxScore),
                            getMaxDiffWinForFirstPlayer2(coins, start, end - 2, maxScore)
                    )

            );
        }

        return maxScore[start][end];
    }

    private static Pair<Integer, Integer> getMaxDiffWinForFirstPlayer(int[] coins) {
        return getMaxDiffWinForFirstPlayer(coins, 0, coins.length - 1, 0);
    }

    private static Pair<Integer, Integer> getMaxDiffWinForFirstPlayer(int[] coins, int start, int end, int turn) {
        Map<Triplet<Integer, Integer, Integer>, Pair<Integer, Integer>> cache = new HashMap<>();
        return getMaxDiffWinForFirstPlayerHelper(coins, start, end, turn, cache);
    }

    private static Pair<Integer, Integer> getMaxDiffWinForFirstPlayerHelper(int[] coins, int start, int end, int turn,

                                                                            Map<Triplet<Integer, Integer, Integer>, Pair<Integer, Integer>> cache) {
        if (start > end) {
            return new Pair<>(0, 0);
        }
        Triplet<Integer, Integer, Integer> key = new Triplet<>(start, end, turn);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        Pair<Integer, Integer> result;
        if (turn % 2 == 0) {
            //play for max
            Pair<Integer, Integer> left = getMaxDiffWinForFirstPlayerHelper(coins, start + 1, end, turn + 1,
                    cache);
            Pair<Integer, Integer> right = getMaxDiffWinForFirstPlayerHelper(coins, start, end - 1, turn + 1,
                    cache);
            if (left.getFirst() + coins[start] - left.getSecond() > right.getFirst() + coins[end] - right.getSecond()) {
                result = new Pair<>(left.getFirst() + coins[start], left.getSecond());
            } else {
                result = new Pair<>(right.getFirst() + coins[end], right.getSecond());
            }


        } else {
            //play for min
             Pair<Integer, Integer> left = getMaxDiffWinForFirstPlayerHelper(coins, start + 1, end, turn + 1,
                    cache);
            Pair<Integer, Integer> right = getMaxDiffWinForFirstPlayerHelper(coins, start, end - 1, turn + 1,
                    cache);
            if (left.getFirst() - left.getSecond() + coins[start] > right.getFirst() - right.getSecond() + coins[end]) {
                result = new Pair<>(left.getFirst(), left.getSecond() + coins[start]);
            } else {
                result = new Pair<>(right.getFirst(), right.getSecond() + coins[end]);
            }


        }
        cache.put(key, result);
        return cache.get(key);
    }
}
