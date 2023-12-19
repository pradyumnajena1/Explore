package epp.stacknqueue;

import java.util.Stack;

public class MaxStack2<T extends Comparable<T>> {
    private Stack<T> stack;
    private Stack<T> maxStack;

    public MaxStack2() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(T value){
        stack.push(value);
        if(maxStack.isEmpty()){
            maxStack.push(value);
        }else if(maxStack.peek().compareTo(value)<=0){
            maxStack.push(value);
        }
    }

    public T pop(){
        if(stack.isEmpty()){
            throw new IllegalStateException("empty stack");
        }
        T pop = stack.pop();
        if(maxStack.peek().compareTo(pop)<=0){
            maxStack.pop();
        }
        return pop;
    }

    public T peek(){
        if(stack.isEmpty()){
            throw new IllegalStateException("empty stack");
        }
        T peek = stack.peek();
        return peek;
    }

    public T max(){
        if(stack.isEmpty()){
            throw new IllegalStateException("empty stack");
        }
        return maxStack.peek();
    }

    public static void main(String[] args) {
        MaxStack2<Integer> maxStack2 = new MaxStack2<>();
        maxStack2.push(5);
        maxStack2.push(7);
        maxStack2.push(3);
        maxStack2.push(2);
        maxStack2.push(9);
        maxStack2.push(9);
        maxStack2.push(1);
        System.out.println(maxStack2.max());
        maxStack2.pop();
        maxStack2.pop();
        System.out.println(maxStack2.max());
        maxStack2.pop();
        System.out.println(maxStack2.max());

    }
}
