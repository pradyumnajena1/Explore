package epp.heap;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class FindKthElementInArray {

    public static void main(String[] args) {
        int[] array = ArrayUtils.randomArray(20,1,50);
        System.out.println(Arrays.toString(array));
        int value  = ArrayUtils.findNthItem(array,5);
        System.out.println(value);
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
    }

}
