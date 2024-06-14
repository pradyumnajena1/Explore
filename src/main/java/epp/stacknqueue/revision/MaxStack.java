package epp.stacknqueue.revision;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class MaxStack<T extends Comparable<T>> {

    public static void main(String[] args) {
        MaxStack<Integer> maxStack = new MaxStack<>();
        maxStack.push(3);
        maxStack.push(1);
        maxStack.push(4);
        System.out.println(maxStack.max());
        maxStack.pop();
        System.out.println(maxStack.max());
    }

    private Deque<MaxStackNode<T>> stack;

    public MaxStack(){
    stack = new LinkedList<>();
    }
    public void push(T data){

        if(stack.isEmpty()){
            stack.push(new MaxStackNode<>(data,data));
        }else{
            T currentMax = stack.peek().max;
            MaxStackNode<T> maxStackNode = new MaxStackNode<>(data, data.compareTo(currentMax) > 0 ? data : currentMax);
            stack.push(maxStackNode);
        }
    }
    public T pop(){
        if(stack.isEmpty()){
            throw new IllegalStateException("empty stack");
        }
        MaxStackNode<T> pop = stack.pop();
        return pop.data;
    }

    public T peek(){
        if(stack.isEmpty()){
            throw new IllegalStateException("empty stack");
        }
        return stack.peek().data;
    }

    public T max(){
        if(stack.isEmpty()){
            throw new IllegalStateException("empty stack");
        }
        return stack.peek().max;
    }
    private static class MaxStackNode<T extends Comparable<T>>{
        private T data;
        private T max;
        MaxStackNode(T data,T max){
            this.data=data;
            this.max=max;
        }
    }
}
