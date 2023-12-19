package epp.stacknqueue.revision;

import epp.array.ArrayUtils;

import java.util.Stack;

public class SortStackDescending {
    public static void main(String[] args) {
        Stack<Integer> stack = getStack();

        System.out.println(stack);
        sortStackDescending(stack);
        System.out.println(stack);

        stack = getStack();

        System.out.println(stack);
        sortStackDescending2(stack);
        System.out.println(stack);

    }

    private static Stack<Integer> getStack() {
        Stack<Integer> stack = new Stack<>();

        int[] values = ArrayUtils.randomArray(10, 1, 20);
        for(int value:values){
            stack.push(value);
        }
        return stack;
    }

    private static void sortStackDescending(Stack<Integer> stack) {
        Stack<Integer> stack2 = new Stack<>();
        while (!stack.isEmpty()){
            stack2.push(stack.pop());
        }
        while (!stack2.isEmpty()){
            Integer pop = stack2.pop();
            while (!stack.isEmpty()&&stack.peek()>pop){
                stack2.push(stack.pop());
            }
            stack.push(pop);
        }
    }

    private static void sortStackDescending2(Stack<Integer> stack) {
        if(!stack.isEmpty()){
            Integer pop = stack.pop();
            sortStackDescending2(stack);
            insert(stack,pop);
        }
    }

    private static void insert(Stack<Integer> stack, Integer value) {
        if(stack.isEmpty() || stack.peek()<=value){
            stack.push(value);
        }else{
            Integer pop = stack.pop();
            insert(stack,value);
            stack.push(pop);
        }
    }
}
