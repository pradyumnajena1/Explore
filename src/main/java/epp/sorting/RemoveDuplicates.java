package epp.sorting;

import epp.array.ArrayUtils;

public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] array = ArrayUtils.randomSortedArray(20,1,10);
        ArrayUtils.printArray(array);
        int i = removeDuplicates(array);
        ArrayUtils.printArray(array);
        System.out.println(i);
    }

    private static int removeDuplicates(int[] array) {
        int writePosition = 1;
        int readPosition = 1;
        while (readPosition<array.length){

            if(array[readPosition]>array[writePosition-1]){

                array[writePosition] = array[readPosition];
                writePosition++;
            }
            readPosition++;
        }
        return writePosition-1;
    }
}
