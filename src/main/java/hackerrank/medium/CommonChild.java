package hackerrank.medium;

import epp.Pair;
import epp.array.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class CommonChild {
    public static void main(String[] args) {
        System.out.println(commonChild("ABCD", "ABDC"));
        System.out.println(commonChild("HARRY", "SALLY"));
        System.out.println(commonChild("AA", "BB"));
        System.out.println(commonChild("SHINCHAN", "NOHARAAA"));
    }

    public static int commonChild(String s1, String s2) {
        // Write your code here
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for(int i=0;i<=s1.length();i++){
            for(int j=0;j<=s2.length();j++){

                if(i==0||j==0){
                    dp[i][j]=0;
                }else if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1+dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        //ArrayUtils.print2DArray(dp);
        return dp[s1.length()][s2.length()];
    }


}
