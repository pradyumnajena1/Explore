package epp.dp.revision;

import epp.array.ArrayUtils;

public class FindSubArraySumClosestToK {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10, -20, 20);
        ArrayUtils.printArray(values);
        Result result = findSubArraySumClosestTo(values, 10);
        System.out.println(result);

        result = findSubArraySumClosestTo(values, 0);
        System.out.println(result);
    }

    public static Result findSubArraySumClosestTo(int[] values, int t) {

        Result prev = new Result(0, 0, values[0]);
        Result result = prev;
        int closest = Math.abs(values[0] - t);
        for (int i = 1; i < values.length; i++) {
            Result current;
            if (Math.abs(t - (prev.sum + values[i])) < Math.abs(t - values[i])) {
                current = new Result(prev.start, i, prev.sum + values[i]);
            } else {
                current = new Result(i, i, values[i]);
            }

            if (Math.abs(current.sum - t) < closest) {
                closest = Math.abs(current.sum - t);
                result = current;
            }
            prev = current;
        }
        return result;
    }

}
