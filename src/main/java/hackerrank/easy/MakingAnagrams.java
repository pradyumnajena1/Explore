package hackerrank.easy;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MakingAnagrams {
    public static void main(String[] args) {
        System.out.println(makingAnagrams("abc", "amnop"));
        System.out.println(makingAnagrams("cde", "abc"));
        System.out.println(makingAnagrams("absdjkvuahdakejfnfauhdsaavasdlkj", "djfladfhiawasdkjvalskufhafablsdkashlahdfa"));
    }

    public static int makingAnagrams(String s1, String s2) {
        // Write your code here
        Map<Character, Long> frequencyMap1 = getFrequencyMap(s1);
        Map<Character, Long> frequencyMap2 = getFrequencyMap(s2);
        int remainCount=0;
        for(Map.Entry<Character, Long> entry:frequencyMap1.entrySet()){
            long count1 = entry.getValue();
            long count2 = frequencyMap2.getOrDefault(entry.getKey(),0l);
            remainCount+= Math.min(count1,count2)*2;
        }

        return s1.length()+s2.length()-remainCount;

    }
    private static Map<Character, Long> getFrequencyMap(String first) {
        return first.chars().mapToObj(x -> Character.valueOf((char) x)).collect(Collectors.groupingBy(Function.identity(),
                Collectors.counting()));
    }
}
