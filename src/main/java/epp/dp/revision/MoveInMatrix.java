package epp.dp.revision;

import epp.Pair;

import java.util.HashMap;
import java.util.Map;

public class MoveInMatrix {
    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int numWays =   getNumWays(m,n, new Pair<>(0,0),new Pair<>(m-1,n-1));
        System.out.println(numWays);
    }

    private static int getNumWays(int m, int n, Pair<Integer, Integer> source, Pair<Integer, Integer> destination) {
        Map<Pair<Integer,Integer>,Integer> cache = new HashMap<>();
       return getNumWaysRecurse(m,n,source,destination,cache);
    }

    private static int getNumWaysRecurse(int m, int n, Pair<Integer, Integer> source, Pair<Integer, Integer> destination, Map<Pair<Integer, Integer>, Integer> cache) {
        if(source.equals(destination)){
            return 1;
        }
        if(cache.containsKey(source)){
            return cache.get(source);
        }
        int numWays = 0;
        if(source.getFirst()<m-1){
            numWays +=   getNumWays(m,n,new Pair<>(source.getFirst()+1, source.getSecond() ),destination);
        }
        if(source.getSecond()<n-1){
            numWays +=   getNumWays(m,n,new Pair<>(source.getFirst(), source.getSecond()+1 ),destination);
        }
        cache.put(source,numWays);
        return numWays;
    }
}
