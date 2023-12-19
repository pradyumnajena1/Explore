package epp.dp.revision;

import epp.Pair;
import epp.Triplet;
import epp.array.ArrayUtils;

import java.util.*;

public class CheckIfArrayAppearsInMatrix {
    public static void main(String[] args) {
        int[][] matrix = ArrayUtils.createRandomMNMatrix(3, 5, 1, 5);
        int[] array = ArrayUtils.randomArray(6, 1, 5);
        ArrayUtils.print2DArray(matrix);
        ArrayUtils.printArray(array);
        List<Pair<Integer, Integer>> sequence = isPresent(matrix, array);
        System.out.println(sequence);

        System.out.println( ArrayUtils.getNeighbours(matrix.length-1,matrix[0].length-1, 2, 0, false));
    }

    private static List<Pair<Integer, Integer>> isPresent(int[][] matrix, int[] array) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == array[0]) {
                    List<Pair<Integer, Integer>> list = isPresent(matrix, array, new Pair<>(i, j), 0);
                    if (list != null) {
                        return list;
                    }
                }
            }
        }
        return null;
    }

    private static List<Pair<Integer, Integer>> isPresent(int[][] matrix, int[] array, Pair<Integer, Integer> source, int index) {
        Comparator<Triplet<Integer, Integer, Integer>> tripletComparator =
                Comparator.comparingInt((Triplet<Integer, Integer, Integer> x)->x.getFirst())
                        .thenComparingInt((Triplet<Integer, Integer, Integer> x)->x.getSecond())
                        .thenComparingInt((Triplet<Integer, Integer, Integer> x)->x.getThird());
        Map<Triplet<Integer, Integer, Integer>, List<Pair<Integer, Integer>>> cache =
                new TreeMap<>(tripletComparator);


        List<Pair<Integer, Integer>> result = isPresentRecurse(matrix, array, source, index, cache);
        for(Map.Entry<Triplet<Integer, Integer, Integer>, List<Pair<Integer, Integer>>> entry:cache.entrySet()){
            System.out.println(entry);
        }
        return result;
    }

    private static List<Pair<Integer, Integer>> isPresentRecurse(int[][] matrix, int[] array, Pair<Integer, Integer> source, int index, Map<Triplet<Integer, Integer, Integer>, List<Pair<Integer, Integer>>> cache) {
        if (index == array.length) {
            return new ArrayList<>();
        }
        if (matrix[source.getFirst()][source.getSecond()] != array[index]) {
            return null;
        }

        Triplet<Integer, Integer, Integer> key = new Triplet<>(source.getFirst(), source.getSecond(), index);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        List<Pair<Integer, Integer>> neighbours =ArrayUtils.getNeighbours(matrix.length-1,matrix[0].length-1, source,
                true);
        for (Pair<Integer, Integer> neighbour : neighbours) {

            List<Pair<Integer, Integer>> partial = isPresentRecurse(matrix, array, neighbour, index + 1, cache);
            if (partial != null) {
                List<Pair<Integer, Integer>> result = new ArrayList<>();
                result.add(source);
                result.addAll(partial);

                cache.put(key, result);
                break;

            }
        }
        return cache.get(key);
    }


}
