package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BoundedBufferBasic<T> {
    int count = 0;
    private int maxSize;
    private T[] storage;
    private int readPosition;
    private int writePosition;

    public BoundedBufferBasic(int maxSize) {
        this.maxSize = maxSize;

        storage = (T[]) new Object[maxSize];
        readPosition = 0;
        writePosition = 0;
        count = 0;
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedBufferBasic<Integer> boundedBuffer = new BoundedBufferBasic<>(5);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            while (true) {
                try {
                    boundedBuffer.addItem((int) (Math.random() * 10));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        executorService.submit(() -> {
            while (true) {
                try {
                    Integer item = boundedBuffer.getItem();
                    System.out.println(item);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

    }

    public synchronized void addItem(T value) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        boolean wasEmpty = isEmpty();
        putItem(value);
        if (wasEmpty) {

            notifyAll();
        }
    }

    public synchronized T getItem() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        boolean wasFull = isFull();

        T item = extractItem();
        if (wasFull) {

            notifyAll();
        }
        return item;
    }

    public synchronized boolean isEmpty() {
        return count == 0;
    }

    public synchronized boolean isFull() {
        return count == maxSize;
    }

    private synchronized void putItem(T value) {

        storage[writePosition] = value;
        writePosition = (writePosition + 1) % maxSize;
        count++;

    }

    private synchronized T extractItem() {
        T item;
        item = storage[readPosition];
        storage[readPosition] = null;
        readPosition = (readPosition + 1) % maxSize;
        count--;
        return item;
    }
}
