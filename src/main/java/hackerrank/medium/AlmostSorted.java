package hackerrank.medium;

import java.util.ArrayList;
import java.util.List;

public class AlmostSorted {
    public static void main(String[] args) {
        almostSorted(new ArrayList<>(List.of(1, 3, 2)));
        almostSorted(new ArrayList<>(List.of(2, 3, 7, 6, 5, 4, 9, 10)));
    }

    public static void almostSorted(List<Integer> arr) {
        // Write your code here
        if (arr.size() <= 1) {
            System.out.println("yes");
        }

        int start = 0;
        int end = arr.size() - 1;

        while (start < arr.size() - 1 && arr.get(start) <= arr.get(start + 1)) {
            start++;
        }
        while (end > 0 && arr.get(end) >= arr.get(end - 1)) {
            end--;
        }
        if (start > end) {
            System.out.println("yes");
            return;
        } else {


            swap(arr, start, end);
            if (isSorted(arr, Math.max(0, start - 1), Math.min(end + 1, arr.size() - 1))) {
                System.out.println("yes");
                System.out.println("swap " + (start+1) + " " + (end+1));
            } else {
                swap(arr, start, end);
                reverse(arr, start, end);
                if (isSorted(arr, Math.max(0, start - 1), Math.min(end + 1, arr.size() - 1))) {
                    System.out.println("yes");
                    System.out.println("reverse  " + (start+1) + " " + (end+1));
                }
            }

        }


    }

    private static boolean isSorted(List<Integer> arr, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            if (arr.get(i) < arr.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static <T> void swap(List<T> values, int i, int j) {
        T temp = values.get(i);
        values.set(i, values.get(j));
        values.set(j, temp);
    }

    public static <T> void reverse(List<T> list, int start, int end) {

        while (start < end) {
            T copy = list.get(start);
            list.set(start, list.get(end));
            list.set(end, copy);
            start++;
            end--;
        }

    }
}
