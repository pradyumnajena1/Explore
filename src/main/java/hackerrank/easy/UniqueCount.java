package hackerrank.easy;

import epp.array.ArrayUtils;

import java.util.*;

public class UniqueCount {

    public static int getGCD(int a, int b, Map<String, Integer> cache) {
        if (b == 0) {
            return a;
        }
        if (a == 0) {
            return b;
        }
        String key = a + "_" + b;
        if (cache.containsKey(a + "_" + b)) {
            return cache.get(key);
        }
        int gcd;
        if (a > b) {
            gcd = getGCD(b, a % b, cache);
        } else {
            gcd = getGCD(a, b % a, cache);
        }
        cache.put(key, gcd);
        return gcd;
    }

    public static void main(String[] args) {

        int uniqueCount = getUniqueCount(List.of(1, 2, 3), List.of(2, 4, 6), 1, 1, 2, 2);


        System.out.println(uniqueCount);
    }

    private static int getUniqueCount(List<Integer> a, List<Integer> b, int r1, int c1, int r2, int c2) {

        Map<String, Integer> cache = new HashMap<>();

        List<List<Map<Integer, Integer>>> matrix = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j < b.size(); j++) {
                matrix.get(i).add(new HashMap<>());
            }
        }
        int[][] gcdMatrix = new int[a.size()][b.size()];
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                gcdMatrix[i][j] = getGCD(a.get(i), b.get(j), cache);
            }
        }
        ArrayUtils.print2DArray(gcdMatrix);

        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                matrix.get(i).get(j).put(gcdMatrix[i][j], 1);
                if (j > 0) {
                    for(Map.Entry<Integer,Integer> entry:matrix.get(i ).get(j-1).entrySet()){
                        matrix.get(i).get(j).put(entry.getKey(),
                                matrix.get(i).get(j).getOrDefault(entry.getKey(),0)+entry.getValue() );
                    }
                }
                if (i > 0) {
                    for(Map.Entry<Integer,Integer> entry:matrix.get(i-1 ).get(j).entrySet()){
                        matrix.get(i).get(j).put(entry.getKey(),
                                matrix.get(i).get(j).getOrDefault(entry.getKey(),0)+entry.getValue() );
                    }
                }

                if (i > 0 && j > 0) {
                    for(Map.Entry<Integer,Integer> entry:matrix.get(i-1 ).get(j-1).entrySet()){
                        matrix.get(i).get(j).put(entry.getKey(),
                                matrix.get(i).get(j).getOrDefault(entry.getKey(),0)-entry.getValue() );
                    } }
            }
        }
        for (List<Map<Integer, Integer>> row : matrix) {
            System.out.println(row);
        }


        int uniqueCount = 0;
        for (Map.Entry<Integer, Integer> entry : matrix.get(r2).get(c2).entrySet()) {


            Integer key = entry.getKey();
            int prevCol = c1 > 0 ? matrix.get(r1).get(c1 - 1).getOrDefault(key, 0) : 0;
            int prevRow = r1 > 0 ? matrix.get(r1 - 1).get(c1).getOrDefault(key, 0) : 0;
            int prevRowCol = r1 > 0 && c1 > 0 ? matrix.get(r1 - 1).get(c1 - 1).getOrDefault(key, 0) : 0;
            int count = entry.getValue()
                    - prevCol
                    - prevRow
                    + prevRowCol;
            System.out.println(key+" "+count);

            if (count > 0) {
                uniqueCount++;
            }
        }
        return uniqueCount;
    }
}
