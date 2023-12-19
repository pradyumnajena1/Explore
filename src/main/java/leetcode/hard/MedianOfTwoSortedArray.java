package leetcode.hard;

public class MedianOfTwoSortedArray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2};
        int[] nums2 = new int[]{3,4};
        double median = getMedian(nums1,nums2);
        System.out.println(median);
    }

    private static double getMedian(int[] nums1, int[] nums2) {
        int totalNum = nums1.length + nums2.length;
        int median =   (totalNum )/2;
        int[] smaller = nums1.length>nums2.length?nums2:nums1;
        int[] bigger = nums1.length>nums2.length?nums1:nums2;
        int low = 0;
        int high = Math.min(median,smaller.length);
        int split_smaller = -1;
        int split_bigger =-1;
        while (low<high){
            int mid = (low+high)/2;
              split_smaller = mid;
              split_bigger = median - split_smaller;
            if(getItem(smaller,split_smaller-1) <= getItem(bigger,split_bigger) && getItem(bigger,split_bigger-1)<= getItem(smaller,split_smaller)){
                break;
            }else if(getItem(smaller,split_smaller-1) > getItem(bigger,split_bigger)){
                high = mid-1;
            }else{
                low = mid+1;
            }


        }
        System.out.println(split_smaller);
        System.out.println(split_bigger);
        if(totalNum%2==0  ){
            return ((double) (Math.max(getItem(smaller,split_smaller-1),getItem(bigger,split_bigger-1)) + Math.min(getItem(smaller,split_smaller),getItem(bigger,split_bigger))))/2;
        }else{
            return Math.min(getItem(smaller,split_smaller),getItem(bigger,split_bigger));
        }

    }

    private static int getItem(int[] array, int i) {
        if(i>=0 && i<array.length){
            return array[i];
        }else if(i<0){
            return Integer.MIN_VALUE;
        }else{
            return Integer.MAX_VALUE;
        }

    }
}
