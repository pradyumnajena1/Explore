package epp.array.revision;

import epp.array.ArrayUtils;

public class IncrementBigInteger {
    public static void main(String[] args) {
        int[] arr = new int[]{9,9,9 };
        increment(arr);
        ArrayUtils.printArray(arr);
    }

    private static void increment(int[] arr) {
        int carry = 1;
        for(int i=arr.length-1;i>=0;i--){
            int sum = carry + arr[i];
            carry = sum/10;
            int digit = sum % 10;
            arr[i]= digit;

        }
    }
}
