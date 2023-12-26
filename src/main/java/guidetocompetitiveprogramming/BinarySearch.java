package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

public class BinarySearch {
    public static void main(String[] args) {
        int[] sortedArray = ArrayUtils.randomSortedArray(10, 1, 10);
        sortedArray=new int[]{3, 4, 5, 5, 6, 6, 6, 7, 7, 8};
        ArrayUtils.printArray(sortedArray);
        System.out.println(binarySearch(sortedArray, sortedArray[4]));
        System.out.println(binarySearch2(sortedArray, sortedArray[4]));
    }

    private static int binarySearch(int[] sortedArray, int value) {
        int low = 0;
        int high = sortedArray.length-1;
        while (low<=high){
            int mid = (low+high)/2;
            if(sortedArray[mid]==value){
                return mid;
            }else if(sortedArray[mid]<value){
                low=mid+1;
            }else{
                high = mid-1;
            }
        }
        return -(low+1);
    }
    private static int binarySearch2(int[] sortedArray, int value){
        int start = 0;
        for(int jump = sortedArray.length/2;jump>=1;jump=jump/2){
            while (start+jump< sortedArray.length && sortedArray[start+jump]<=value){
                start = start+jump;
            }
        }
        if(sortedArray[start]==value){
            return start;
        }
        return -(start+1);
    }
}
