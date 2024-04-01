package amaze;

import epp.array.ArrayUtils;

import java.util.Stack;

public class StockSpanProblem {
    public static void main(String[] args) {
        ArrayUtils.printArray(getStockSpans(new int[]{100, 80, 60, 70, 60, 75, 85}));
        ArrayUtils.printArray(getStockSpans(new int[]{10, 4, 5, 90, 120, 80}));
    }

    private static int[] getStockSpans(int[] stockPrices) {

        int[] result = new int[stockPrices.length];
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i< stockPrices.length;i++){
            while (!stack.empty() &&  stockPrices[ stack.peek()] <= stockPrices[i]){
                stack.pop();
            }
            if(stack.isEmpty()){
                result[i] = i+1;
            }else{
                result[i] = i- stack.peek();
            }
            stack.push(i);
        }
        return result;
    }
}
