package epp.stacknqueue;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueWithMax {

    private Queue<Integer> queue;
    private ArrayDeque<Integer> maxCandidates;

    public QueueWithMax() {
        queue = new ArrayDeque<>();
        maxCandidates = new ArrayDeque<>();
    }

    public void enqueue(int value){
        queue.offer(value);
        while (!maxCandidates.isEmpty()&& maxCandidates.peekLast()<value){
            maxCandidates.removeLast();
        }
        maxCandidates.offerLast(value);
    }
    public int deque(){
        if(queue.isEmpty()){
            throw new IllegalStateException("empty queue");
        }
        Integer value = queue.poll();
        if(value==maxCandidates.peekFirst()){
            maxCandidates.pollFirst();
        }
        return value;
    }

    public int max(){
        if(queue.isEmpty()){
            throw new IllegalStateException("empty queue");
        }
        return maxCandidates.peekFirst();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("QueueWithMax{");
        sb.append("queue=").append(queue);
        sb.append(", maxCandidates=").append(maxCandidates);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        QueueWithMax  queueWithMax = new QueueWithMax();
        queueWithMax.enqueue(2);
        queueWithMax.enqueue(7);
        System.out.println(queueWithMax);
        queueWithMax.enqueue(9);
        System.out.println(queueWithMax);
        queueWithMax.enqueue(5);
        System.out.println(queueWithMax);
        queueWithMax.enqueue(17);
        queueWithMax.enqueue(9);
        System.out.println(queueWithMax);
    }
}
