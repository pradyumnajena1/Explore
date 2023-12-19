package hackerrank.easy;

import epp.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestCommonSubSequence {
    public static void main(String[] args) {
       /* System.out.println(longestCommonSubsequence(new ArrayList<>(List.of(1, 2, 3, 4, 1)),
                new ArrayList<>(List.of(3, 4, 1, 2, 1, 3))));*/
        System.out.println(longestCommonSubsequence(new ArrayList<>(List.of(697,953,900,438,899,593,591,963,31,160,894,493,782,445,326,452,988,657,7,544,768,398,791,650,818,12,347,928,828,336,692,339,130,837,548,487,989,333,875,711,957,341,821,319,338,328,234,7,670,328,451,200,685,699,855,668,609,322,752,386,81,635,952,618,133,73,548,163,221,105,773,398,639,579,660,746,718,918,224,984,265,242,506,762,734,98,324,100,896,346,344,27,420,353,532,105,914,130,695)),
                new ArrayList<>(List.of(438,591,768,160,777,894,782,398,445,306,326,282,452,607,241,513,185,7,544,12,347,828,336,83,924,143,692,339,130,515,837,466,989,875,711,957,338,266,305,480,328,28,7,670,328,699,849,668,609,979,100,322,283,386,655,263,826,169,635,952,618,73,238,897,221,863,34,372,732,398,579,666,545,660,794,746,526,718,918,403,615,946,224,822,242,506,734,324,100,55,346,704,24,650,678,532,914,130,423,998))));
    }

    public static List<Integer> longestCommonSubsequence(List<Integer> a, List<Integer> b) {
        // Write your code here
        Map<Pair<Integer, Integer>, List<Integer>> cache = new HashMap<>();
        return longestCommonSubsequence(a, b, 0, 0, cache);

    }

    private static List<Integer> longestCommonSubsequence(List<Integer> a, List<Integer> b, int i, int j, Map<Pair<Integer, Integer>, List<Integer>> cache) {
        if (i == a.size() || j == b.size()) {
            return new ArrayList<>();
        }
        Pair<Integer, Integer> key = new Pair<>(i, j);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        List<Integer> sol;
        if (a.get(i).intValue() == b.get(j).intValue()) {
            List<Integer> subsol = longestCommonSubsequence(a, b, i + 1, j + 1, cache);
            sol = new ArrayList<>();
            sol.add(a.get(i));
            sol.addAll(subsol);

        } else {
            List<Integer> subsol1 = longestCommonSubsequence(a, b, i + 1, j, cache);
            List<Integer> subsol2 = longestCommonSubsequence(a, b, i, j + 1, cache);
            sol = new ArrayList<>();
            if (subsol1.size() > subsol2.size()) {
                sol.addAll(subsol1);
            } else {
                sol.addAll(subsol2);
            }

        }
        cache.put(key, sol);
        return sol;
    }
}
