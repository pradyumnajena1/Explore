package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;

public class SubarrayDivision {
    public static void main(String[] args) {
        System.out.println(birthday(new ArrayList<>(List.of(2, 2, 1, 3, 2)), 4, 2));
        System.out.println(birthday(new ArrayList<>(List.of(1 ,2 ,1 ,3, 2)), 3, 2));
    }

    public static int birthday(List<Integer> s, int d, int m) {
        // Write your code here
        List<Integer> cumSum = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < s.size(); i++) {
            sum = sum + s.get(i);
            cumSum.add(sum);
        }
        int count = 0;
        for (int i = m - 1; i < s.size(); i++) {
            if (cumSum.get(i) - (i >= m   ? cumSum.get(i - m) : 0) == d) {
                count++;
            }
        }
        return count;
    }
}
