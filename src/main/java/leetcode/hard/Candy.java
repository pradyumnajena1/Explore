package leetcode.hard;

import epp.array.ArrayUtils;

public class Candy {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.candy(new int[]{1, 0, 2}));
        System.out.println(solution.candy(new int[]{1, 2, 2}));
        System.out.println(solution.candy(new int[]{1, 2, 87, 87, 87, 2, 1}));
        System.out.println(solution.candy(new int[]{1,3,4,5,2}));
        System.out.println(solution.candy(new int[]{2, 4, 2, 6, 1, 7, 8, 9, 2, 1}));
    }

    private static class Solution {
        public int candy(int[] ratings) {
            int[] candies = new int[ratings.length];
            for (int i = 0; i < candies.length; i++) {
                candies[i] = 1;
            }
            boolean done = true;
            do {
                done = true;
                for (int i = 0; i < candies.length; i++) {

                    if ((i > 0 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1])) {
                        candies[i] = candies[i - 1] + 1;
                        done=false;
                    }
                }
                for (int i = candies.length - 1; i >= 0; i--) {
                    if ((i < candies.length - 1 && ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1])) {
                        candies[i] = candies[i + 1] + 1;
                        done=false;
                    }
                }
            }while (!done);

            int sum = 0;
            for (int i = 0; i < candies.length; i++) {
                sum += candies[i];
            }

            return sum;
        }
    }
}
