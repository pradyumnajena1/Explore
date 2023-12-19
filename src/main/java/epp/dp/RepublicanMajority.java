package epp.dp;

import epp.array.ArrayUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RepublicanMajority {
    public static void main(String[] args) {
        double[] probabilities = ArrayUtils.randomArray(10,0d,1d);
        ArrayUtils.printArray(probabilities);

        double republicWin = getRepublicanWin(probabilities);
        System.out.println(republicWin);

    }
    private static class Key{
        int start;
        int end;
        int winReqd;

        public Key(int start, int end, int winReqd) {
            this.start = start;
            this.end = end;
            this.winReqd = winReqd;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return start == key.start && end == key.end && winReqd == key.winReqd;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end, winReqd);
        }
    }

    private static double getRepublicanWin(double[] probabilities) {
        Map<Key,Double> cache = new HashMap<>();
        return getRepublicanWin(probabilities,0,probabilities.length-1,probabilities.length/2+1,cache);
    }

    private static double getRepublicanWin(double[] probabilities, int start, int end,int winReqd,Map<Key,Double> cache) {

        if(winReqd>end-start+1){
            return 0;
        }
        if(winReqd==0 && start>end){
            return 1;
        }else if(winReqd<0){
            return 0;
        }
        Key key = new Key(start, end, winReqd);
        if(cache.containsKey(key)){
            return cache.get(key);
        }

        double probability =
                probabilities[start] * getRepublicanWin(probabilities, start + 1, end, winReqd - 1,cache) + (1 - probabilities[start]) * getRepublicanWin(probabilities, start + 1, end, winReqd,cache);
        cache.put(key,probability);
        return probability;

    }
}
