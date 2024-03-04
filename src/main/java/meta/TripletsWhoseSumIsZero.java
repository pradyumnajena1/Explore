package meta;

import cph.TwoSumProblem;
import epp.Pair;
import epp.Triplet;
import epp.array.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class TripletsWhoseSumIsZero {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10,-10,10);
        Triplet<Integer,Integer,Integer> result = findTripletsWithSum(values,0);
        System.out.println(result);
    }

    private static Triplet<Integer, Integer, Integer> findTripletsWithSum(int[] values, int sum) {
        for(int i=0;i< values.length;i++){
            Pair<Integer, Integer> pair =  find2SumHelper(values, sum - values[i],0, i - 1
                     );
            if(pair!=null){
                return new Triplet<>(pair.getFirst(), pair.getSecond(), values[i]);
            }
        }
        return null;
    }
    public static Pair<Integer, Integer> find2SumHelper(int[] values, int sum, int start, int end) {
        if(start<0 || start>= values.length || end<0||end>= values.length){
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = start; i <= end; i++) {
            Integer index = indexMap.get(sum - values[i]);
            if (index != null) {
                return new Pair<>(values[index], values[i]);
            }
            indexMap.put(values[i], i);
        }
        return null;
    }
}
