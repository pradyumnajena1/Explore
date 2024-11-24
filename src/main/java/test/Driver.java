package test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Driver {
    public static void main(String[] args){
    System.out.println(countDistinctChars("aaaAA"));
    System.out.println(countDistinctChars("aaaAA",false));
    System.out.println(countDistinctChars("aaa@A@AA",false,(Character ch)->!Set.of('#','@').contains(ch)));
        Map<Character, Integer> topCharFrequencies = getTopCharFrequencies("AAAaaaAAAdd", 3);
        String formattedResult = getFormattedResult(topCharFrequencies);
        System.out.println(formattedResult);


        topCharFrequencies = getTopCharFrequencies("aBaBbeC", 3);
        formattedResult = getFormattedResult(topCharFrequencies);
        System.out.println(formattedResult);

    topCharFrequencies = getTopCharFrequencies("a\uD800\uDC00\uD800\uDC00", 3);
        formattedResult = getFormattedResult(topCharFrequencies);
        System.out.println(formattedResult);
    }

    private static String getFormattedResult(Map<Character, Integer> topCharFrequencies) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Map.Entry<Character, Integer> entry: topCharFrequencies.entrySet()){
            String formatted = "("+entry.getKey()+"|"+entry.getValue()+")";
            sb.append(formatted);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }

    public static int countDistinctChars(String s ){

        return countDistinctChars(s,true) ;
    }

    public static int countDistinctChars(String s ,boolean caseSensitive){
        if(s==null || s.length()<1){
            return 0;
        }
        Set<Character> uniqueChars = new HashSet<>();
        for(int i=0;i<s.length();i++){

            char ch = caseSensitive?s.charAt(i):Character.toUpperCase(s.charAt(i));

            uniqueChars.add(ch);
        }
        return uniqueChars.size();
    }

    public static int countDistinctChars(String s , boolean caseSensitive, Predicate<Character> filter){
        if(s==null || s.length()<1){
            return 0;
        }
        Set<Character> uniqueChars = new HashSet<>();
        for(int i=0;i<s.length();i++){
            char ch =  s.charAt(i);
            if(filter.test(ch)){
                  ch = caseSensitive?ch:Character.toUpperCase(ch);
                  uniqueChars.add(ch);
            }

        }
        return uniqueChars.size();
    }


    public static Map<Character,Integer> getTopCharFrequencies(String s, int top){
        Map<Character,Integer> frequenciesMap = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            int freq = frequenciesMap.getOrDefault(ch,0)+1;
            frequenciesMap.put(ch,freq);
        }
        Comparator<Map.Entry<Character,Integer>> freqComparator = Comparator.comparingInt(x->x.getValue());
        freqComparator = freqComparator.reversed();
        List<Map.Entry<Character, Integer>> sorted = frequenciesMap.entrySet().stream().sorted(freqComparator).collect(Collectors.toList());
        Map<Character,Integer> result = new LinkedHashMap<>();
    for (int i = 0; i < top && i < sorted.size(); i++) {
            result.put(sorted.get(i).getKey(),sorted.get(i).getValue());
        }
        return result;
    }


}
