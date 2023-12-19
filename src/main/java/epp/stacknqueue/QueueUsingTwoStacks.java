package epp.stacknqueue;

import java.util.Stack;

public class QueueUsingTwoStacks {
    private static class Queue2StackImpl{
        private Stack<Integer> stackA = new Stack<>();
        private Stack<Integer> stackB = new Stack<>();

        public Queue2StackImpl() {
        }

        public void enqueue(int value){

            stackA.push(value);
        }
        public int dequeue(){
            if(!stackB.isEmpty()){
                return stackB.pop();
            }
            if(stackA.isEmpty()){
                throw new IllegalStateException("empty queue");
            }
            while (!stackA.isEmpty()){
                stackB.push(stackA.pop());
            }
            return stackB.pop();
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Queue2StackImpl{");
            sb.append("stackA=").append(stackA);
            sb.append(", stackB=").append(stackB);
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Queue2StackImpl queue2Stack = new Queue2StackImpl();
        queue2Stack.enqueue(1);
        queue2Stack.enqueue(2);
        queue2Stack.enqueue(3);
        queue2Stack.enqueue(4);
        queue2Stack.enqueue(5);
        System.out.println(queue2Stack);
        queue2Stack.dequeue();
        System.out.println(queue2Stack);
        queue2Stack.enqueue(6);
        queue2Stack.enqueue(7);
        queue2Stack.enqueue(8);
        queue2Stack.dequeue();
        System.out.println(queue2Stack);
        queue2Stack.dequeue();
        queue2Stack.dequeue();
        queue2Stack.dequeue();
        queue2Stack.dequeue();
        System.out.println(queue2Stack);
    }
}
