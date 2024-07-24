package epp.honours;

public class SearchInTwoSortedArrays {
    public static void main(String[] args){
        int[] arr1 = {2, 5,7, 8, 12, 16, 23, 38, 56, 72, 91};
        int[] arr2 = {3, 4, 7, 10, 11, 12, 15, 18, 22, 30};
        int k = 6;
        int value = findKthItem(arr1, arr2, k);
    System.out.println(k+"th item value: " + value);
    }

    private static int findKthItem(int[] arr1, int[] arr2, int k) {
        int m = arr1.length;
        int n = arr2.length;
        if(m>n){
            return findKthItem(arr2, arr1, k);
        }
        int lower = Math.max(0,k-n);
        int upper = Math.min(k,arr1.length);

        while(lower<upper){
            int mid1 = lower + (upper-lower)/2;
            int mid2 = k - mid1 ;

            int val1 = getValue(arr1, mid1-1);
            int val2 = getValue(arr1, mid1);

            int val3 = getValue(arr2, mid2-1);
            int val4 =  getValue(arr2, mid2);
            if(val1<=val4 && val3<=val2){
                return Math.max(val1, val3);
            } else if(val1 > val4){
                upper = mid1 - 1;
            } else {
                lower = mid1 + 1;
            }
        }
        int val1 = getValue(arr1, lower-1);
        int val2 = getValue(arr2, k-lower-1);
        return Math.max(val1,val2);
    }

    private static int getValue(int[] values,int index){
        if(index<0){
           return Integer.MIN_VALUE;
        }else if(index>=values.length){
            return Integer.MAX_VALUE;
        }else{
            return values[index];
        }
    }
}
