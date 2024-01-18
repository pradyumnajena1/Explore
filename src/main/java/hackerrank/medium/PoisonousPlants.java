package hackerrank.medium;

import epp.Pair;
import guidetocompetitiveprogramming.SegmentTree;
import lfc.NearestSmallerItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class PoisonousPlants {

    public static void main(String[] args) {
         System.out.println(poisonousPlants(new ArrayList<>(List.of(6, 5, 8, 4, 7, 10, 9))));
         System.out.println(poisonousPlants(new ArrayList<>(List.of(3, 6, 2, 7, 5))));
         System.out.println(poisonousPlants(new ArrayList<>(List.of(4, 3, 7, 5, 6, 4, 2))));
        System.out.println(poisonousPlants(new ArrayList<>(List.of(1,1,1,1,1))));
        System.out.println(poisonousPlants(new ArrayList<>(List.of(3, 7, 1, 2, 4, 8, 2, 7, 10))));
    }

    public static int poisonousPlants(List<Integer> p) {
        // Write your code here

        SegmentTree segmentTree = new SegmentTree(new int[p.size()], Integer::max, 0);
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        stack.add(new Pair<>(p.get(0), 0));
        for (int i = 1; i < p.size(); i++) {

            if (!stack.isEmpty() && stack.peek().getFirst() >= p.get(i)) {
                while (!stack.isEmpty() && stack.peek().getFirst() >= p.get(i)) {
                    stack.pop();
                }

            }

            if (!stack.isEmpty()  ) {
                int max = segmentTree.rangeResult(stack.peek().getSecond() + 1, i - 1);
                int numDays = 1 + max;
                segmentTree.set(i, numDays);

            }
            stack.push(new Pair<>(p.get(i), i));
            //System.out.println(stack);
        }

        return segmentTree.rangeResult(0, p.size()-1);
    }


}
