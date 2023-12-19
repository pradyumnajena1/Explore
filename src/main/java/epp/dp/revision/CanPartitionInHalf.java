package epp.dp.revision;

import epp.Triplet;
import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CanPartitionInHalf {
    public static void main(String[] args) {
        boolean canPartition = false;
        do {
            int[] values = ArrayUtils.randomArray(20,10,50);
            ArrayUtils.printArray(values);
              canPartition =  canPartitionInHalf(values);
            System.out.println(canPartition);
        }while (!canPartition);

    }

    private static boolean canPartitionInHalf(int[] values) {
        int sum = Arrays.stream(values).sum();
        System.out.println(sum);
        if(sum%2==1){
            return false;
        }
        Map<Triplet<Integer,Integer,Integer>,Boolean> cache = new HashMap<>();
        return canPartitionInHalf(values,0,sum/2,sum/2,cache);
    }

    private static boolean canPartitionInHalf(int[] values, int index, int left, int right, Map<Triplet<Integer, Integer, Integer>, Boolean> cache) {
        if(index+2==values.length){
            return values[index]==left&&values[index+1]==right || values[index]==right&&values[index+1]==left;
        }
        Triplet<Integer, Integer, Integer> key = new Triplet<>(index, left, right);
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        Boolean result = false;
        if(canPartitionInHalf(values,index+1,left-values[index],right, cache) ||
                canPartitionInHalf(values,index+1,left,right-values[index], cache)){

            result =  true;
        }
        cache.put(key,result);
        return cache.get(key);
    }

}
