package leetcode.hard;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class SubstringWithConcatanation {
    public static void main(String[] args) {
       Solution solution = new Solution();
        System.out.println(solution.findSubstring("barfoofoobarthefoobarman", new String[]{"bar","foo","the"}));
    }

   private static class Solution {


       /**
        * n^2 complexity
        * @param s
        * @param words
        * @return
        */
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> result = new ArrayList<>();
            int wordLength = words[0].length();
            int substringLength = words.length* wordLength;
            Map<String, Long> wordSet =  Arrays.stream(words).collect(Collectors.groupingBy(Function.identity(),
                    HashMap::new, Collectors.counting()));

           for(int i=0;i+substringLength<=s.length();i++){
               String substring = s.substring(i,i+substringLength);
               Map<String, Long> splits =  getSplits(substring, wordLength);
               if(wordSet.equals(splits)){
                   result.add(i);
               }
           }

           return result;
        }

       private Map<String, Long> getSplits(String string, int length) {
           Map<String, Long> result = IntStream.range(0, string.length()).boxed()
                   .filter(i -> i % length == 0)
                   .map(i -> string.substring(i, i + length))
                  .collect(Collectors.groupingBy(Function.identity(),HashMap::new,Collectors.counting()));
           return result;
       }
   }
}

