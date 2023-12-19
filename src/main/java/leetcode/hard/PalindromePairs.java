package leetcode.hard;

import epp.array.ArrayUtils;

import java.util.*;

public class PalindromePairs {
    public static void main(String[] args) {
     Solution solution = new Solution();
        /*System.out.println(solution.getPrefixes("abcd"));*/
         System.out.println(solution.palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"}));
         System.out.println(solution.palindromePairs(new String[]{"bat","tab","cat"}));
         System.out.println(solution.palindromePairs(new String[]{"a","" }));

    }
   private static class Solution {
        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> result = new ArrayList<>();
            Map<String,List<Integer>> suffixMap = new HashMap<>();
            for(int i=0;i< words.length;i++){
                List<String> suffixes = getSuffixes(words[i]);
                for(String suffix:suffixes){
                    List<Integer> suffixList = suffixMap.getOrDefault(suffix, new ArrayList<>());
                    suffixList.add(i);
                    suffixMap.put(suffix,suffixList);
                }
            }
            for(int i=0;i< words.length;i++){

                List<String> prefixes = getPrefixes(words[i]);
                prefixes.sort((x,y)-> -Integer.compare(x.length(),y.length()));
               outer: for(String prefix:prefixes){
                    if(suffixMap.containsKey(prefix)){
                        List<Integer> suffixList = suffixMap.get(prefix);
                        for(int wordIndex:suffixList){
                            if(wordIndex!=i && isPalindromePair(words,i,wordIndex)){
                                ArrayList<Integer> resultpair = new ArrayList<>();
                                resultpair.add(i);
                                resultpair.add(wordIndex);
                                result.add(resultpair);
                                break outer;
                            }
                        }

                    }
                }
            }
            System.out.println(suffixMap);

            return result;
        }

       private boolean isPalindromePair(String[] words, int i, int wordIndex) {
            String newWord = words[i]+words[wordIndex];
            int low=0;
            int high = newWord.length()-1;
            while (low<high&& newWord.charAt(low)==newWord.charAt(high)){
                low++;
                high--;
            }
           return low>=high;
       }

       private String reverse(String word) {
           char[] chars = word.toCharArray();
           int low = 0;
           int high = chars.length-1;
           while (low<high){
                swap(chars,low++,high--);
           }
           return new String(chars);
       }

       private void swap(char[] chars, int i, int j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
       }

       private List<String> getSuffixes(String word) {
           List<String> suffixes = new ArrayList<>();
          /* if(word.isEmpty()){
               suffixes.add(word);
               return suffixes;
           }*/
           for(int i=0;i<=word.length();i++){
               suffixes.add(word.substring(i));
           }
           return suffixes;
       }
       private List<String> getPrefixes(String word) {
           List<String> prefixes = new ArrayList<>();
           /*if(word.isEmpty()){
               prefixes.add(word);
               return prefixes;
           }*/
           for(int i=0;i<=word.length();i++){
               prefixes.add(word.substring(0,i));
           }
           return prefixes;
       }
   }
}
