package epp.array.revision;

import epp.array.ArrayUtils;

public class FirstMissingPositiveNumber {
    public static void main(String[] args) {
        int[] arr ={-8,-7,-6};// ArrayUtils.randomArray(10,1,10);
        ArrayUtils.printArray(arr);
        int firstMissingPositive = findFirstMissingPositive(arr);
        ArrayUtils.printArray(arr);
        System.out.println(firstMissingPositive);
    }

    private static int findFirstMissingPositive(int[] arr) {
        for(int i=0;i<arr.length;i++){
            if(arr[i]>=1 && arr[i]<=arr.length && arr[i]!=i+1 && arr[arr[i]-1]!=arr[i] ){
                ArrayUtils.swap(arr,arr[i]-1,i);
            }
        }
        for(int i=0;i<arr.length;i++){
            if(arr[i]!=i+1){
                return i+1;
            }
        }
        return arr.length+1;
    }
}
