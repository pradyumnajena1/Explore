package amaze;

import java.util.Stack;

public class MinStackImpl {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> min = new Stack<>();

    public void push(int value) {
        stack.push(value);
        if (min.isEmpty() || min.peek() >= value) {
            min.push(value);
        }
    }

    public int pop() {
        Integer pop = stack.pop();
        if (min.peek().intValue() == pop.intValue()) {
            min.pop();
        }
        return pop;
    }

    public int min() {
        return min.peek();
    }
}
