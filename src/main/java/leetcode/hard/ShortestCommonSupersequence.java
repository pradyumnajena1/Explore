package leetcode.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ShortestCommonSupersequence {
    public static void main(String[] args) {
        Solution solution = new Solution();
       // System.out.println(solution.shortestCommonSupersequence("abac", "cab"));
      //  System.out.println(solution.shortestCommonSupersequence("bbbaaaba", "bbababbb"));

        System.out.println(solution.lcs("bbbaaaba", "bbababbb"));
        System.out.println(Arrays.toString( solution.indexofSubsequence("bbbaaaba", "bbaba")));

    }

    private static class Solution {
        public String shortestCommonSupersequence(String str1, String str2) {
            Map<String, String> cache = new HashMap<>();
            return shortestCommonSupersequence(str1, str2, cache);
        }

        private String shortestCommonSupersequence(String str1, String str2, Map<String, String> cache) {
            if (str1.isEmpty()) {
                return str2;
            } else if (str2.isEmpty()) {
                return str1;
            }
            String key = str1 + "_" + str2;
            if (cache.containsKey(key)) {
                return cache.get(key);
            }

            String lcs = lcs(str1, str2);
            String result = null;
            if (lcs.isEmpty()) {
                result = str1 + str2;

            } else {
                int[] index1 = indexofSubsequence(str1, lcs);
                String prefix1;
                String suffix1;
                int[] index2 = indexofSubsequence(str2, lcs);
                String prefix2;
                String suffix2;


                prefix1 = str1.substring(0, index1[0]);
                suffix1 = str1.substring(index1[1] + 1);
                prefix2 = str2.substring(0, index2[0]);
                suffix2 = str2.substring(index2[1] + 1);

                result = shortestCommonSupersequence(prefix1, prefix2) + lcs + shortestCommonSupersequence(suffix1,
                        suffix2);


            }


            cache.put(key, result);
            return result;
        }

        private String lcs(String str1, String str2) {
            Map<String, String> cache = new HashMap<>();
            return lcs(str1, str2, cache);

        }

        private String lcs(String str1, String str2, Map<String, String> cache) {
            if (str1.isEmpty() || str2.isEmpty()) {
                return "";
            }
            String key = str1 + "_" + str2;
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
            String result = "";
            if (str1.charAt(0) == str2.charAt(0)) {
                result = str1.charAt(0) + lcs(str1.substring(1), str2.substring(1));
            } else {
                String lcs1 = lcs(str1, str2.substring(1));
                String lcs2 = lcs(str1.substring(1), str2);
                if (lcs1.length() > lcs2.length()) {
                    result = lcs1;
                } else {
                    result = lcs2;
                }
            }
            cache.put(key, result);
            return result;
        }

        private int[] indexofSubsequence(String str, String subsequence) {
             int i=0;
             int j=0;
             int firstMatch=-1;
             while (i<str.length() && j<subsequence.length()){
                 if(str.charAt(i)==subsequence.charAt(j)){
                     j++;
                     if(firstMatch==-1){
                         firstMatch=i;
                     }
                 }
                 i++;
             }
             return new int[]{firstMatch,i-1};

        }


    }
}
