package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

public class SubArraySumK {
    public static void main(String[] args) {
        int[] values = {1, 3, 2, 5, 1, 1, 2, 3}; //ArrayUtils.randomArray(10, 1, 20);
        boolean isSubArraySumPresent = isSubArraySumPresent(values, 8);
        System.out.println(isSubArraySumPresent);
    }

    private static boolean isSubArraySumPresent(int[] values, int k) {
        int left = 0;
        int right = 0;
        int sum = values[0];
        while (right < values.length - 1 && (sum + values[right + 1]) <= k) {
            sum += values[right + 1];
            right++;
        }
        while (left < values.length && right < values.length) {

            System.out.println(left + " " + right + " " + sum);
            if (sum == k) {
                return true;
            }
            sum -= values[left];
            left++;

            while (right < values.length - 1 && (sum + values[right + 1]) <= k) {
                sum += values[right + 1];
                right++;
            }


        }
        return false;
    }
}
