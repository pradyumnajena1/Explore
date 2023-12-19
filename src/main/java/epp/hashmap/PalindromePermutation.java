package epp.hashmap;

import java.util.Arrays;
import java.util.Map;

public class PalindromePermutation {
    public static void main(String[] args) {
        System.out.println(checkPalindromicPermutation("level"));
        System.out.println(checkPalindromicPermutation2("level"));
        System.out.println(checkPalindromicPermutation("edified"));
        System.out.println(checkPalindromicPermutation2("edified"));
    }

    public static boolean checkPalindromicPermutation(String s) {
        Map<Character, Integer> frequencyMap = MapUtils.getFrequencyMap(s);
        int oddCount=0;
        for(Map.Entry<Character,Integer> entry:frequencyMap.entrySet()){
            if(entry.getValue()%2==1){
                oddCount++;
            }
        }
        return oddCount<=1;
    }
    public static boolean checkPalindromicPermutation2(String s){
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        char lastChar = chars[0];
        int lastCharCount = 1;
        int oddCount = 0;
        for(int i=1;i<chars.length;i++){
            char currentChar = chars[i];
            if(currentChar==lastChar){
                lastCharCount++;
            }else{
                if(lastCharCount%2==1){
                    oddCount++;
                }
                lastCharCount=1;
                lastChar = currentChar;
            }
        }
        if(lastCharCount%2==1){
            oddCount++;
        }
        return oddCount<=1;

    }
}
