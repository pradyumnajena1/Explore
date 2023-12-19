package leetcode.hard;

public class ShortestPalindrome {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String shortestPalindrome ;
         shortestPalindrome= solution.shortestPalindrome("aacecaaa");
        System.out.println(shortestPalindrome);
         shortestPalindrome = solution.shortestPalindrome("abcd");
        System.out.println(shortestPalindrome);
    }
   private static class Solution {
        public String shortestPalindrome(String s) {
          int mid = s.length()/2;
          int maxLength = 0;
          int maxPosition = 0;
          String minAdd = "";
          for(int i=mid;i>=0;i--){
              int left = i;
              int right = i;
              while(left>=0 && right<=s.length()-1 && s.charAt(left)==s.charAt(right)){
                  left--;
                  right++;
              }
              int length = right-left-1;
              if(left==-1 &&  length>maxLength){
                  maxLength = length;
                  maxPosition = i;
                  minAdd = s.substring(maxPosition+maxLength/2+1);
                  System.out.println(s.charAt( maxPosition)+" "+maxLength);
              }

                left = i-2;
                right = i+1;
              while(left>=0 && right<=s.length()-1 && s.charAt(left)==s.charAt(right)){
                  left--;
                  right++;
              }
                length = right-left-1;
              if(left==-1 &&  length>maxLength){
                  maxLength = length;
                  maxPosition = i;
                  minAdd = s.substring(maxPosition+maxLength/2);
                  System.out.println(s.charAt( maxPosition-1)+" "+s.charAt( maxPosition)+" "+maxLength);
              }


          }
          return minAdd;
        }
    }
}
