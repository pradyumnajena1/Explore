package hackerrank.easy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MarcsCakewalk {
    public static void main(String[] args) {
        System.out.println(marcsCakewalk(new ArrayList<>(List.of(1, 3, 2))));
    }

    public static long marcsCakewalk(List<Integer> calorie) {
        // Write your code here
        calorie.sort(Comparator.reverseOrder());
        int totalWalk = 0;
        for (int i = 0; i < calorie.size(); i++) {
            totalWalk += Math.pow(2, i) * calorie.get(i);
        }
        return totalWalk;
    }
}
