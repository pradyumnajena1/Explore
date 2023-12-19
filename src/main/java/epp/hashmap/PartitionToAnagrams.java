package epp.hashmap;

import java.util.*;

public class PartitionToAnagrams {

    public static void main(String[] args) {
        Set<String> words = Set.of("eleven plus two","twelve plus one","dog","god");
        Map<Map<Character,Integer>, List<String>> anagrams = partitionToAnagrams(words);
        System.out.println(anagrams);
    }

    public static Map<Map<Character,Integer>, List<String>> partitionToAnagrams(Set<String> words) {
        Map<Map<Character,Integer>, List<String>> result  = new HashMap<>();
        for(String word:words){
            Map<Character,Integer> freqMap = MapUtils.getFrequencyMap(word);
            List<String> anagrams = result.getOrDefault(freqMap, new ArrayList<>());
            anagrams.add(word);
            result.put(freqMap,anagrams);
        }
        return result;
    }

}
