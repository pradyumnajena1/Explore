package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

public class RangeSumQueryProcessor {
    int[] values;

    public RangeSumQueryProcessor(int[] values) {
        ArrayUtils.printArray(values);
        this.values = values;
        for (int i = 1; i < values.length; i++) {
            values[i] = values[i - 1] + values[i];
        }
    }

    public int rangeSum(int a, int b) {
        if (a < 0 || a >= values.length)
            throw new IllegalArgumentException("invalid a " + a);
        if (b < 0 || b >= values.length)
            throw new IllegalArgumentException("invalid b " + b);
        a = Math.min(a, b);
        b = Math.max(a, b);
        return rangeSum(b) - rangeSum(a-1);
    }
    private int rangeSum(int a){
        if(a<0){
            return 0;
        }
        return values[a];
    }

    public static void main(String[] args) {
        RangeSumQueryProcessor queryProcessor = new RangeSumQueryProcessor(ArrayUtils.randomArray(10, 1, 20));
        System.out.println(queryProcessor.rangeSum(2, 7));
        System.out.println(queryProcessor.rangeSum(0, 2));
    }
}
