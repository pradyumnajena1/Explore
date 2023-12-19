package epp.hashmap.revision;

import epp.hashmap.MapUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PartitionIntoAnagramGroups {
    public static void main(String[] args) {
        String[] values = { "eleven plus two","twelve plus one","god","dog" };
        Set<Set<String>> partition = partitionIntoAnagramGroups(values);
        System.out.println(partition);
    }

    public static Set<Set<String>> partitionIntoAnagramGroups(String[] values) {
        Map<Map<Character,Integer>,Set<String>> groups = new HashMap<>();
        for(String value:values){
            Map<Character,Integer> charFrequency = MapUtils.getCharFrequency(value);
            Set<String> set = groups.getOrDefault(charFrequency, new HashSet<>());
            set.add(value);
            groups.put(charFrequency,set);
        }
        return new HashSet<>(groups.values());
    }


}
