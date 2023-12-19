package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MatchingStrings {
    public static void main(String[] args) {
        System.out.println(matchingStrings(new ArrayList<>(List.of("ab", "ab", "abc")), new ArrayList<>(List.of("ab", "abc", "bc"))));
    }

    public static List<Integer> matchingStrings(List<String> stringList, List<String> queries) {
        // Write your code here
        Map<String, Long> freq = stringList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Integer> result = new ArrayList<>();
        for(String query:queries){
            Long l1 = freq.getOrDefault(query,0l);
            int l =   l1.intValue();
            result.add(l);
        }
        return result;
    }
}
