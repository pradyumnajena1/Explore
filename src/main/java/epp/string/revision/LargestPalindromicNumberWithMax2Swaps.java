package epp.string.revision;

import epp.array.ArrayUtils;

public class LargestPalindromicNumberWithMax2Swaps {
    public static void main(String[] args) {
        String palindrome = "4697557964";
        String largestPalindrome = getLargestPalindromeWithMaxSwaps(palindrome,4);
        System.out.println(largestPalindrome);
    }

    private static String getLargestPalindromeWithMaxSwaps(String palindrome, int maxSwap) {
        if(palindrome.length()<=2 || maxSwap<2){
            return palindrome;
        }
        char[] chars = palindrome.toCharArray();
        int start=0;
        int end = palindrome.length()-1;
        Integer maxIndex = null;
        Integer max = 0;
        while (start<end){
            if(chars[ start] >max){
                max = (int) palindrome.charAt(start);
                maxIndex = start;
            }
            start++;
            end--;
        }
        if(maxIndex!=0){
            ArrayUtils.swap(chars,0,maxIndex);
            ArrayUtils.swap(chars,chars.length-1,chars.length-1  - maxIndex);
        }
        String largestPalindromeWithMaxSwaps = getLargestPalindromeWithMaxSwaps(new String(chars, 1, chars.length - 2),
                maxSwap - 2);
        return chars[0]+ largestPalindromeWithMaxSwaps +chars[chars.length-1];
    }
}
