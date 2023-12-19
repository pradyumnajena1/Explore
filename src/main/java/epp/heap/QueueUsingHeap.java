package epp.heap;

import java.util.PriorityQueue;

public class QueueUsingHeap {
    private static long counter = 1;
    private static class HeapNode implements Comparable<QueueUsingHeap.HeapNode>{

        public int data;
        public long id;



        public HeapNode(int data) {
            this.data = data;
            this.id = counter++;
        }

        @Override
        public int compareTo(QueueUsingHeap.HeapNode o) {
            return Long.compare( this.id,o.id);
        }
    }
    private PriorityQueue< HeapNode> priorityQueue = new PriorityQueue<>();
    public void enqueue(int x){
        priorityQueue.add(new HeapNode(x));
    }
    public int deque(){
       return priorityQueue.poll().data;
    }
    public int head(){
        return priorityQueue.peek().data;
    }

    public static void main(String[] args) {
        QueueUsingHeap queueUsingHeap = new QueueUsingHeap();
        queueUsingHeap.enqueue(1);
        queueUsingHeap.enqueue(2);
        queueUsingHeap.enqueue(3);
        System.out.println( queueUsingHeap.deque());
    }
}
