package epp.recursion.revision;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class CountInvertedPairsInAnArray {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10, 1, 10);
        ArrayUtils.printArray(values);
        System.out.println(countInvertedPairsBrute(values, 0, values.length - 1));
        int count =  countInvertedPairs(values);
        System.out.println(count);


    }

    private static int countInvertedPairs(int[] values) {
        return countInvertedPairs(values,0,values.length-1);
    }

    private static int countInvertedPairs(int[] values, int start, int end) {
        if(start==end){
            return 0;
        }
        int mid = (start+end)/2;
        int left = countInvertedPairs(values,start,mid);
        int right = countInvertedPairs(values,mid+1,end);
        Arrays.sort(values,start,mid+1);
        Arrays.sort(values,mid+1,end);
        int accros = merge(values, start, end, mid);
        return left+right+accros;
    }

    private static int merge(int[] values, int start, int end, int mid) {
        int leftIndex = start;
        int rightIndex = mid +1;
        int accros = 0;
        int[] copy = new int[end-start+1];
        int writeIndex = 0;
        while (leftIndex<= mid && rightIndex<= end){
            if(values[leftIndex]<= values[rightIndex]){
                copy[writeIndex++] = values[leftIndex++];

            }else{
                copy[writeIndex++] = values[rightIndex++];
                accros += mid -leftIndex+1;
            }
        }
        while (leftIndex<=mid){
            copy[writeIndex++] = values[leftIndex++];
        }
        while (rightIndex<=end){
            copy[writeIndex++] = values[rightIndex++];
        }
        System.arraycopy(copy,0,values,start,end-start+1);
        return accros;
    }

    private static int countInvertedPairsBrute(int[] values, int start, int end) {
        int count = 0;
        for(int i=start;i<=end;i++){
            for(int j=i+1;j<=end;j++){
                if(i<j && values[i]>values[j]){
                    count++;
                }
            }
        }
        return count;
    }
}
