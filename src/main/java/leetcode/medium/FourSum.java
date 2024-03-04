package leetcode.medium;

import epp.Pair;

import java.util.*;

public class FourSum {
    public static void main(String[] args) {
          FourSum fourSum = new FourSum();

        System.out.println(fourSum.fourSum(new int[]{1000000000,1000000000,1000000000,1000000000}, -294967296));


    }

    public static List<List<Integer>> find2SumHelper(int[] values, long sum, int start, int end) {
        List<List<Integer>> result = new ArrayList<>();
        if (start < 0 || start >= values.length || end < 0 || end >= values.length) {
            return result;
        }
        while (start < end) {
            if (values[start] + values[end] < sum) {
                start++;
            } else if (values[start] + values[end] > sum) {
                end--;
            } else {
                start = lastIndexOf(values, values[start] , start , end-1);
                end = firstIndexOf(values, values[end] , start+1 , end);
                result.add(new ArrayList<>(List.of( values[start], values[end])));
                start++;
                end--;
            }
        }
        return result;
    }

    public static int lastIndexOf(int[] values, int value, int low, int high) {
        Integer index = null;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (value >= values[mid]) {
                low = mid + 1;
                if (value == values[mid]) {
                    index = mid;
                }
            } else {
                high = mid - 1;
            }

        }
        return index == null ? -(low + 1) : index;
    }
    public static int firstIndexOf(int[] values, int value, int low, int high) {
        Integer index = null;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (value <= values[mid]) {
                high = mid - 1;
                if (value == values[mid]) {
                    index = mid;
                }
            } else {
                low = mid + 1;
            }

        }
        return index == null ? -(low + 1) : index;
    }



    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                long sum = nums[i] + nums[j];
                long requiredSum = target - sum;
                List<List<Integer>> pairs = find2SumHelper(nums, requiredSum, i + 1, j - 1);
                for (List<Integer> pair : pairs) {
                    result.add(new ArrayList<>(List.of(nums[i], pair.get(0) , pair.get(1), nums[j])));
                }
            }
        }
        return new ArrayList<>(result);
    }
}
