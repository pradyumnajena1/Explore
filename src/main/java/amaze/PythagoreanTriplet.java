package amaze;

import epp.array.ArrayUtils;

import java.util.Arrays;

/**
 * Given an array of integers, write a function that returns true if there is a triplet (a, b, c) that satisfies a2 + b2 = c2.
 */
public class PythagoreanTriplet {
    public static void main(String[] args) {
        int[] values = new int[]{1, 2, 15, 9, 15, 11, 3, 17, 4, 8};
        ArrayUtils.printArray( values);
        ArrayUtils.printArray(findPythogoreanTriplet(values));
        values = new int[]{1, 2, 15, 9, 15, 11, 3, 17, 4, 8};
        ArrayUtils.printArray( values);
        ArrayUtils.printArray(findPythogoreanTriplet2(values));
    }

    /**
     * complexity n2logn
     * @param values
     * @return
     */
    private static int[] findPythogoreanTriplet(int[] values) {
        for(int i=0;i< values.length;i++){
            values[i] = values[i]*values[i];
        }
        Arrays.sort(values);
        for(int i=0;i< values.length;i++){
            for(int j=i+1;j<values.length;j++){
                int index = Arrays.binarySearch(values, values[i] + values[j]);
                if(index >=0){
                    return new int[]{(int) Math.sqrt(values[i]),(int) Math.sqrt(values[j]),
                            (int) Math.sqrt(values[index])};
                }
            }
        }
        return null;
    }
    /**
     * complexity n^2
     * @param values
     * @return
     */
    private static int[] findPythogoreanTriplet2(int[] values) {
        for(int i=0;i< values.length;i++){
            values[i] = values[i]*values[i];
        }
        Arrays.sort(values);
        for(int i=0;i< values.length;i++){
          int[] pair =   findTwoSum(values,values[i],0,i-1);
          if(pair!=null){
              return new int[]{(int) Math.sqrt( pair[0]),(int) Math.sqrt( pair[1]),(int) Math.sqrt( values[i])};
          }
        }
        return null;
    }

    private static int[] findTwoSum(int[] values, int value, int left, int right) {
        while (left<right){
            int sum = values[left] + values[right];
            if(sum ==value){
                return new int[]{ values[ left],values[right]};
            } else if (sum<value) {
                left++;
            }else{
                right--;
            }
        }
        return null;
    }
}
