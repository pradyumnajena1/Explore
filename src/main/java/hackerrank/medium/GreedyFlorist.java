package hackerrank.medium;

import java.util.Arrays;

public class GreedyFlorist {
    public static void main(String[] args) {
        System.out.println(getMinimumCost(3, new int[]{2, 5, 6}));
        System.out.println(getMinimumCost(2, new int[]{2, 5, 6}));
        System.out.println(getMinimumCost(3, new int[]{1 ,3 ,5 ,7, 9}));
    }

    static int getMinimumCost(int k, int[] c) {
        Arrays.sort(c);
        int end = c.length - 1;
        int previousPurchase = 0;
        int totalCost = 0;
        while (end >= 0) {
            for (int i = 0; i < k && end - i >= 0; i++) {
                totalCost += (previousPurchase + 1) * c[end - i];
            }
            previousPurchase++;
            end = end-k;
        }
        return totalCost;

    }

}
