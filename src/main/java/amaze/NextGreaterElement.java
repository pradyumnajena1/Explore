package amaze;

import epp.array.ArrayUtils;

import java.util.Stack;

public class NextGreaterElement {
    public static void main(String[] args) {
        ArrayUtils.printArray(findNextGreaterElement(new int[]{4 , 5 , 2 , 25}));
        ArrayUtils.printArray(findNextGreaterElement(new int[]{13 , 7, 6 , 12}));
    }

    private static int[] findNextGreaterElement(int[] values) {
        int[] result = new int[values.length];
        Stack<Integer> stack = new Stack<>();
        for(int i= values.length-1;i>=0;i--){
            while (!stack.empty() &&  values[ stack.peek()] <= values[i]){
                stack.pop();
            }
            if(stack.isEmpty()){
                result[i] = -1;
            }else{
                result[i] = values[stack.peek()];
            }
            stack.push(i);
        }
        return result;
    }
}
