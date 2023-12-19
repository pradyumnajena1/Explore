package epp.recursion;

import epp.array.ArrayUtils;

public class NumberOfInvertedPairs {
    public static void main(String[] args) {
       // int[] array = ArrayUtils.randomArray(5,1,30);
        int[] array =  {19, 27, 28, 13, 23} ;//
       // int[] array =  {4,3,2,1} ;//
        ArrayUtils.printArray(array);
        int num= getNumOfInvertedPairs(array);
        System.out.println(num);
    }

    private static int getNumOfInvertedPairs(int[] array) {
        return getNumOfInvertedPairs(array,0,array.length-1);
    }

    private static int getNumOfInvertedPairs(int[] array, int start, int end) {
        if(start>end || start==end){
            return 0;
        }

        int mid = (start+end)/2;
        int total =0;
        total+= getNumOfInvertedPairs(array,start,mid);
        total+= getNumOfInvertedPairs(array,mid+1,end);

        int writePos = 0;
        int[] mergeArray = new int[end-start+1];
        int leftp = start;
        int rightp = mid+1;
        while (leftp<=mid && rightp<=end){
            if(array[leftp]<array[rightp]){
                mergeArray[writePos++] = array[leftp++];
            }else if(array[leftp]>array[rightp]){
                total+=(mid-leftp+1);
                mergeArray[writePos++] = array[rightp++];

            }else{
                mergeArray[writePos++] = array[leftp++];
            }
        }

        while (leftp<=mid){

            mergeArray[writePos++] = array[leftp++];

        }

        while (rightp<=end){
            mergeArray[writePos++] = array[rightp++];

        }
        System.arraycopy(mergeArray,0,array,start,end-start+1);
        ArrayUtils.printArray( array,start,end);
        System.out.println(total);
        return total;
    }
}
