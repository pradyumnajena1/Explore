package guidetocompetitiveprogramming;

import epp.Pair;

import java.util.HashMap;
import java.util.Map;

public class EditDistance {
    public static void main(String[] args) {
        int editDistance = editDistance("love","movie");
        System.out.println(editDistance);
    }

    private static int editDistance(String a, String b) {
        Map<Pair<Integer,Integer>,Integer> cache = new HashMap<>();
        return editDistance(a,b,a.length(),b.length(),cache);
    }

    private static int editDistance(String source, String target, int i, int j, Map<Pair<Integer, Integer>, Integer> cache) {
        if(i==0 ){
            return j;
        }
        if(j==0){
            return i;
        }
        Pair<Integer, Integer> key = new Pair<>(i, j);
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        int editDistance;
        if(source.charAt(i-1)==target.charAt(j-1)){
            editDistance = editDistance(source,target,i-1,j-1,cache);
        }else{
            editDistance = Integer.MAX_VALUE;
            //update
            editDistance = Math.min(editDistance,editDistance(source,target,i-1,j-1,cache)+1);
            //delete
            editDistance = Math.min(editDistance,editDistance(source,target,i,j-1,cache)+1);
            //insert
            editDistance = Math.min(editDistance,editDistance(source,target,i-1,j,cache)+1);
        }
        cache.put(key,editDistance);

        return editDistance;
    }
}
