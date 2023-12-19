package leetcode.hard;

import epp.array.ArrayUtils;

public class PartitionArray {
    public static void main(String[] args) {
        int[] arr = new int[]{4,3,2,1,4,3,2,1};
        partition(arr,2);
        ArrayUtils.printArray(arr);
    }

    private static void partition(int[] arr, int pivot) {
       int equal =  partitionRange(arr, pivot,0,arr.length-1);
        partitionRange(arr, Math.min( arr[equal],arr[arr.length-1]),equal,arr.length-1);
    }

    private static int partitionRange(int[] arr, int pivot, int start, int end) {
        int smaller =start; // [start smaller-1]
        int equal =start;// [smaller,equal-1]
        int larger = end;//[larger+1,end]
        while (equal<=larger){
            int incoming = arr[equal];
            if(incoming< pivot){
              ArrayUtils. swap(arr,smaller++,equal++);
            }else if(incoming== pivot){
                equal++;
            }else{
              ArrayUtils.  swap(arr,equal,larger--);
            }
        }
        return equal;
    }


}
