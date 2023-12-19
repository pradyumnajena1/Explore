package epp.hashmap.revision;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindAllSubstringsComposedOfGivenSetOfStrings {
    public static void main(String[] args) {
        String s = "amanaplanacanaplal";
        List<String> strings = List.of("can","apl","ana","apl");

        System.out.println( List.of(1,1,2,3).stream().collect(Collectors.groupingBy(Function.identity(),
                Collectors.summingInt(e -> 1))));
        Set<String> allsubstrings = findAllSubstrings(s,strings);
        System.out.println(allsubstrings);
    }

    private static Set<String> findAllSubstrings(String s, List<String> words) {
       Map<String,Integer> dictionary =  words.stream().collect(Collectors.groupingBy(Function.identity(),
               Collectors.summingInt(e->1)));
       Set<String> result = new HashSet<>();
       int wordSize = words.get(0).length();
       int numWords = words.size();
       for(int i=0;i+numWords*wordSize<=s.length();i++){
           if(matchAllWordsInDictionary(s,dictionary,i,wordSize,numWords)){
               result.add(s.substring(i,i+numWords*wordSize));
           }
       }
       return result;
    }

    private static boolean matchAllWordsInDictionary(String s, Map<String, Integer> dictionary, int start, int wordSize, int numWords) {
        Map<String,Integer> curr_dict = new HashMap<>();
        for(int i=0;i<numWords;i++){
            String curr_word = s.substring(start+i*wordSize,start+(i+1)*wordSize);
            Integer count = dictionary.get(curr_word);
            if(count ==null){
                return false;
            }
            curr_dict.put(curr_word,curr_dict.getOrDefault(curr_word,0)+1);
            if(curr_dict.get(curr_word)>count){
                return false;
            }
        }
        return true;
    }
}
