package epp.parallelcomputing;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer<T> {

    private Queue<T> queue;
    private Lock lock;
    private Condition notEmptyQueue;
    private Condition notFullQueue;
    int maxSize;

    public ProducerConsumer(int maxSize) {
        this.maxSize = maxSize;
        queue = new ArrayDeque<>();
        lock = new ReentrantLock();
        notEmptyQueue = lock.newCondition();
        notFullQueue = lock.newCondition();
    }
    public void produce(T value){
        lock.lock();
        while (queue.size()==maxSize){
            try {
                notFullQueue.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(value);
        notEmptyQueue.signal();
        lock.unlock();
    }

    public void consume(){
        lock.lock();
        while (queue.isEmpty()){

            try {
                notEmptyQueue.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        T value = queue.poll();
        notFullQueue.signal();
        lock.unlock();
        System.out.println(value);
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer<String> producerConsumer = new ProducerConsumer<>(5);
        Thread producer = new Thread(() -> {
            for (int i=0;i<100;i++) {
                producerConsumer.produce(" " + System.nanoTime());
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i=0;i<100;i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                producerConsumer.consume( );
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
