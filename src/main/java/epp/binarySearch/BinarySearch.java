package epp.binarySearch;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = ArrayUtils.randomArray(10,1,10);
        int value = array[7];
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        System.out.println(value);
        int index = ArrayUtils.binarySearch(array,value);
        System.out.println(index);
    }

}
