package hackerrank.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class QuerieswithFixedLength {
    public static void main(String[] args) {
        System.out.println(solve(new ArrayList<>(List.of(2, 3, 4, 5, 6)), new ArrayList<>(List.of(2, 3))));
    }

    public static List<Integer> solve(List<Integer> arr, List<Integer> queries) {
        // Write your code here
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < queries.size(); i++) {

            int min = getMinOfMaxOfWindow(arr, queries.get(i));
            result.add(min);
        }
        return result;
    }

    private static int getMinOfMaxOfWindow(List<Integer> arr, Integer windowSize) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < windowSize && i < arr.size(); i++) {
            int incomingValue = arr.get(i);
            while (!queue.isEmpty() &&  queue.peekLast() < incomingValue) {
                queue.pollLast();
            }
            queue.offerLast(incomingValue);
        }

        int min = queue.peekFirst();

        for (int i = 0; i + windowSize < arr.size(); i++) {

            int outgoingValue = arr.get(i);
            if (outgoingValue == queue.peekFirst()) {
                queue.pollFirst();
            }

            int incomingValue = arr.get(i + windowSize);
            while (!queue.isEmpty() && queue.peekLast() < incomingValue) {
                queue.pollLast();
            }
            queue.offerLast(incomingValue);

            if (min > queue.peekFirst()) {
                min = queue.peekFirst();
            }

        }
        return min;
    }
}
