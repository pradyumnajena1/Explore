package epp.honours;

import epp.array.ArrayUtils;

public class FindFirstMissingPositiveNumber {
    public static void main(String[] args){
        int[] arr = {3,4,-1,1,2,4,6,7,8,9,10};
        int firstMissingPositive = findFirstMissingPositive(arr);
        System.out.println(firstMissingPositive);
    }

    private static int findFirstMissingPositive(int[] arr) {

        for(int i=0;i<arr.length;i++){
            while(arr[i]>0 && arr[i]<=arr.length && arr[arr[i]-1]!=arr[i]){
                ArrayUtils.swap(arr,i, arr[i]-1);
            }
            ArrayUtils.printArray(arr);
        }
        for(int i=0; i<arr.length; i++){
            if(arr[i]!=i+1){
                return i+1;
            }
        }
        return arr.length+1;
    }
}
