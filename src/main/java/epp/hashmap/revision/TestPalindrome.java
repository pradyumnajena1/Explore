package epp.hashmap.revision;

import epp.hashmap.MapUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * test if word can be rearranged to form a palindrome
 * ex edified can be arranged to -> deified which is a palindrome
 */
public class TestPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("edified"));
        System.out.println(isPalindrome("level"));
        System.out.println(isPalindrome("levvel"));
        System.out.println(isPalindrome("levvvel"));
        System.out.println(isPalindrome("rotator"));

        System.out.println(isPalindrome2("edified"));
        System.out.println(isPalindrome2("level"));
        System.out.println(isPalindrome2("levvel"));
        System.out.println(isPalindrome2("levvvel"));
        System.out.println(isPalindrome2("rotator"));

        System.out.println(isPalindrome("lvel"));
        System.out.println(isPalindrome2("lvel"));
    }

    /**
     * space complexity O(n) time complexity O(n)
     * @param s
     * @return
     */

    private static boolean isPalindrome(String s) {
        Map<Character, Long> frequencyMap =
                s.chars().mapToObj(x -> Character.valueOf((char) x)).collect(Collectors.groupingBy(Function.identity(),
                Collectors.counting()));

        Long oddCount = frequencyMap.entrySet().stream().filter(x -> x.getValue() % 2 == 1).collect(Collectors.counting());

        return oddCount<=1;
    }

    /**
     * eellv
     * @param s
     * @return
     */
    private static boolean isPalindrome2(String s) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        int count=1;
        char lastChar = charArray[0];
        int oddCount = 0;
        for(int i=1;i<charArray.length;i++){
            if(charArray[i]==lastChar){
                count++;
            }else{
                if(count%2==1){
                    oddCount++;
                }
                lastChar = charArray[i];
                count=1;
            }
        }
        if(count%2==1){
            oddCount++;
        }
        return oddCount<=1;
    }
    }
