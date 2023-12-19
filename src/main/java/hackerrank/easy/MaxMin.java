package hackerrank.easy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public static void main(String[] args) {
        System.out.println(maxMin(2, new ArrayList<>(List.of(1, 4, 2, 7))));
        System.out.println(maxMin(3, new ArrayList<>(List.of(10, 100, 300, 200, 1000, 20, 30))));
        System.out.println(maxMin(4, new ArrayList<>(List.of(1, 2, 3, 4, 10, 20, 30, 40, 100, 200))));
        System.out.println(maxMin(2, new ArrayList<>(List.of(1, 2, 1, 2, 1))));
    }

    public static int maxMin(int k, List<Integer> arr) {
        // Write your code here
        arr.sort(Comparator.naturalOrder());
        int minFairness = Integer.MAX_VALUE;
        for (int i = 0; i + k <= arr.size(); i++) {
            int fairness = arr.get(i + k - 1) - arr.get(i);
            if (fairness < minFairness) {
                minFairness = fairness;
            }
        }
        return minFairness;

    }
}
