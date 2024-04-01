package amaze;

import java.util.Stack;

public class MaxStackImpl {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> max = new Stack<>();

    public void push(int value) {
        stack.push(value);
        if (max.isEmpty() || max.peek() <= value) {
            max.push(value);
        }
    }

    public int pop() {
        Integer pop = stack.pop();
        if (max.peek().intValue() == pop.intValue()) {
            max.pop();
        }
        return pop;
    }

    public int max() {
        return max.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
