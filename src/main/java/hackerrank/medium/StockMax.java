package hackerrank.medium;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StockMax {
    public static void main(String[] args) {
        System.out.println(stockmax(new ArrayList<>(List.of(5, 3, 2))));

        System.out.println(stockmax(new ArrayList<>(List.of(1, 2, 100))));
        System.out.println(stockmax(new ArrayList<>(List.of(1, 3, 1, 2))));
        System.out.println(stockmax(new ArrayList<>(List.of(5, 4, 3, 4, 5))));
    }

    public static long stockmax(List<Integer> prices) {
        // Write your code here
        Map<Integer, Long> cache = new HashMap<>();

        return stockmax(prices, 0, cache);
    }

    private static long stockmax(List<Integer> prices, int start, Map<Integer, Long> cache) {
        if (start >= prices.size() - 1) {
            return 0;
        }
        if (cache.containsKey(start)) {
            return cache.get(start);
        }
        TreeMap<Integer, Long> smaller = new TreeMap<>(prices.subList(start, prices.size()).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
        long maxProfit = 0;
        for (int i = prices.size() - 1; i >= start; i--) {
            //
            int current = prices.get(i);
            Long count = smaller.getOrDefault(current, 0l);
            if (count == 1) {
                smaller.remove(current);
            } else {

                smaller.put(current, count - 1);
            }
            // System.out.println(current + " "+ smaller);
            SortedMap<Integer, Long> smallerValues = smaller.headMap(current);
            long sum = smallerValues.entrySet().stream().mapToLong(x -> (current - x.getKey()) * x.getValue()).sum();
            long profit = sum + stockmax(prices, i + 1, cache);
            maxProfit = Math.max(profit, maxProfit);

        }
        cache.put(start, maxProfit);
        return maxProfit;
    }


}
