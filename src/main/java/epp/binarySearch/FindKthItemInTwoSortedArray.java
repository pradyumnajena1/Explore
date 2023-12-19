package epp.binarySearch;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class FindKthItemInTwoSortedArray {
    public static void main(String[] args) {
        int[] array_a = ArrayUtils.randomArray(10,1,40);
        int[] array_b = ArrayUtils.randomArray(15,1,40);
        Arrays.sort(array_a);
        Arrays.sort(array_b);
        System.out.println(Arrays.toString(array_a));
        System.out.println(Arrays.toString(array_b));
        int value = findKthItem(array_a,array_b,10);
        System.out.println(value);
    }

    private static int findKthItem(int[] array_a, int[] array_b, int numItem) {

        int[] smaller,bigger;
        if(array_a.length<=array_b.length){
            smaller = array_a;
            bigger = array_b;
        }else{
            smaller = array_b;
            bigger = array_a;
        }

        int low =0;
        int high  = Math.min(numItem,smaller.length);
        int split_smaller = 0;
        int split_bigger = 0;

        while (low<=high){
              int mid = low+(high-low)/2;
              split_smaller = mid;
              split_bigger = numItem - split_smaller;

            if( getItem(smaller,split_smaller)   > getItem(bigger,split_bigger-1)  && getItem(bigger,split_bigger)  > getItem(smaller,split_smaller-1) ){
                break;
            }else if( getItem(smaller,split_smaller)  < getItem(bigger,split_bigger-1)  ){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return  Math.max(getItem(smaller,split_smaller-1),getItem(bigger,split_bigger-1));
    }

  static   int getItem(int[] array,int index){
        if(index<0){
            return Integer.MIN_VALUE;
        }else if(index>=array.length){
            return Integer.MAX_VALUE;
        }else{
            return array[index];
        }
    }
}
