package epp.dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DetermineCriticalHeight {
    public static void main(String[] args) {
        int x = getCriticalHeight(2,10);
        System.out.println(x);
    }

    private static int getCriticalHeight(int cases, int drops) {
        Map<Pair,Integer> cache = new HashMap<>();
        return getCriticalHeight2(cases, drops,cache);

    }
    private static class Pair{
        int cases;
        int drops;

        public Pair(int cases, int drops) {

            this.cases = cases;
            this.drops = drops;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return cases == pair.cases && drops == pair.drops;
        }

        @Override
        public int hashCode() {
            return Objects.hash(cases, drops);
        }
    }


    private static int getCriticalHeight2(int cases, int drops, Map<Pair, Integer> cache) {
        if(cases ==0 || drops==0){
            return 0;
        }
        if(cases ==1){
            return   drops;
        }
        Pair key = new Pair(cases, drops);
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        int value = getCriticalHeight2(cases - 1, drops - 1, cache) + 1 + getCriticalHeight2(cases, drops - 1, cache);
        cache.put(key,value);
        return value;
    }
}
