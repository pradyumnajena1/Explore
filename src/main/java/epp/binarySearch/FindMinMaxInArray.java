package epp.binarySearch;

import epp.MutablePrimitive;
import epp.array.ArrayUtils;

import java.util.Arrays;

public class FindMinMaxInArray {
    public static void main(String[] args) {
        int[] array = ArrayUtils.randomArray(10,10,100);
        System.out.println(Arrays.toString(array));
        MutablePrimitive<Integer> min = new MutablePrimitive<>(Integer.MAX_VALUE);
        MutablePrimitive<Integer> max = new MutablePrimitive<>(Integer.MIN_VALUE);
        findMinMax(array,min,max);
        System.out.println(max.getValue());
        System.out.println(min.getValue());
    }

    private static void findMinMax(int[] array, MutablePrimitive<Integer> min, MutablePrimitive<Integer> max) {
        for(int i=0;i<array.length;i++){
            if(array[i]<min.getValue()){
                min.setValue(array[i]);
            }else if(array[i]>max.getValue()){
                max.setValue(array[i]);
            }
        }
    }

    private static void findMinMax2(int[] array,MutablePrimitive<Integer> min, MutablePrimitive<Integer> max){

    }
}
