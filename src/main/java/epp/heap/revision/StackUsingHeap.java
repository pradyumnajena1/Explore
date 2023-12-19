package epp.heap.revision;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.PriorityQueue;

public class StackUsingHeap<T> {
    public static void main(String[] args) {
        StackUsingHeap<String> stackUsingHeap = new StackUsingHeap<>();
        stackUsingHeap.push("hello");
        stackUsingHeap.push("world");
        stackUsingHeap.push("there");
        System.out.println(stackUsingHeap.pop());
        System.out.println(stackUsingHeap.pop());
        stackUsingHeap.push("test");
        System.out.println(stackUsingHeap.peek());
        stackUsingHeap.pop();
        System.out.println(stackUsingHeap.peek());
    }
    private PriorityQueue<QueueUsingHeap.Record<T>> maxHeap;
    private BigInteger recordNumber = BigInteger.ZERO;

    public StackUsingHeap() {
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void push(T value){
        recordNumber = recordNumber.add(BigInteger.ONE);
        maxHeap.offer(new QueueUsingHeap.Record<>(value,recordNumber));
    }
    public T pop(){
        if(maxHeap.isEmpty()){
            throw new IllegalStateException("Empty stack");
        }
        return maxHeap.poll().getValue();
    }
    public T peek(){
        if(maxHeap.isEmpty()){
            throw new IllegalStateException("Empty stack");
        }
        return maxHeap.peek().getValue();
    }
}
