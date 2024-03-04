package leetcode.medium;

/**
 * Longest Palindromic Substring
 * Medium
 * Topics
 * Companies
 * Hint
 * Given a string s, return the longest
 * palindromic
 *
 * substring
 *  in s.
 */
public class LongestPalindromicSubString {
    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubString().longestPalindrome("babad"));
        System.out.println(new LongestPalindromicSubString().longestPalindrome("cbbd"));
    }
    public String longestPalindrome(String s) {
        String result = "";
        char[] chars = s.toCharArray();
        for(int i=0;i<s.length();i++){
             String  palindrome = getPalindromeLengthWithCenter(chars,i);
             if(palindrome.length()>result.length()){
                 result = palindrome;
             }

         }
        for(int i=1;i<s.length();i++){
            String  palindrome = getPalindromeLengthWithCenter(chars,i-1,i);
            if(palindrome.length()>result.length()){
                result = palindrome;
            }

        }
        return result;
    }

    private String getPalindromeLengthWithCenter(char[] chars, int i) {
        return getPalindromeLengthWithCenter(chars, i,i);
    }

    private   String getPalindromeLengthWithCenter(char[] chars, int left,int right) {

        while(left>=0 && right < chars.length && chars[left]  == chars[right]){
            left--;
            right++;
        }
        return  new String(chars,left+1,right-left-1)  ;
    }
}
