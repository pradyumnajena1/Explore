package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BoundedBuffer<T> {
    private int maxSize;
    private Semaphore availableItems;
    private Semaphore availableEmptySpaces;
    private T[] storage;
    private int readPosition;
    private int writePosition;

    public BoundedBuffer(int maxSize) {
        this.maxSize = maxSize;
        availableItems = new Semaphore(0);
        availableEmptySpaces = new Semaphore(maxSize);
        storage = (T[]) new Object[maxSize];
        readPosition=0;
        writePosition=0;
    }

    public   boolean isEmpty(){
        return availableItems.availablePermits() == 0;
    }
    public   boolean isFull(){
        return availableEmptySpaces.availablePermits() == 0;
    }

    public void addItem(T value) throws InterruptedException {
        availableEmptySpaces.acquire();
        putItem(value);
        availableItems.release();
    }
    public T getItem() throws InterruptedException {
        availableItems.acquire();
        T item = extractItem();
        availableEmptySpaces.release();
        return item;
    }
    private synchronized void putItem(T value) {

        storage[writePosition] = value;
        writePosition = (writePosition + 1) % maxSize;

    }
    private synchronized T extractItem() {
        T item;
        item = storage[readPosition];
        storage[readPosition] = null;
        readPosition = (readPosition + 1) % maxSize;
        return item;
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<>(5);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()->{
            while (true) {
                try {
                    boundedBuffer.addItem((int) (Math.random()*10));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        executorService.submit(()->{
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
}
