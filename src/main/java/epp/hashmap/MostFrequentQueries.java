package epp.hashmap;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MostFrequentQueries {
    public static void main(String[] args) {
        List<String> queries = List.of("hello","world","hello","sachin","virat","sachin","modi","sachin","modi");
        List<Tuple> mostFrequentQueries = getMostFrequentQueries(queries,3);
        System.out.println(mostFrequentQueries);
    }

    private static List<Tuple> getMostFrequentQueries(List<String> queries, int k) {
        Map<String, Integer> frequencyMap = MapUtils.getFrequencyMap(queries.toArray(new String[0]));
        Tuple[] tuples = new Tuple[frequencyMap.size()];
        int i=0;
        for(Map.Entry<String,Integer> entry:frequencyMap.entrySet()){
            tuples[i++] = new Tuple(entry.getKey(),entry.getValue());
        }
        ArrayUtils.findNthItem(tuples,tuples.length- k);
        Tuple[] top = new Tuple[k];
        System.arraycopy(tuples,tuples.length- k,top,0,k);
        return Arrays.asList(top);
    }

    private static class Tuple implements Comparable<Tuple>{
        String query;
        int frequency;

        public Tuple(String query, int frequency) {
            this.query = query;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Tuple o) {
            return Integer.compare(this.frequency, o.frequency);
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Tuple{");
            sb.append("query='").append(query).append('\'');
            sb.append(", frequency=").append(frequency);
            sb.append('}');
            return sb.toString();
        }
    }
}
