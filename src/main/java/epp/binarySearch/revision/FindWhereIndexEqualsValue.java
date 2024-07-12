package epp.binarySearch.revision;

import epp.array.ArrayUtils;

public class FindWhereIndexEqualsValue {
    public static void main(String[] args) {
        // Test case 1
        testIndexEqualsValue(new int[]{-3, -1, 0, 3, 5}, 3);

        // Test case 2
        testIndexEqualsValue(new int[]{-10, -5, 1, 3, 6}, 3);

        // Test case 3
        testIndexEqualsValue(new int[]{0, 2, 3, 4, 5}, 0);

        // Test case 4
        testIndexEqualsValue(new int[]{-2, -1, 0, 1, 4}, 4);

        // Test case 5
        testIndexEqualsValue(new int[]{-15, -10, -5, 0, 3, 6, 7, 8, 10}, -6);

        // Test case 6
        testIndexEqualsValue(new int[]{-15, -10, -5, 0, 2, 5, 7, 9, 12}, 5);
    }

    public static void testIndexEqualsValue(int[] sortedArray, int expected) {
        int result = findWhereIndexEqualsValue(sortedArray);
        System.out.println("Array: " + java.util.Arrays.toString(sortedArray));
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println(result == expected ? "Test Passed" : "Test Failed");
        System.out.println();
    }

    public static int findWhereIndexEqualsValue(int[] values) {
        return findWhereIndexEqualsValue(values,0,values.length-1);
    }

    public static int findWhereIndexEqualsValue(int[] values, int left, int right) {

        while (left<=right){
            int mid = left+(right-left)/2;
            int difference = values[mid]-mid;
            if(difference==0){
                return mid;
            }else if(difference>0){
                right=mid-1;
            }else {
                left=mid+1;
            }
        }
        return  -(left+1);
    }
}
