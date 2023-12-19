package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PickingNumbers {
    public static void main(String[] args) {
        System.out.println(pickingNumbers(new ArrayList<>(List.of(4, 6, 5, 3, 3, 1))));
        System.out.println(pickingNumbers(new ArrayList<>(List.of(1,2,2,3,1,2))));
    }

    public static int pickingNumbers(List<Integer> a) {
        // Write your code here
        TreeMap<Integer, Long> treeMap = new TreeMap(a.stream().collect(Collectors.groupingBy(Function.identity(),
                Collectors.counting())));
        long maxCount = 0;
        for (int value : a) {
            Long count = treeMap.getOrDefault(value,0l);
            Long oneUpCount = treeMap.getOrDefault(value + 1,0l);
            Long oneDownCount = treeMap.getOrDefault(value - 1,0l);
            count = count + Math.max(oneUpCount, oneDownCount);
            maxCount = Math.max(count, maxCount);
        }
        return (int) maxCount;

    }
}
