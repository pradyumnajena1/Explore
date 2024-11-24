package clr.heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class QueueUsingHeap<T> {
    private PriorityQueue<PriorityQueueNode> priorityQueue ;
    private   long timestamp = 0;

    public QueueUsingHeap( ) {
        priorityQueue = new PriorityQueue<PriorityQueueNode>( Comparator.naturalOrder());
    }
    public static void main(String[] args){
        QueueUsingHeap<String> queue = new QueueUsingHeap<String>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue("D");
        System.out.println(queue.peek());
        queue.enqueue("E");
        System.out.println(queue.dequeue());
    }

    private T peek() {
        if(isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        PriorityQueueNode<T> node = priorityQueue.peek();
        return node.value;
    }

    private T dequeue() {
        if(isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        PriorityQueueNode<T> node = priorityQueue.poll();
        return node.value;
    }

    private boolean isEmpty() {
        return priorityQueue.size() == 0;
    }

    public void enqueue(T a) {
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
