package epp.dp.revision;

import epp.Triplet;

import java.util.HashMap;
import java.util.Map;

public class CheckStringInterleaving {
    public static void main(String[] args) {
        String s1 = "gtaa";
        String s2 = "atca";
        String s = "gattacaa";
        boolean interleaving = isInterleaving(s1, s2, s);
        System.out.println(interleaving);
    }

    private static boolean isInterleaving(String s1, String s2, String s) {
        Map<Triplet<Integer,Integer,Integer>,Boolean> cache = new HashMap<>();
        return isInterleaving(s1.toCharArray(), 0, s2.toCharArray(), 0, s.toCharArray(), 0,cache);
    }

    private static boolean isInterleaving(char[] s1, int s1Start, char[] s2, int s2Start, char[] s, int sStart, Map<Triplet<Integer, Integer, Integer>, Boolean> cache) {
        if (s1Start == s1.length && s2Start == s2.length && sStart == s.length) {
            return true;
        } else if (s1Start == s1.length && s2Start == s2.length) {
            return false;
        } else if (s1Start == s1.length) {
            return new String(s, sStart, s.length - sStart).equals(new String(s2, s2Start, s2.length - s2Start));
        } else if (s2Start == s2.length) {
            return new String(s, sStart, s.length - sStart).equals(new String(s1, s1Start, s1.length - s1Start));
        }
        Triplet<Integer, Integer, Integer> key = new Triplet<>(sStart, s1Start, s2Start);
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        Boolean result = false;
        if (s[sStart] == s1[s1Start] && s[sStart] == s2[s2Start]) {
            result = isInterleaving(s1, s1Start + 1, s2, s2Start, s, sStart + 1, cache) || isInterleaving(s1, s1Start, s2, s2Start + 1, s, sStart + 1, cache);
        } else if (s[sStart] == s1[s1Start]) {
            result = isInterleaving(s1, s1Start + 1, s2, s2Start, s, sStart + 1, cache);
        } else if (s[sStart] == s2[s2Start]) {
            result = isInterleaving(s1, s1Start, s2, s2Start + 1, s, sStart + 1, cache);
        }
        cache.put(key,result);
        return result;
    }
}
