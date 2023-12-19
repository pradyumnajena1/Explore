package hackerrank.easy;

import java.util.Arrays;

public class FunnyString {
    public static void main(String[] args) {
        System.out.println(funnyString("lmnop"));
    }
    public static String funnyString(String s) {
        // Write your code here
         if(isFunnyString(s)){
             return "Funny";
         }
         return "Not Funny";
    }

    private static boolean isFunnyString(String s) {
         String reverse = getReverse(s);
         int[] diffs =  getSuccessiveElementDiff(s);
         int[] diffRev =  getSuccessiveElementDiff(reverse);
       return Arrays.equals(diffRev,diffs);
    }

    private static int[] getSuccessiveElementDiff(String s) {
        int[] diff = new int[s.length()-1];
        for(int i=0;i<s.length()-1;i++){
            diff[i] = Math.abs(s.charAt(i+1)-s.charAt(i));
        }
        return diff;
    }

    private static String getReverse(String s) {
        char[] charArray = s.toCharArray();
        int start =0;
        int end = charArray.length-1;
        while (start<end){
            char t = charArray[start];
            charArray[start] = charArray[end];
            charArray[end] = t;
            start++;
            end--;
        }
        return new String(charArray);
    }
}
