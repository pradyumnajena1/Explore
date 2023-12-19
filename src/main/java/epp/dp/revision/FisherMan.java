package epp.dp.revision;

import epp.Pair;
import epp.array.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class FisherMan {
    public static void main(String[] args) {
        int[][] matrix = ArrayUtils.createRandomMNMatrix(3, 4, 0, 3);
        ArrayUtils.print2DArray(matrix);
        int max = getMaxFish(matrix, new Pair<>(0, 0), new Pair<>(matrix.length - 1, matrix[0].length - 1));
        System.out.println(max);
    }

    private static int getMaxFish(int[][] matrix, Pair<Integer, Integer> source, Pair<Integer, Integer> destination) {
        Map<Pair<Integer, Integer>, Integer> cache = new HashMap<>();
        return getMaxFishHelper(matrix, source, destination, cache);
    }

    private static int getMaxFishHelper(int[][] matrix, Pair<Integer, Integer> source, Pair<Integer, Integer> destination, Map<Pair<Integer, Integer>, Integer> cache) {

        if (source.equals(destination)) {
            return matrix[source.getFirst()][source.getSecond()];
        }

        if (cache.containsKey(source)) {
            return cache.get(source);
        }
        int down = 0;
        int left = 0;
        if (source.getFirst() < matrix.length - 1) {
            down = getMaxFishHelper(matrix, new Pair<>(source.getFirst() + 1, source.getSecond()), destination, cache);
        }
        if (source.getSecond() < matrix[0].length - 1) {
            left = getMaxFishHelper(matrix, new Pair<>(source.getFirst(), source.getSecond() + 1), destination, cache);
        }
        int result = Math.max(left, down) + matrix[source.getFirst()][source.getSecond()];
        cache.put(source, result);

        return result;
    }
}
