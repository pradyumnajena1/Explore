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
        Integer result = null;
        int start = 0;
        int end = values.length - 1;

        while (start <= end) {
            if (values[start] <= values[end]) {
                result = values[start];
                break;
            } else {
                int mid = start + (end - start) / 2;
                if (values[mid] < values[mid - 1] && values[mid] < values[mid + 1]) {
                    result = values[mid];
                    break;
                } else if (values[mid] < values[start] && values[mid] < values[end]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

        }
        return result;
    }
}
