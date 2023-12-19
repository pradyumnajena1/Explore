package epp.dp.revision;

import epp.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeasuringWithFaultyJugs {
    public static void main(String[] args) {
        Pair<Integer,Integer> required = new Pair<>(2100,2300);
        Pair<Integer,Integer>[] jugs = new Pair[]{new Pair(230,240),new Pair(290,310),new Pair(500,515)};
        Map<Pair<Integer,Integer>,List<Pair<Integer,Integer>>> cache = new HashMap<>();
        List<Pair<Integer,Integer>> sequence = canMeasure(jugs,required,cache);
        System.out.println(sequence);
    }

    private static List<Pair<Integer, Integer>> canMeasure(Pair<Integer, Integer>[] jugs, Pair<Integer, Integer> required, Map<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> cache) {
       for(int i=0;i<jugs.length;i++){
           if(contains(required,jugs[i]) ){
               ArrayList<Pair<Integer, Integer>> sequence = new ArrayList<>();
               sequence.add(jugs[i]);
               return sequence;
           }
       }
       if(cache.containsKey(required)){
           return cache.get(required);
       }
       for(int i=0;i< jugs.length;i++){
           Pair<Integer, Integer> newRequired = new Pair<>(required.getFirst() - jugs[i].getFirst(),
                   required.getSecond() - jugs[i].getSecond());

           List<Pair<Integer, Integer>> partialResult = canMeasure(jugs, newRequired, cache);
           if(partialResult!=null){
               List<Pair<Integer, Integer>> newResult = new ArrayList<>();
               newResult.add(jugs[i]);
               newResult.addAll(partialResult);
               cache.put(required,newResult);
               break;
           }
       }

        return cache.get(required);
    }

    private static boolean contains(Pair<Integer, Integer> required, Pair<Integer, Integer> jug) {
        return required.getFirst()<=jug.getFirst()  && required.getSecond()>=jug.getSecond() ;
    }
}
