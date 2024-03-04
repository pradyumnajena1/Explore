package meta;

import epp.Pair;
import epp.array.ArrayUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckKPalindrome {
    public static void main(String[] args) {
        String s="abcdecba";
        boolean isPalindrome = isPalindrome(s,2);
        System.out.println(isPalindrome);
    }

    private static boolean isPalindrome(String s, int k) {
        String target = reverse(s);
        int minEditDistance = getEditDistance(s,target);
        return k<=minEditDistance;
    }

    private static int getEditDistance(String a, String b) {
        int[][] dist = new int[a.length()+1][b.length()+1];
        dist[0][0] = 0;
        for(int i=1;i<=a.length();i++){
            dist[0][i] = i;
        }
        for(int i=1;i<=b.length();i++){
            dist[i][0] = i;
        }
        for(int i=1;i<=a.length();i++){
            for(int j=1;j<=b.length();j++){

                if(a.charAt(i-1) == b.charAt(j-1)){
                    dist[i][j] = dist[i-1][j-1];
                }else{
                    dist[i][j] = 1 + Math.min(dist[i-1][j],dist[i][j-1]);
                }
            }
        }
        ArrayUtils.print2DArray(dist);
        return dist[a.length()][b.length()];
    }



    private static String reverse(String s) {
        char[] charArray = s.toCharArray();
        int left = 0;
        int right = charArray.length-1;
        while (left<right){
            swap(charArray,left,right);
            left++;
            right--;
        }
        return new String(charArray);
    }

    private static void swap(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }
}
