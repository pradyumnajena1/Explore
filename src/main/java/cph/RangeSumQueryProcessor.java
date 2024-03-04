package cph;

import epp.array.ArrayUtils;

public class RangeSumQueryProcessor {
    private int[] prefixSum;

    public RangeSumQueryProcessor(int[] values) {
        ArrayUtils.printArray(values);
        this.prefixSum = new int[values.length];
        int sum = 0;
        for(int i=0;i< values.length;i++){
            sum=sum+values[i];
            prefixSum[i] = sum;
        }
    }
    public int rangeSum(int a, int b){
        return rangeSum(b) - rangeSum(a-1);
    }
    private int rangeSum(int a){
        if(a<0){
            return 0;
        }
        return prefixSum[a];
    }

    public static void main(String[] args) {
        RangeSumQueryProcessor sumQueryProcessor = new RangeSumQueryProcessor(ArrayUtils.randomArray(10,1,20));
        System.out.println(sumQueryProcessor.rangeSum(2, 7));
        System.out.println(sumQueryProcessor.rangeSum(3, 9));
    }

}
