package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MigratoryBirds {
    public static void main(String[] args) {
       // System.out.println(migratoryBirds(new ArrayList<>(List.of(1, 1, 2, 2, 3))));
        //System.out.println(migratoryBirds(new ArrayList<>(List.of(1, 4, 4, 4, 5, 3))));
        System.out.println(migratoryBirds(new ArrayList<>(List.of(1,2,3,4,5,4,3,2,1,3,4))));
    }

    public static int migratoryBirds(List<Integer> arr) {
        // Write your code here
        Map<Integer, Long> freq = arr.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Long max = null;
        Integer maxId = null;
        for (Map.Entry<Integer, Long> entry : freq.entrySet()) {

            if (max == null) {
                max = entry.getValue();
                maxId = entry.getKey();
            } else {

                if (entry.getValue() > max) {
                    maxId = entry.getKey();
                    max= entry.getValue();
                } else if (entry.getValue() == max && entry.getKey() < maxId) {
                    maxId = entry.getKey();
                    ;max= entry.getValue();
                }

            }
        }
        return maxId;
    }
}
