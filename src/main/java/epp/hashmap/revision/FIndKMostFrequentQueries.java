package epp.hashmap.revision;

import epp.array.ArrayUtils;
import epp.binarySearch.revision.FindTheKthElement;
import epp.hashmap.MapUtils;

import java.util.*;
import java.util.stream.Collectors;

public class FIndKMostFrequentQueries {
    public static void main(String[] args) {
        List<String> queries = List.of("hello", "world", "hello", "hello", "hello", "world", "programming", "java", "java", "cpp", "cpp", "programming", "java");
        List<String> frequentQueries = findMostFrequentItems(queries, 3);
        System.out.println(frequentQueries);
    }

    private static List<String> findMostFrequentItems(List<String> queries, int k) {
        Map<String, Long> frequencies = MapUtils.getFrequencies(queries);
        Comparator<Map.Entry<String, Integer>> entryComparator = (Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) -> Integer.compare(b.getValue(), a.getValue());
        List<Map.Entry<String, Integer>> entries = new ArrayList(frequencies.entrySet());
        FindTheKthElement.findKthItem(entries, k, entryComparator);
        System.out.println(entries);
        return entries.subList(0, k).stream().map(a -> a.getKey()).collect(Collectors.toList());

    }
}
