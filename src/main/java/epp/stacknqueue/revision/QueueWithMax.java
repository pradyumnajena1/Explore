package epp.stacknqueue.revision;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class QueueWithMax <T extends Comparable<T>>{
    private Queue<T> queue;
    private Deque<T> maxQueue;

    public QueueWithMax() {
        queue = new ArrayDeque<>();
        maxQueue = new ArrayDeque<>();
    }

    public void enqueue(T value){
        queue.offer(value);
        while (!maxQueue.isEmpty()&& maxQueue.peekLast().compareTo(value)<0){
            maxQueue.pollLast();
        }
        maxQueue.offerLast(value);

    }
    public T dequeue(){
        if(queue.isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        T polled = queue.poll();
        if(polled.compareTo(maxQueue.peekFirst())==0){
            maxQueue.pollFirst();
        }
        return polled;
    }
    public T max(){
        if(queue.isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        return maxQueue.peekFirst();
    }

    public static void main(String[] args) {
        QueueWithMax<Integer> queueWithMax = new QueueWithMax<>();
        queueWithMax.enqueue(1);
        queueWithMax.enqueue(3);
        queueWithMax.enqueue(2);
        System.out.println(queueWithMax.max());
        queueWithMax.dequeue();
        System.out.println(queueWithMax.max());
        queueWithMax.dequeue();
        System.out.println(queueWithMax.max());
    }
}
