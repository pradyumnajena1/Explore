package epp.heap;

import java.util.PriorityQueue;

public class StackUsingHeap {
    private static long counter = 1;
    private static class HeapNode implements Comparable<HeapNode>{

        public int data;
        public long id;



        public HeapNode(int data) {
            this.data = data;
            this.id = counter++;
        }

        @Override
        public int compareTo(HeapNode o) {
            return Long.compare(o.id,this.id);
        }
    }
    private PriorityQueue<HeapNode> priorityQueue = new PriorityQueue<>();

    public void push(int value){
        priorityQueue.offer(new HeapNode(value));
    }
    public int pop(){
        if(priorityQueue.isEmpty()) throw new IllegalStateException("empty stack");
       return  priorityQueue.poll().data;
    }
    public int peek(){
        if(priorityQueue.isEmpty()) throw new IllegalStateException("empty stack");
        return priorityQueue.peek().data;
    }

    public static void main(String[] args) {
        StackUsingHeap stackUsingHeap = new StackUsingHeap();

        stackUsingHeap.push(1);
        stackUsingHeap.push(2);
        stackUsingHeap.push(3);
        stackUsingHeap.push(4);
        System.out.println(stackUsingHeap.pop());
    }
}
