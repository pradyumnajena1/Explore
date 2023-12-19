package hackerrank.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class PermutationGame {
    public static void main(String[] args) {
        System.out.println(permutationGame(new ArrayList<>(List.of(4, 2, 3, 1))));
        System.out.println(permutationGame(new ArrayList<>(List.of(1, 3, 2))));
        System.out.println(permutationGame(new ArrayList<>(List.of(5, 3, 2, 1, 4))));
    }

    public static String permutationGame(List<Integer> arr) {
        // Write your code here
        int turn = 0;
        List<Integer> indices = null;
        while ((indices = isSorted(arr, 0, arr.size() - 1)).size() != 0) {
            turn++;
            if (indices.size() == 1) {
                arr.remove(indices.get(0).intValue());
            } else {
                List<Integer> permutation = getPermutation(arr);


                int minDistance = arr.size();
                int minIndex = 0;
                for (int i = 0; i < permutation.size(); i++) {

                    if (Math.abs(permutation.get(i) - i) < minDistance) {
                        minDistance = Math.abs(permutation.get(i) - i);
                        minIndex = i;
                    }
                }
                arr.remove(minIndex);
            }

        }
        return turn % 2 == 1 ? "Alice" : "Bob";
    }

    private static List<Integer> getPermutation(List<Integer> arr) {
        List<Integer> copy = new ArrayList<>(arr);
        List<Integer> res = new ArrayList<>();
        copy.sort(Comparator.naturalOrder());
        for (int value : arr) {
            res.add(Collections.binarySearch(copy, value));
        }
        return res;
    }


    private static List<Integer> isSorted(List<Integer> arr, int start, int end) {
        List<Integer> indices = new ArrayList<>();
        for (int i = start + 1; i <= end; i++) {
            if (arr.get(i) < arr.get(i - 1)) {
                indices.add(i);
            }
        }
        return indices;
    }
}
