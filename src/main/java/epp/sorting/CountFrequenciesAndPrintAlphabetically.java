package epp.sorting;

import epp.hashmap.MapUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountFrequenciesAndPrintAlphabetically {
    public static void main(String[] args) {
        String s = "bcdacebe";
        PrintAlphabetically(s);
    }

    private static void PrintAlphabetically(String s) {
        Map<Character, Integer> frequencyMap = MapUtils.getFrequencyMap(s);
        List<Tuple> list = frequencyMap.entrySet().stream().map(x -> new Tuple(x.getKey(), x.getValue())).collect(Collectors.toList());
        Collections.sort(list);
        System.out.println(list);

    }
    private static class Tuple implements Comparable<Tuple>{
        char ch;
        int count;

        public Tuple(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }

        @Override
        public int compareTo(Tuple o) {
            return Character.compare(this.ch,o.ch);
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("(");
            sb.append("ch=").append(ch);
            sb.append(", count=").append(count);
            sb.append(')');
            return sb.toString();
        }
    }
}
