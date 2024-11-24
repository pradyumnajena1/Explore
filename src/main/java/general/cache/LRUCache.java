package general.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUCache<K, V> {
  private int capacity;
  private int partitions;
  private Map<Integer, Partition<K, V>> partitionsMap;

  public LRUCache(int capacity, int partitions) {
    this.capacity = capacity;
    this.partitions = partitions;
    partitionsMap = new HashMap<>();
    for (int i = 0; i < partitions; i++) {
      partitionsMap.put(i, new Partition<>(i, capacity / partitions));
    }
  }

  public static void main(String[] args) throws InterruptedException {
    LRUCache<Integer, Integer> lruCache = new LRUCache<Integer, Integer>(100, 10);
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    Runnable runnable =
        () -> {

          ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
          for (int i = 0; i < 100; i++) {
            int key = threadLocalRandom.nextInt(1000);
            lruCache.put(key, key);

            key = threadLocalRandom.nextInt(1000);
            lruCache.get(key);
          }
        };
    executorService.submit(runnable);
    executorService.submit(runnable);
    executorService.submit(runnable);
    executorService.submit(runnable);
    executorService.submit(runnable);
    executorService.shutdown();
    executorService.awaitTermination(10, TimeUnit.SECONDS);
  }

  public V get(K key) {
    int partitionIndex = Math.abs(key.hashCode()) % partitions;
    return partitionsMap.get(partitionIndex).get(key);
  }

  public V put(K key, V value) {
    int partitionIndex = Math.abs(key.hashCode()) % partitions;
    return partitionsMap.get(partitionIndex).put(key, value);
  }

  private static class Partition<K, V> {
    private LinkedHashMap<K, V> map;
    private int capacity;
    private int partitionIndex;
    private ReadWriteLock lock;

    public Partition(int partitionIndex, int capacity) {
      this.partitionIndex = partitionIndex;
      this.capacity = capacity;
      this.lock = new ReentrantReadWriteLock();
      this.map =
          new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
              boolean shdRemoved = size() > capacity;
              if (shdRemoved) {
                System.out.println("removed eldest from cache partition "+partitionIndex);
              }
              return shdRemoved;
            }
          };
    }

    public V get(K key) {
      Lock readLock = lock.readLock();
      try {
        readLock.lock();
        System.out.println("get " + key + " in " + partitionIndex);
        return map.get(key);
      } finally {
        readLock.unlock();
      }
    }

    public V put(K key, V value) {
      Lock writeLock = lock.writeLock();
      try {
        writeLock.lock();
        System.out.println("put " + key + " " + value + " in " + partitionIndex);
        return this.map.put(key, value);
      } finally {
        writeLock.unlock();
      }
    }
  }
}
