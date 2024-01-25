package hackerrank.medium;

import guidetocompetitiveprogramming.RangeUpdateSegmentTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KindergartenAdventures {
    public static int solve(List<Integer> t) {
        // Return the ID


        RangeUpdateSegmentTree rangeUpdateSegmentTree = new RangeUpdateSegmentTree(new ArrayList<>(Collections.nCopies(t.size(), t.size())));
        for (int i = 0; i < t.size(); i++) {
            if (t.get(i) > 0) {
                int start = (i - t.get(i) + 1 + t.size()) % t.size();
                if (start <= i) {
                    rangeUpdateSegmentTree.rangeUpdate(start, i, -1);
                } else {
                    rangeUpdateSegmentTree.rangeUpdate(0, i, -1);
                    rangeUpdateSegmentTree.rangeUpdate(start, t.size() - 1, -1);
                }
            }

        }

        int max = 0;
        for (int i = 1; i < t.size(); i++) {
            if (rangeUpdateSegmentTree.getValue(i) > rangeUpdateSegmentTree.getValue(max)) {
                max = i;
            }
        }
        return max + 1;
    }

    public static void main(String[] args) {
        System.out.println(solve(new ArrayList<>(List.of(0, 1, 2))));
        System.out.println(solve(new ArrayList<>(List.of(1, 0, 0))));
    }
}
