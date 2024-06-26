package epp.stacknqueue.revision;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * implement circular queue using array
 */
public class CircularQueue<T> {
    public static final int SCALE_FACTOR = 2;
    private Object[] values;
     private int head = 0;
     private int tail = 0;
     private int size = 0;
     public CircularQueue(int capacity){
         values = new Object[capacity];
     }
     public void enqueue(T value){
         if(size==values.length){
             Collections.rotate(Arrays.asList(values), -head );
             head=0;
             tail=size;
             values = Arrays.copyOf(values,size* SCALE_FACTOR);
         }
         values[tail] = value;
         tail = (tail+1)% values.length;
         size++;
     }
     public T deque(){

         if(size>0){
             T value = (T) values[head];
             values[head] = null;
             head = (head+1)% values.length;
             size--;
             return value;
         }
         throw new IllegalStateException("Empty stack");
     }

    @Override
    public String toString() {
         StringBuilder sb = new StringBuilder();
         sb.append("Circular Queue{");
        sb.append(" capacity = "+values.length);
        sb.append(" size = "+size);
        sb.append(" head = "+head);
        sb.append(" tail = "+tail);
        sb.append(" values = "+ Arrays.toString(values));
         sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        CircularQueue<Integer> circularQueue = new CircularQueue<>(5);
        circularQueue.enqueue(1);
        circularQueue.enqueue(SCALE_FACTOR);
        circularQueue.enqueue(3);
        circularQueue.enqueue(4);
        circularQueue.enqueue(5);
        System.out.println(circularQueue);
        circularQueue.deque();
        circularQueue.enqueue(6);
        System.out.println(circularQueue);
        circularQueue.enqueue(7);
        System.out.println(circularQueue);
        circularQueue.deque();
        System.out.println(circularQueue);
    }
}
