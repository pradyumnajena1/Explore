package epp.dp.revision;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordBreaking {
    public static void main(String[] args) {
        Set<String> dictionary = new HashSet<>();
        dictionary.add("bed");
        dictionary.add("and");
        dictionary.add("bath");
        dictionary.add("beyond");
        dictionary.add("be");
        String s = "bedbathandbeyond";
        Set<String> splits = breakWordIntoDictionaryWords(s,dictionary);
        System.out.println(splits);

    }

    private static Set<String> breakWordIntoDictionaryWords(String s, Set<String> dictionary) {
        return breakWordIntoDictionaryWords(s,0,dictionary);
    }

    private static Set<String> breakWordIntoDictionaryWords(String s, int index, Set<String> dictionary) {
        Map<Integer,Set<String>> cache = new HashMap<>();
        Set<String> result = breakWordIntoDictionaryWordsHelper(s, index, dictionary, cache);
        System.out.println(cache);
        return result;
    }

    private static Set<String> breakWordIntoDictionaryWordsHelper(String s, int index, Set<String> dictionary, Map<Integer, Set<String>> cache) {
        if(index==s.length()){
            return new HashSet<>();
        }
        if(cache.containsKey(index)){
            return cache.get(index);
        }
        for(int i=index;i<s.length();i++){
            String prefix = s.substring(index, i + 1);
            Set<String> partial = breakWordIntoDictionaryWordsHelper(s, i + 1, dictionary,
                    cache);
            if( dictionary.contains(prefix) && partial !=null){
                HashSet<String> result = new HashSet<>(partial);
                result.add(prefix);
                cache.put(index,result);
            }
        }
        return cache.get(index);
    }
}
