package cph;

import epp.Pair;
import epp.Triplet;
import epp.array.ArrayUtils;

public class ThreeSumProblem {
    public static void main(String[] args) {
        int[] values = new int[]{8, 1, 15, 2, 19, 7, 16, 16, 18, 8};
        ArrayUtils.printArray(values);
        System.out.println(find3Sum(values, 11));
    }

    private static Triplet<Integer,Integer,Integer> find3Sum(int[] values, int sum) {
        for(int i=0;i< values.length;i++){
            Pair<Integer, Integer> pair = TwoSumProblem.find2SumHelper(values, sum - values[i], 0, i - 1);
            if(pair!=null){
                return new Triplet<>(pair.getFirst(), pair.getSecond(), values[i]);
            }
        }
        return null;
    }
}
