package hackerrank.easy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BeautifulPairs {
    public static void main(String[] args) {
        System.out.println(beautifulPairs(new ArrayList<>(List.of(1, 2, 3, 4)), new ArrayList<>(List.of(1, 2, 3, 3))));
        System.out.println(beautifulPairs(new ArrayList<>(List.of(3, 5, 7, 11, 5, 8)), new ArrayList<>(List.of(5, 7, 11, 10, 5, 8))));
    }

    public static int beautifulPairs(List<Integer> A, List<Integer> B) {
        // Write your code here

        Map<Integer, Long> freqA = A.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<Integer, Long> freqB = B.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        // System.out.println(getPairCount(freqA,freqB));
        change(B, freqA, freqB);

        return getPairCount(freqA, freqB);
    }


    private static int getPairCount(Map<Integer, Long> freqA, Map<Integer, Long> freqB) {
        int numPairs = 0;
        for (Map.Entry<Integer, Long> entry : freqA.entrySet()) {
            Integer key = entry.getKey();
            Long afreq = entry.getValue();
            Long bfreq = freqB.getOrDefault(key, 0L);


            numPairs += Math.min(afreq, bfreq);

        }
        return numPairs;
    }


    private static void change(List<Integer> B, Map<Integer, Long> freqA, Map<Integer, Long> freqB) {
        Comparator<Map.Entry<Integer, Long>> frequencyComparator = Comparator.comparingLong((Map.Entry<Integer, Long> x) -> x.getValue()).reversed();

        Comparator<Map.Entry<Integer, Long>> presenceComparatorA = Comparator.comparingLong(x -> freqB.containsKey(x.getKey()) ? 1L : 0L);
        Comparator<Map.Entry<Integer, Long>> presenceComparatorB = Comparator.comparingLong(x -> freqA.containsKey(x.getKey()) ? 1L : 0L);
        List<Map.Entry<Integer, Long>> sortedA = freqA.entrySet().stream()
                .sorted(presenceComparatorA.thenComparing(frequencyComparator)).collect(Collectors.toList());

        List<Map.Entry<Integer, Long>> sortedB = freqB.entrySet().stream()
                .sorted(presenceComparatorB.thenComparing(frequencyComparator)).collect(Collectors.toList());


        if (sortedA.size() > 0 && sortedB.size() > 0) {
            Integer current = sortedB.get(0).getKey();
            Integer replacement = sortedA.get(0).getKey();

            long newFrequency = freqB.getOrDefault(current, 1L) - 1;
            if (newFrequency > 0) {
                freqB.put(current, newFrequency);
            } else {
                freqB.remove(current);
            }
            freqB.put(replacement, freqB.getOrDefault(replacement, 0L) + 1);

        }
    }

}
