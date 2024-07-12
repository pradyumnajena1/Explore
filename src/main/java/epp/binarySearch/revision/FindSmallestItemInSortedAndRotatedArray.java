package epp.binarySearch.revision;

import epp.array.ArrayUtils;

public class FindSmallestItemInSortedAndRotatedArray {
    public static void main(String[] args) {
        int[] values =  new int[]{1,2,3,4,5,6,7,8,9,10};
       for(int i=1;i<=10;i++){
           ArrayUtils.rotateArray(values, 1);
           ArrayUtils.printArray(values);
           int value = findSmallestItemInSortedAndRotatedArray(values);
           System.out.println(value);
       }

    }

    public static int findSmallestItemInSortedAndRotatedArray(int[] values) {
        int left = 0;
        int right = values.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (values[mid] > values[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
