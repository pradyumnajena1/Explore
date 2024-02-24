package cph;

import epp.array.ArrayUtils;

public class BinarySearch {
    public static void main(String[] args) {
        int[] values =  new int[]{-4, -4, -3, -2, -1, 3,4,4, 4, 7, 9, 9};
        ArrayUtils.printArray(values);
        System.out.println(binarySearch(values, values[6]));
        System.out.println(binarySearch2(values, values[6]));
    }

    public static int binarySearch(int[] values,int x){
        int low = 0;
        int high = values.length-1;
        while (low<=high){
            int mid = (low+high)/2;
            if(values[mid]==x){
                return mid;
            }
            if(values[mid]>x){
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return -(low+1);
    }

    public static int binarySearch2(int[] values,int x){
        int start = 0;
        for(int i=values.length/2;i>=1;i=i/2){
            while (start+i< values.length && values[start+i]<=x){
                start+=i;
            }
        }
        if(values[start]==x){
            return start;
        }
        return -(start+1);
    }
}
