package meta;

import java.util.HashMap;
import java.util.Map;

public class DistinctPairsWithDIffEqualsK {
    public static void main(String[] args) {
        System.out.println(distinctPairsWithDiffCount(new int[]{1, 5, 3, 4, 2},3));
        System.out.println(distinctPairsWithDiffCount(new int[]{8, 12, 16, 4, 0, 20},4));
    }

    private static int distinctPairsWithDiffCount(int[] values, int k) {
        Map<Integer,Integer> positionMap = new HashMap<>();
        for(int i=0;i<values.length;i++){
            positionMap.put(values[i],i);
        }
        int count = 0;
        for(int i=0;i<values.length;i++){
            if(positionMap.containsKey(values[i]+k)){
                count++;
            }
            if(positionMap.containsKey(values[i]-k)){
                count++;
            }
            positionMap.remove(values[i]);
        }

        return count;
    }
}
