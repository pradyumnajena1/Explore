package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GetMissingNumbers {
    public static void main(String[] args) {
        ArrayList<Integer> brr = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        arr.addAll(List.of(7, 2, 5, 3, 5, 3));
        brr.addAll(List.of(7, 2, 5, 4, 6, 3, 5, 3));
        System.out.println(missingNumbers(arr, brr));

    }

    public static List<Integer> missingNumbers(List<Integer> arr, List<Integer> brr) {
        // Write your code here
        List<Integer> result = new ArrayList<>();
        TreeMap<Integer, Long> frequencyMapA = new TreeMap<>(arr.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
        TreeMap<Integer, Long> frequencyMapB = new TreeMap<>(brr.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
        for (Map.Entry<Integer, Long> entry : frequencyMapB.entrySet()) {
            Long count = frequencyMapA.get(entry.getKey());
            if (count == null || count < entry.getValue()) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
}
