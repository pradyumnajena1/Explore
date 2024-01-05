package guidetocompetitiveprogramming;

import epp.Pair;
import epp.Triplet;

import java.util.*;
import java.util.stream.IntStream;

public class NimSumGame {
    public static void main(String[] args) {
        System.out.println(computeWinningStateForNim(new int[]{1, 2, 2}));
    }

    public static Map<Triplet<Integer, Integer, Integer>, Boolean> computeWinningStateForNim(int[] heaps) {
        Map<Triplet<Integer, Integer, Integer>, Boolean> winningStates = new HashMap<>();
        winningStates.put(new Triplet<>(0, 0, 0), false);
        IntStream.range(0, heaps[0] + 1).boxed()
                .flatMap(x -> IntStream.range(0, heaps[1] + 1).boxed().map(y -> new Pair<>(x, y)))
                .flatMap(pair -> IntStream.range(0, heaps[2] + 1).boxed().map(z -> new Triplet<>(pair.getFirst(), pair.getSecond(), z)))
                .forEach(triplet -> {

            winningStates.put(triplet, getXORSum(triplet) != 0);

        });
        return winningStates;
    }

    private static int getXORSum(Triplet<Integer, Integer, Integer> triplet) {
        return triplet.getFirst() ^ triplet.getSecond() ^ triplet.getThird();
    }
}
