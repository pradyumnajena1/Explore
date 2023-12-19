package epp.string;

import java.util.Arrays;

public class CheckPalindrome {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal,Panama";
       boolean isPalindrome =  checkPalindrome(s);
        System.out.println(isPalindrome);
    }

    private static boolean checkPalindrome(String s) {

        return checkPalindrome(s.toCharArray());
    }

    private static boolean checkPalindrome(char[] chars) {
       int length =  stripNonAlphabets(chars);
        System.out.println(Arrays.toString(chars));
        return checkPalindrome(chars,0,length-1);
    }

    private static boolean checkPalindrome(char[] chars, int start, int end) {
        while (start<end){
            if( Character.toLowerCase( chars[start++])!=Character.toLowerCase(chars[end--]))
                return false;
        }
        return true;
    }

    private static int stripNonAlphabets(char[] chars) {

        int writeIndex = 0;
        for(char ch:chars){
            if(Character.isAlphabetic(ch)){
                chars[writeIndex++] = ch;
            }
        }

        return writeIndex;
    }
}
