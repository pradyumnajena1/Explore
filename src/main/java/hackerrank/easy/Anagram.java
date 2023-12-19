package hackerrank.easy;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Anagram {
    public static void main(String[] args) {
        System.out.println(anagram("abccde"));
        System.out.println(anagram("aaabbb"));
        System.out.println(anagram("ab"));
        System.out.println(anagram("abc"));
        System.out.println(anagram("mnop"));
        System.out.println(anagram("xyyx"));
        System.out.println(anagram("xaxbbbxx"));
    }

    public static int anagram(String s) {
        // Write your code here
        if(s.length()%2==1){
            return -1;
        }
        int mid = s.length() / 2;
        String first = s.substring(0, mid);
        String second = s.substring(mid);
        Map<Character, Long> firstFrequency = getFrequencyMap(first);
        Map<Character, Long> secondFrequency = getFrequencyMap(second);
        int editCount = 0;
        for (Map.Entry<Character, Long> entry : firstFrequency.entrySet()) {
            long firstC = entry.getValue();
            long secondC = secondFrequency.getOrDefault(entry.getKey(), 0L);
            if (firstC > secondC) {
                editCount += firstC - secondC;
            }
        }
        return editCount;
    }

    private static Map<Character, Long> getFrequencyMap(String first) {
        return first.chars().mapToObj(x -> Character.valueOf((char) x)).collect(Collectors.groupingBy(Function.identity(),
                Collectors.counting()));
    }
}
