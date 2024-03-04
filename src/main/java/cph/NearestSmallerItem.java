package cph;

import epp.array.ArrayUtils;

import java.util.Stack;

public class NearestSmallerItem {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10, 1, 20);
        ArrayUtils.printArray(values);
        int[] nearestSmaller = getNearestSmallerItems(values);
        ArrayUtils.printArray(nearestSmaller);

    }

    private static int[] getNearestSmallerItems(int[] values) {
        int[] left = new int[values.length];
        int[] right = new int[values.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < values.length; i++) {
            while (!stack.isEmpty() && values[stack.peek()] >= values[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack = new Stack<>();
        for (int i = values.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && values[stack.peek()] >= values[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        int[] result = new int[values.length];
        for (int i = 0; i < result.length; i++) {
            if (left[i] == -1) {
                result[i] = right[i];
            } else if (right[i] == -1) {
                result[i] = left[i];
            } else if (i-left[i] >  right[i] - i) {
                result[i] = right[i];
            } else {
                result[i] = left[i];
            }
            result[i] = result[i]!=-1?values[result[i]]:Integer.MIN_VALUE;

        }

        return result;
    }
}
