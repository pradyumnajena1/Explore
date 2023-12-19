package epp.stacknqueue;

import java.util.Stack;

public class SortStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(7);
        stack.push(2);
        stack.push(17);
        System.out.println(sortStack(stack));
    }

    private static Stack<Integer> sortStack(Stack<Integer> stack) {
        Stack<Integer> buffer = new Stack<>();
        while (!stack.isEmpty()) {

            int currentValue = stack.pop();

            while (!buffer.isEmpty() && buffer.peek().compareTo(currentValue) > 0) {
                stack.push(buffer.pop());
            }
            buffer.push(currentValue);

        }
        return buffer;
    }
}
