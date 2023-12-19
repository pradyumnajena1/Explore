package epp.sorting.revision;

import epp.array.ArrayUtils;

public class MergeSortInPlace {
    public static void main(String[] args) {
        int[] array1 = ArrayUtils.randomSortedArray(10, 1, 30);
        int[] array2 = ArrayUtils.randomSortedArray(10, 1, 30);
        int length1 = array1.length;
        int[] newArray = new int[length1 +array2.length+10];
        System.arraycopy(array1,0,newArray,0, length1);
        array1 = newArray;
        ArrayUtils.printArray(array1);
        ArrayUtils.printArray(array2);
        mergeSortInPlace(array1,length1,array2);
        ArrayUtils.printArray(array1);
    }

    private static void mergeSortInPlace(int[] bigger, int biggerLength, int[] smaller) {
        if(biggerLength+smaller.length > bigger.length){
            throw new IllegalArgumentException("Not enough space in the bigger array");
        }
        int writePosition = biggerLength + smaller.length-1;
        int biggerReadPosition = biggerLength-1;
        int smallerReadPosition = smaller.length-1;

        while (biggerReadPosition>=0 && smallerReadPosition>=0){

            if(bigger[biggerReadPosition]>=smaller[smallerReadPosition]){
                bigger[writePosition--] = bigger[biggerReadPosition--];

            }else{
                bigger[writePosition--] = smaller[smallerReadPosition--];
            }
        }
        while (biggerReadPosition>=0  ){
            bigger[writePosition--] = bigger[biggerReadPosition--];
        }
        while (smallerReadPosition>=0  ){
            bigger[writePosition--] = smaller[smallerReadPosition--];
        }

    }
}
