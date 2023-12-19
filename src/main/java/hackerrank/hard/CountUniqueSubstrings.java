package hackerrank.hard;

import epp.Pair;

import java.util.*;

public class CountUniqueSubstrings {
    public static void main(String[] args) {
        ArrayList<List<Integer>> queries = new ArrayList<>();
        queries.add(List.of(1,1));
        queries.add(List.of(1,4));
        queries.add(List.of(1,1));
        queries.add(List.of(1,4));
        queries.add(List.of(0,2));
        //System.out.println(getSubstringsEndingWith("abaa", 1, 3));
        System.out.println(countSubstrings("aabaa", queries));
    }

    public static List<Integer> countSubstrings(String s, List<List<Integer>> queries) {
        // Write your code here
        Map<Pair<Integer,Integer>,Set<String>> cache = new HashMap<>();
        return countSubStringsHelper(s, queries,cache);
    }

    private static List<Integer> countSubStringsHelper(String s, List<List<Integer>> queries, Map<Pair<Integer, Integer>, Set<String>> cache) {
        List<Integer> result = new ArrayList<>();
        for (List<Integer> query : queries) {
            Set<String> uniqueSubstrings = getUniqueSubStrings(s, query.get(0), query.get(1),cache);
            result.add(uniqueSubstrings.size());
        }
        return result;
    }

    private static Set<String> getUniqueSubStrings(String s, int i, int j, Map<Pair<Integer, Integer>, Set<String>> cache) {
        if (i > j) {
            HashSet<String> result = new HashSet<>();

            return result;
        }
        if (i == j) {
            HashSet<String> result = new HashSet<>();
            result.add(s.substring(i, i + 1));
            return result;
        }
        Pair<Integer, Integer> key = new Pair<>(i, j);
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        Set<String> result = new HashSet<>();
        Set<String> uniqueSubStrings = getUniqueSubStrings(s, i + 1, j - 1, cache);
        HashSet<String> substringsStaringWith = getSubstringsStaringWith(s, i, j);
        HashSet<String> substringsEndingWith = getSubstringsEndingWith(s, i, j);
        result.addAll(uniqueSubStrings);
        result.addAll(substringsStaringWith);
        result.addAll(substringsEndingWith);
        cache.put(key,result);
        return result;
    }

    private static HashSet<String> getSubstringsEndingWith(String s, int i, int j) {
        HashSet<String> result = new HashSet<>();
        if(i>j){
            return result;
        }
        int start = i;
        while (start <= j) {
            result.add(s.substring(start, j + 1));
            start++;
        }
        return result;
    }

    private static HashSet<String> getSubstringsStaringWith(String s, int i, int j) {
        HashSet<String> result = new HashSet<>();
        if(i>j){
            return result;
        }
        int end = j + 1;
        while (end > i) {
            result.add(s.substring(i, end));
            end--;
        }
        return result;
    }
}
