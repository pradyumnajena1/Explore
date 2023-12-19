package hackerrank.easy.dp;

import epp.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CostFunction {
    public static void main(String[] args) {
        ArrayList<Integer> listB = new ArrayList<>();
        listB.add(100);
        listB.add(2);
        listB.add(100);
        listB.add(2);
        listB.add(100);
        int cost = cost(listB);
        System.out.println(cost);
    }
    public static int cost(List<Integer> listB) {
       Map<String,Pair<Integer,Integer>> cache = new HashMap<>();
        return cost(listB,0,0,cache).getFirst();

    }

    private static Pair<Integer,Integer> cost(List<Integer> listB, int index, int previousChosen, Map<String,
            Pair<Integer,Integer>> cache) {

        if(index==listB.size()-1){
            Pair<Integer,Integer> max;
            if(Math.abs(previousChosen - listB.get(index)) > Math.abs(previousChosen - 1) ){
                max = new Pair<>(Math.abs(previousChosen - listB.get(index)),listB.get(index));
            }else{
                max = new Pair<>(Math.abs(previousChosen - 1),1);
            }

            return max;
        }
        String key = previousChosen + "_" + index;
        if(cache.containsKey(key)){
            return cache.get(key);
        }

        Pair<Integer,Integer> max = null;
        for(int i=1;i<=listB.get(index);i+= listB.get(index)-1){
            Pair<Integer, Integer> cost = cost(listB, index + 1, i, cache);
            int newCost = (index>0?  Math.abs(previousChosen-i):0) +cost.getFirst();
            if(max==null||max.getFirst()<newCost){
                max = new Pair<>(newCost,i);
            }

        }
        //System.out.println( " index = " + index + ", previousChosen = " + previousChosen+ " "+max.toString());
        //System.out.println(previousChosen + " " + max.toString());
        cache.put(key,max);

        return max;
    }


}
