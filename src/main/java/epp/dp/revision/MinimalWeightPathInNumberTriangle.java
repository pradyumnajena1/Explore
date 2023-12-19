package epp.dp.revision;

import epp.Pair;
import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimalWeightPathInNumberTriangle {
    public static void main(String[] args) {
        int[][] pascalTriangle = ArrayUtils.generatePascalTriangle(15,true);
        ArrayUtils.print2DArray(pascalTriangle);
        Pair<List<Integer>, Integer> minWeightPath = getMinWeightPath(pascalTriangle, 0, 0, pascalTriangle.length - 1);
        System.out.println(minWeightPath);
    }

    private static Pair<List<Integer>, Integer> getMinWeightPath(int[][] pascalTriangle, int row, int col, int lastRow) {


        Map<Pair<Integer, Integer>, Pair<List<Integer>, Integer>> cache = new HashMap<>();
        return getMinWeightPathHelper(pascalTriangle, row, col, lastRow, cache);
    }

    private static Pair<List<Integer>, Integer> getMinWeightPathHelper(int[][] pascalTriangle, int row, int col, int lastRow, Map<Pair<Integer, Integer>, Pair<List<Integer>, Integer>> cache) {
        if (row == lastRow + 1) {
            ArrayList<Integer> resultPath = new ArrayList<>();
            return new Pair<>(resultPath, 0);
        }
        Pair<Integer, Integer> key = new Pair<>(row, col);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int minWeight = Integer.MAX_VALUE;
        List<Integer> minPath = null;
        Pair<List<Integer>, Integer> result = null;
        for (int i = 0; i <= row; i++) {

            Pair<List<Integer>, Integer> partialResult = getMinWeightPathHelper(pascalTriangle, row + 1, i, lastRow, cache);

            if (minWeight > partialResult.getSecond()) {
                minPath = new ArrayList<>();
                minPath.add(pascalTriangle[row][col]);
                minPath.addAll(partialResult.getFirst());
                minWeight = partialResult.getSecond();
                result = new Pair<>(minPath, minWeight + pascalTriangle[row][col]);
            }
        }
        cache.put(key, result);
        return result;
    }

}
