package epp.stacknqueue.revision;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class QueueWithMax <T extends Comparable<T>>{
    private Queue<T> entriesQueue;
    private Deque<T> candidatesForMax;

    public QueueWithMax() {
        entriesQueue = new ArrayDeque<>();
        candidatesForMax = new ArrayDeque<>();
    }

    public void enqueue(T value){
        entriesQueue.offer(value);
        while (!candidatesForMax.isEmpty()&& candidatesForMax.peekLast().compareTo(value)<0){
            candidatesForMax.pollLast();
        }
        candidatesForMax.offerLast(value);

    }
    public T dequeue(){
        if(entriesQueue.isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        T polled = entriesQueue.poll();
        if(polled.compareTo(candidatesForMax.peek())==0){
            candidatesForMax.poll();
        }
        return polled;
    }
    public T max(){
        if(entriesQueue.isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        return candidatesForMax.peek();
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
