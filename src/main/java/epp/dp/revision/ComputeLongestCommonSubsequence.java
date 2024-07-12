package epp.dp.revision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComputeLongestCommonSubsequence {
    public static void main(String[] args){
        String str1 = "AGGTAB";
        String str2 = "GXTXAYB";
        System.out.println("Length of LCS is: " + lcs(str1, str2));
    }

    private static String lcs(String str1, String str2) {
        Map<List<Integer>,String> cache = new HashMap<>();
        return lcs(str1, str2,0,0,cache);
    }

    private static String lcs(String str1, String str2, int i, int j, Map<List<Integer>, String> cache) {
       if(i==str1.length() || j==str2.length()) {
           return "";
       }
        List<Integer> key = List.of(i, j);
        if(!cache.containsKey(key)) {
            String lcs = "";
            if(str1.charAt(i)==str2.charAt(j)) {
                  lcs =str1.charAt(i) + lcs(str1, str2, i+1, j+1, cache) ;

            }else{
               String lcs1 =  lcs(str1, str2, i+1, j, cache) ;
               String lcs2 =  lcs(str1, str2, i, j+1, cache) ;
               lcs = lcs1.length() > lcs2.length()? lcs1 : lcs2;
            }
            cache.put(key, lcs);

        }

        return cache.get(key);
    }
}
