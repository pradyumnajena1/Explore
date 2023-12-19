package epp.binarySearch.revision;

import epp.array.ArrayUtils;

public class FindKthElementInTwoSortedArrays {
    public static void main(String[] args) {
        int[] arr1 = ArrayUtils.randomSortedArray(8,1,8);
        int[] arr2 = ArrayUtils.randomSortedArray(14,12,23);
        ArrayUtils.printArray(arr1);
        ArrayUtils.printArray(arr2);
        int value =  findElement(arr1,arr2,15);
        System.out.println(value);
    }

    private static int findElement(int[] arr1, int[] arr2, int n) {
        if(arr1==null||arr2==null){
            throw new IllegalArgumentException("values are null");
        }
        if(arr1.length+arr2.length<n){
            throw new IllegalArgumentException("not enough elements");
        }
        int[] smaller;
        int[] bigger;
        if(arr1.length<arr2.length){
            smaller=arr1;
            bigger=arr2;
        }else {
            smaller=arr2;
            bigger=arr1;
        }
        int start = 0;
        int end = Math.min(n,smaller.length);

        int smallerSplit = 0;
        int biggerSplit = 0;
        while (start<=end){
              smallerSplit = (start+end)/2;
              biggerSplit = n - smallerSplit;
            if( getElement(smaller,smallerSplit-1) <= getElement(bigger,biggerSplit) &&
                    getElement(bigger, biggerSplit - 1) <= getElement(smaller, smallerSplit)){
                break;
            } else if (getElement(smaller,smallerSplit-1) > getElement(bigger,biggerSplit)) {
                end = smallerSplit-1;
            }else{
                start = smallerSplit+1;
            }
        }
        return Math.max(getElement(smaller,smallerSplit-1),getElement(bigger,biggerSplit-1));
    }

    private static int getElement(int[] array, int i) {
        return i<0?Integer.MIN_VALUE:i<array.length?array[i]:Integer.MAX_VALUE;
    }
}
