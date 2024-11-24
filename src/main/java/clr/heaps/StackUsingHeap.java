package clr.heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StackUsingHeap<T> {

    private PriorityQueue<PriorityQueueNode> priorityQueue ;
    private   long timestamp = 0;

    public StackUsingHeap( ) {
        priorityQueue = new PriorityQueue<PriorityQueueNode>( Comparator.reverseOrder());
    }
    public static void main(String[] args){
        StackUsingHeap<String> stack = new StackUsingHeap<String>();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push("D");
        System.out.println(stack.top());
        stack.push("E");
        System.out.println(stack.pop());
    }

    private T top() {
        if(isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        PriorityQueueNode<T> node = priorityQueue.peek();
        return node.value;
    }

    private T pop() {
        if(isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        PriorityQueueNode<T> node = priorityQueue.poll();
        return node.value;
    }

    private boolean isEmpty() {
        return priorityQueue.size() == 0;
    }

    public void push(T a) {
        priorityQueue.add(new PriorityQueueNode<>(a, ++timestamp));

    }

    private static class PriorityQueueNode<T> implements Comparable<PriorityQueueNode<T>> {
        T value;
        long timestamp;

        public PriorityQueueNode(T value, long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }

        @Override
        public int compareTo(PriorityQueueNode<T> o) {
            return Long.compare(timestamp, o.timestamp);
        }
    }
}
