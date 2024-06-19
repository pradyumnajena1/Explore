package epp.heap.revision;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.PriorityQueue;

public class QueueUsingHeap<T> {
    public static void main(String[] args) {
        QueueUsingHeap<String> queueUsingHeap = new QueueUsingHeap<>();
         queueUsingHeap.offer("hello");
         queueUsingHeap.offer("world");
         queueUsingHeap.offer("there");
        System.out.println(queueUsingHeap.poll());
        System.out.println(queueUsingHeap.poll());
        queueUsingHeap.offer("test");
        System.out.println(queueUsingHeap.peek());
        queueUsingHeap.poll();
        System.out.println(queueUsingHeap.peek());
    }
    private PriorityQueue<ValueWithRank<T>> priorityQueue;
    private BigInteger recordNumber = BigInteger.ZERO;

    public QueueUsingHeap() {
        this.priorityQueue = new PriorityQueue<>( );
    }

    public void offer(T value){
        recordNumber = recordNumber.add(BigInteger.ONE);
        priorityQueue.offer(new ValueWithRank<>(value,recordNumber));
    }
    public T peek(){
        if(priorityQueue.isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        return priorityQueue.peek().value;
    }
    public T poll(){
        if(priorityQueue.isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        return priorityQueue.poll().value;
    }

    public static class ValueWithRank<T> implements Comparable<ValueWithRank>{
        private T value;
        private BigInteger recordNumber;

        public ValueWithRank(T value, BigInteger recordNumber) {
            this.value = value;
            this.recordNumber = recordNumber;
        }

        public T getValue() {
            return value;
        }

        @Override
        public int compareTo(ValueWithRank o) {
            return recordNumber.compareTo(o.recordNumber);
        }
    }
}
