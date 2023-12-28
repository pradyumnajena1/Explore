package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

import java.util.Stack;

public class NearestSmallerElement {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10,1,20);
        ArrayUtils.printArray(values);
        int[] nearestSmallerElement = getNearestSmallerElement(values);
        ArrayUtils.printArray(nearestSmallerElement);
    }

    private static int[] getNearestSmallerElement(int[] values) {
        int[] precedingSmallerElements = getNearestPrecedingSmallerElements(values);
        int[] succeedingSmallerElements = getNearestSucceedingSmallerElements(values);
        int[] smallerElements = new int[values.length];
        for(int i=0;i< values.length;i++){
            smallerElements[i] = -1;
            if( precedingSmallerElements[i]==-1){
                smallerElements[i] = succeedingSmallerElements[i];
            }else if(succeedingSmallerElements[i]==-1){
                smallerElements[i] = precedingSmallerElements[i];
            }else{
                if(Math.abs(i-precedingSmallerElements[i])<=Math.abs(i-succeedingSmallerElements[i])){
                    smallerElements[i] = precedingSmallerElements[i];
                }else{
                    smallerElements[i] = succeedingSmallerElements[i];
                }
            }

        }
        return smallerElements;
    }

    private static int[] getNearestPrecedingSmallerElements(int[] values) {
        Stack<Integer> stack = new Stack<>();
        int[] precedingSmaller = new int[values.length];
        for(int i=0;i< values.length;i++){
            precedingSmaller[i] = getNearestSmaller(values, stack, i);
        }
        return precedingSmaller;
    }
    private static int[] getNearestSucceedingSmallerElements(int[] values) {
        Stack<Integer> stack = new Stack<>();
        int[] succeedingSmaller = new int[values.length];
        for(int i= values.length-1;i>=0 ;i--){
            succeedingSmaller[i] = getNearestSmaller(values,stack,i);
        }
        return succeedingSmaller;
    }

    private static int getNearestSmaller(int[] values, Stack<Integer> stack, int i) {
        int nearestSmallerIndex = -1;
        while (!stack.isEmpty() && values[stack.peek()] >= values[i]){
            stack.pop();
        }
        if(!stack.isEmpty()){
            nearestSmallerIndex = stack.peek();
        }
        stack.push(i);
        return nearestSmallerIndex;
    }


}
