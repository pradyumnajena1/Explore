package hackerrank.easy;

import java.util.*;

public class Gemstones {
    public static void main(String[] args) {
        System.out.println(gemstones(new ArrayList<>(List.of("abc", "bc", "abc"))));
    }

    public static int gemstones(List<String> arr) {
        // Write your code here
        Map<Character,Integer> frequencyMap = new HashMap<>();
        for(String value:arr){
            Set<Character> uniques = new HashSet<>();
            for(int i=0;i<value.length();i++){
                char currentChar = value.charAt(i);
                if(!uniques.contains(currentChar)){

                    frequencyMap.put(currentChar,frequencyMap.getOrDefault(currentChar,0)+1);
                    uniques.add(currentChar);
                }

            }
        }
        long count = frequencyMap.entrySet().stream().filter(x -> x.getValue() == arr.size()).count();
        return (int) count;

    }
}
