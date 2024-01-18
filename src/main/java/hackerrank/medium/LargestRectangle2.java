package hackerrank.medium;

import epp.array.ArrayUtils;
import lfc.NearestSmallerItem;

import java.util.ArrayList;
import java.util.List;

public class LargestRectangle2 {

    public static void main(String[] args) {
        System.out.println(largestRectangle(new ArrayList<>(List.of(1, 2, 3, 4, 5))));
    }

    public static long largestRectangle(List<Integer> h) {
        int[] heights = h.stream().mapToInt(Integer::intValue).toArray();
        int[] left = NearestSmallerItem.findNearestSmallerItemsOnLeft(heights);
        int[] right = NearestSmallerItem.findNearestSmallerItemsOnRight(heights);
        ArrayUtils.printArray(left);
        ArrayUtils.printArray(right);
        long maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int leftIndex = left[i] == -1 ? 0 : left[i]+1;
            int rightIndex = right[i] == -1 ? heights.length - 1 : right[i]-1;
            int width = rightIndex - leftIndex + 1;
            System.out.println(width);
            long area = (long) heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}
