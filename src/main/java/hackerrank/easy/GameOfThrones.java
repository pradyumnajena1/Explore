package hackerrank.easy;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameOfThrones {
    public static void main(String[] args) {
        System.out.println(gameOfThrones("aaabbbb"));
        System.out.println(gameOfThrones("cdefghmnopqrstuvw"));
    }

    public static String gameOfThrones(String s) {
        // Write your code here
        Map<Character, Long> freqs = s.chars().mapToObj(x -> Character.valueOf((char) x)).collect(Collectors.groupingBy(Function.identity(),
                Collectors.counting()));
        long oddCount = freqs.entrySet().stream().filter(x -> x.getValue() % 2 == 1).count();
        return oddCount < 2 ? "YES" : "NO";

    }
}
