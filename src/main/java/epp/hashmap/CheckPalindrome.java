package epp.hashmap;

import java.util.HashMap;
import java.util.Map;

public class CheckPalindrome {
    public static void main(String[] args) {
        System.out.println(checkPalindrome("GATTAACAG"));
        System.out.println(checkPalindrome("GATTAAAG"));
    }

    private static boolean checkPalindrome(String s) {
        Map<Character, Integer> frequencyMap = buildFrequencyMap(s);
        System.out.println(frequencyMap);
        int oddCount =0;
        for(Map.Entry<Character,Integer> entry:frequencyMap.entrySet()){
            if(entry.getValue()%2==1){
                oddCount++;
            }
        }
        return oddCount<=1;
    }

    private static Map<Character, Integer> buildFrequencyMap(String s) {
        Map<Character,Integer> countMap = new HashMap<>();
        for(Character ch: s.toCharArray()){
            countMap.put(ch, countMap.getOrDefault(ch,0) +1);

        }
        return countMap;
    }
}
