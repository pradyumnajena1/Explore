package epp.array;

import java.util.Arrays;

public class RandomSampling {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5};
        randomSample(arr,3);
        System.out.println(Arrays.toString(arr));
    }

    private static void randomSample(int[] arr, int numElements) {
        if(numElements>arr.length){
            throw new IllegalArgumentException("Invalid numElements"+numElements);
        }
        for(int i=0;i<numElements;i++){
         int selectedIndex = i+   (int) (Math.random() * (arr.length-i));
         ArrayUtils.swap(arr,selectedIndex,i);

        }
    }
}
