package hackerrank.medium;

import java.util.*;

public class StoneDivision {

    public static void main(String[] args) {

        System.out.println(stoneDivision(64L, new ArrayList<>(List.of(2L, 4L, 8L, 16L, 64L))));
      /*  System.out.println(stoneDivision(1L, new ArrayList<>(List.of(1L, 2L))));
        System.out.println(stoneDivision(6L, new ArrayList<>(List.of(3L))));
        System.out.println(stoneDivision(64L, new ArrayList<>(List.of(2L, 4L, 8L, 16L, 32L, 64L))));*/
        System.out.println(stoneDivision(16L, new ArrayList<>(List.of(2L, 4L))));
    }

    public static long stoneDivision(long n, List<Long> s) {
        // Write your code here
        s.sort(Long::compareTo);
        TreeMap<Long, Integer> piles = new TreeMap<>();
        piles.put(n, 1);
        Map<TreeMap<Long, Integer>, Long> cache = new HashMap<>();
        long result = stoneDivision(piles, s, cache);
        System.out.println(cache);
        return result;
    }

    private static long stoneDivision(TreeMap<Long, Integer> piles, List<Long> s, Map<TreeMap<Long, Integer>, Long> cache) {

        Long biggest = piles.lastKey();
        if (biggest <= s.get(0)) {
            return 0;
        }
        if (cache.containsKey(piles)) {
            return cache.get(piles);
        }
        long maxSteps = 0;
        for (int i = s.size() - 1; i >= 0; i--) {
            if (biggest != s.get(i) && biggest % s.get(i) == 0) {
                TreeMap<Long, Integer> newPiles = getModifiedPiles(piles, s, biggest, s.get(i));

                long partial = stoneDivision(newPiles, s, cache);

                long numSteps = 1 + partial;
                maxSteps = Math.max(maxSteps, numSteps);


            }
        }
        cache.put(piles, maxSteps);
        return maxSteps;
    }

    private static TreeMap<Long, Integer> getModifiedPiles(TreeMap<Long, Integer> piles, List<Long> s, Long biggest, long divisor) {
        TreeMap<Long, Integer> newPiles = new TreeMap<>(piles);
        Integer count = newPiles.get(biggest);
        if (count == 1) {
            newPiles.remove(biggest);
        } else {
            newPiles.put(biggest, count - 1);
        }
        long newPileSize = biggest / divisor;
        newPiles.put(newPileSize, (int) (newPiles.getOrDefault(newPileSize, 0) + divisor));
        return newPiles;
    }


}
