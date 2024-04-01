package concurrency;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BoundedHashSet<T> {

    private final Set<T> set;
    private final int maxSize;
    private final Semaphore semaphore;

    public BoundedHashSet(int maxSize) {
        this.maxSize = maxSize;
        set = Collections.synchronizedSet(new HashSet<>());
        semaphore = new Semaphore(maxSize);
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedHashSet<Integer> boundedHashSet = new BoundedHashSet<>(5);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> {
            try {
                for (int i = 0; i < 50; i++) {

                    boundedHashSet.add(Integer.valueOf((int) (Math.random() * 10)));
                    Thread.sleep(100);
                }
                System.out.println("added done");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        executorService.submit(() -> {

            try {
                for (int i = 0; i < 100; i++) {
                    boundedHashSet.remove(Integer.valueOf((int) (Math.random() * 10)));
                    Thread.sleep(100);
                }
                System.out.println("remover done");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        executorService.submit(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    System.out.println(boundedHashSet.set);

                    Thread.sleep(100);
                }
                System.out.println("printer done");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
       executorService.shutdown();
    }

    public boolean add(T value) throws InterruptedException {
        semaphore.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(value);
            return wasAdded;
        } finally {
            if (!wasAdded) {
                semaphore.release();
            }
        }

    }

    public boolean remove(T value) {
        boolean wasRemoved = set.remove(value);
        if (wasRemoved) {
            semaphore.release();
        }
        return wasRemoved;
    }
}
