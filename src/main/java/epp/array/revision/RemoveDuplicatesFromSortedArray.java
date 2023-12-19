package epp.array.revision;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        int[] arr = ArrayUtils.randomArray(10,3,8);
        Arrays.sort(arr);
        ArrayUtils.printArray(arr);
        int numElements =   removeDuplicatesFromSortedArray(arr);
        System.out.println(numElements);
        ArrayUtils.printArray(arr);
    }

    private static int removeDuplicatesFromSortedArray(int[] arr) {
        int writeIndex = 1;
        for(int i=1;i<arr.length;i++){
            if(arr[i]!=arr[writeIndex-1]){
                arr[writeIndex]=arr[i];
                writeIndex++;
            }
        }
        return writeIndex;
    }
}
