package hackerrank.easy;

import java.util.List;

public class FindMedian {
    public static void main(String[] args) {

    }

    public static int findMedian(List<Integer> arr) {
        // Write your code here
        arr.sort(Integer::compareTo);
        return arr.get(arr.size()/2);

    }
}
