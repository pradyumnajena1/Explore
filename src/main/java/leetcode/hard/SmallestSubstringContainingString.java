package leetcode.hard;

import java.util.*;

public class SmallestSubstringContainingString {
    public static void main(String[] args) {
        int length = smallestSubstringContainingString("ADOBECODEBANC","AABC");
        System.out.println(length);
    }

    private static int smallestSubstringContainingString(String string, String substring) {
        Map<Character, List<Integer>> lastPosition = new HashMap<>();
        Map<Character,Integer> freqmap = getFreqMap(substring);
        int minLength = Integer.MAX_VALUE;
        int count=0;
        TreeMap<Integer,Character> treeMap = new TreeMap<>();
        for(int i=0;i<string.length();i++){
            char key = string.charAt(i);
            if(freqmap.containsKey(key) ){
                List<Integer> list = lastPosition.getOrDefault(key,new ArrayList<>());
                list.add(i);
                treeMap.put(i,key);
                count++;
                while (list.size()>freqmap.get(key)){
                    Integer remove = list.remove(0);
                    treeMap.remove(remove);
                    count--;
                }

                lastPosition.put(key,list);

                if(count==substring.length()){
                    System.out.println(freqmap);
                    System.out.println(lastPosition);
                    int min = treeMap.firstKey();
                    int length = i - min +1;
                    minLength = Math.min(length,minLength);
                    System.out.println(i+" "+min+" "+treeMap.firstKey());
                }
            }
        }
        return minLength;
    }

    private static int getMin(Map<Character, List<Integer>> map) {
        int min = Integer.MAX_VALUE;
        for(List<Integer> value: map.values()){
            min = Math.min(min,value.get(0));
        }
        return min;
    }

    private static Map<Character, Integer> getFreqMap(String substring) {
        Map<Character, Integer> result = new HashMap<>();
        for(int i=0;i<substring.length();i++){
            char key = substring.charAt(i);
            result.put(key,result.getOrDefault(key,0)+1);
        }
        return  result ;
    }
}
