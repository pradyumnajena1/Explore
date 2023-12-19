package epp.array;

import java.util.Arrays;

public class RandomPermutation {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5};
        ArrayUtils.shuffle(array);
        System.out.println(Arrays.toString(array));
    }

}
