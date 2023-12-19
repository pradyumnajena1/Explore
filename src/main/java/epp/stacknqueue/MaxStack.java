package epp.stacknqueue;

import java.util.Stack;

public class MaxStack {

    private static class MaxStackNode{
          int data;
          int max;

        public MaxStackNode(int data, int max) {
            this.data = data;
            this.max = max;
        }

        @Override
        public String toString() {
            return "MaxStackNode{" +
                    "data=" + data +
                    ", max=" + max +
                    '}';
        }
    }

    private Stack<MaxStackNode> stack;

    public MaxStack() {
        stack = new Stack<>();
    }

    public void push(int value){
        if(stack.size()==0){
            stack.push(new MaxStackNode(value,value));
        }else{
            stack.push(new MaxStackNode(value,Math.max(value,stack.peek().max)));
        }
    }
    public int pop(){
        return stack.pop().data;
    }
    public int max(){
        return stack.peek().max;
    }

    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();
        maxStack.push(3);
        maxStack.push(7);
        maxStack.push(2);
        maxStack.push(5);
        maxStack.push(9);
        System.out.println(maxStack.max());
        maxStack.pop();
        System.out.println(maxStack.max());

    }
}
