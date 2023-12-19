package epp.sorting.revision;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(20, 1, 30);
        ArrayUtils.printArray(values);
        values =   removeDuplicates(values);
        ArrayUtils.printArray(values);

        values = ArrayUtils.randomArray(20, 1, 30);
        ArrayUtils.printArray(values);
        values =   removeDuplicates2(values);
        ArrayUtils.printArray(values);
    }

    /**
     * no extra space time complexity O(nlogn)
     * @param values
     * @return
     */
    private static int[] removeDuplicates(int[] values) {
        Arrays.sort(values);
        int writePos = 1;
        for(int i=1;i< values.length;i++){
            if(values[i]!=values[writePos-1]){
                values[writePos++] = values[i];
            }
        }
        int[] result = new int[writePos];
        System.arraycopy(values,0,result,0,writePos);
        return result;
    }

    /**
     * O(n) extra space, time complexity O(n)
     * @param values
     * @return
     */
    private static int[] removeDuplicates2(int[] values) {
        Set<Integer> uniques = new HashSet<>();
        int writePos = 0;
         for(int i=0;i< values.length;i++){
             if(!uniques.contains(values[i])){
                 values[writePos++] = values[i];
                 uniques.add(values[i]);
             }
         }
         int[] result = new int[writePos];
         System.arraycopy(values,0,result,0,writePos);
         return result;
    }
}
