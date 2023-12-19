package epp.array.revision;

import epp.array.ArrayUtils;

public class SortSquared {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomSortedArray(20,-10,10);
        ArrayUtils.printArray(values);
        sortSquared(values);
    }

    private static void sortSquared(int[] values) {
         int[] result = new int[values.length];
         int left=0;
         int right=values.length-1;
         int writeIndex = result.length-1;
         while (left<=right){
             if(Math.abs(values[left])>=Math.abs(values[right])){
                 result[writeIndex--] = values[left];
                 left++;
             }else{
                 result[writeIndex--] = values[right];
                 right--;
             }
         }
         ArrayUtils.printArray(result);
    }
}
