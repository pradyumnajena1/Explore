package epp.binarySearch;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class FindKthItemInArray {
    public static void main(String[] args) {
        int[] array = ArrayUtils.randomArray(20,1,20);
        System.out.println(Arrays.toString(array));
        int value = findKthItem(array,5);
        System.out.println(value);
    }

    private static int findKthItem(int[] array, int k) {
        return findKthItem(array,k,0,array.length-1);
    }

    private static int findKthItem(int[] array, int k, int start, int end) {


         int partition =   partition(array,start,end);

         if(partition-start==k-1){
             return array[partition];
         }else if(partition-start > k-1){
            return findKthItem(array,k,start,partition-1);
         }else{
            return findKthItem(array, k-partition+start-1,partition+1,end);
         }


    }

    private static int partition(int[] array, int start, int end) {
        int readPosition  = start;
        int writePosition = start;
        int pivot = array[end];
        while (readPosition<=end){
            if(array[readPosition]<=pivot){
                ArrayUtils.swap(array,readPosition,writePosition++);
            }
            readPosition++;
        }
        return writePosition-1;
    }
}
