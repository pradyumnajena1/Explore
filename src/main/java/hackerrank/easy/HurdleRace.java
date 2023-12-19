package hackerrank.easy;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class HurdleRace {
    public static void main(String[] args) {

    }

    public static int hurdleRace(int k, List<Integer> height) {
        // Write your code here
        Optional<Integer> max = height.stream().max(Comparator.naturalOrder());
        int result = max.get() - k>0?max.get() - k:0;
        return result;
    }
}
