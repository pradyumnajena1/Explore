package leetcode.hard;

public class FindMinimumInRotatedSortedArrayII {
    public static void main(String[] args) {
        Solution solution = new Solution();
      /*   System.out.println(solution.findMin(new int[]{1, 3, 5}));
        System.out.println(solution.findMin(new int[]{2, 2, 2, 0, 1}));
        System.out.println(solution.findMin(new int[]{4, 5, 6, 7, 0, 1, 4}));
        System.out.println(solution.findMin(new int[]{1,1}));*/
        System.out.println(solution.findMin(new int[]{3,1,1}));
    }

    private static class Solution {
        public int findMin(int[] nums) {
            int low = 0;
            int high = nums.length - 1;
            while (low < high) {

                 if(nums[low]<nums[high]){
                     return nums[low];
                 }

                int mid = (low + high) / 2;

                if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                    low++;
                    high--;
                } else if (mid > 0 && nums[mid] < nums[mid - 1] && mid < nums.length - 1 && nums[mid] <= nums[mid + 1]) {
                    return nums[mid];
                } else if (nums[low] <= nums[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }

            }
            return nums[low];

        }
    }
}
