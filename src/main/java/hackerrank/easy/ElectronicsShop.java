package hackerrank.easy;

import java.util.Arrays;

public class ElectronicsShop {
    public static void main(String[] args) {
        System.out.println(getMoneySpent(new int[]{40, 50, 60}, new int[]{5, 8, 12}, 60));
        System.out.println(getMoneySpent(new int[]{4}, new int[]{5 }, 5));
    }
    static int getMoneySpent(int[] keyboards, int[] drives, int b) {
        /*
         * Write your code here.
         */
        Arrays.sort(keyboards);
        Arrays.sort(drives);
        Integer firstDrive = getLastSmallerOrEqualItemIndex(drives, b);

        Integer totalMaxCost = null;
        if(firstDrive!=null){
            for(int i=firstDrive;i>=0;i--){
                Integer kbIndex = getLastSmallerOrEqualItemIndex(keyboards, b - drives[i]);

                if(kbIndex!=null){
                    if(totalMaxCost==null || totalMaxCost< drives[i] + keyboards[kbIndex]){
                        totalMaxCost = drives[i] + keyboards[kbIndex];
                    }

                }
            }
          return   totalMaxCost==null?-1:totalMaxCost;
        }
        return -1;

    }
    static Integer getLastSmallerOrEqualItemIndex(int[] arr,int value){
        int start=0;
        int end = arr.length-1;
        Integer index = null;
        while (start<=end){
            int mid  = (start+end)/2;
            if(arr[mid]<=value){
                index = mid;
                start=start+1;
            }else{
                end = mid-1;
            }
        }
        return index;
    }
}
