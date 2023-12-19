package epp.sorting.revision;

import epp.hashmap.MapUtils;

import java.util.*;

import static epp.hashmap.MapUtils.getFrequencies;

public class PrintFrequencyInAlphabeticOrder {
    public static void main(String[] args) {
        String s = "bbcdabcdesdd";

        List<Pair> frequencies = getFrequencies(s);
        System.out.println(frequencies);

    }

    private static List<Pair> getFrequencies(String s) {
        List<Pair> result = new ArrayList<>();
        TreeMap<Character, Integer> frequencyMap = new TreeMap<>( MapUtils.getFrequencyMap(s));
        for (Character c : frequencyMap.keySet()) {
            result.add(new Pair(c,frequencyMap.get(c)));
        }
        return result;
    }


    private static class Pair{
        char ch;
        int frequency;

        public Pair(char ch, int frequency) {
            this.ch = ch;
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ",   "(", ")")
                    .add(""+ch)
                    .add("" + frequency)
                    .toString();
        }
    }
}
