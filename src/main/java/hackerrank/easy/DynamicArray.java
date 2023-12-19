package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;

public class DynamicArray {
    public static void main(String[] args) {
        ArrayList<List<Integer>> queries = new ArrayList<>();
        queries.add(new ArrayList<>(List.of(1, 0, 5)));
        queries.add(new ArrayList<>(List.of(1, 1, 7)));
        queries.add(new ArrayList<>(List.of(1, 0, 3)));
        queries.add(new ArrayList<>(List.of(2, 1, 0)));
        queries.add(new ArrayList<>(List.of(2, 1, 1)));
        System.out.println(dynamicArray(2, queries));
    }

    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
        // Write your code here

        List<List<Integer>> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(new ArrayList<>());
        }
        int lastAnswer = 0;
        List<Integer> answers = new ArrayList<>();
        for (List<Integer> query : queries) {
            if (query.get(0) == 1) {
                int index = (lastAnswer ^ query.get(1)) % n;
                arr.get(index).add(query.get(2));

            } else if (query.get(0) == 2) {
                int index = (lastAnswer ^ query.get(1)) % n;
                lastAnswer = arr.get(index).get(query.get(2) % (arr.get(index).size()));
                answers.add(lastAnswer);
            }

        }
        return answers;
    }
}
