package general.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class MFUCache<K, V> {
  private int capacity;
  private Map<K, V> cache;
  private Map<K, Integer> keyToFrequencyMap;
  private Map<Integer, LinkedHashSet<K>> frequencyToKeysMap;
  private int maxFrequency = 0;

  public MFUCache(int capacity) {
    this.capacity = capacity;
    cache = new HashMap<>();
    keyToFrequencyMap = new HashMap<>();
    frequencyToKeysMap = new HashMap<>();
    maxFrequency = 0;
  }

  public V get(K key) {
    if (cache.containsKey(key)) {
      return null;
    }

    int currentFrequency = keyToFrequencyMap.get(key);
    frequencyToKeysMap.get(currentFrequency).remove(key);
    if (frequencyToKeysMap.get(currentFrequency).isEmpty()) {
      frequencyToKeysMap.remove(currentFrequency);
      if (currentFrequency == maxFrequency) {
        maxFrequency++;
      }
    }
    frequencyToKeysMap.computeIfAbsent(currentFrequency + 1, x -> new LinkedHashSet<>()).add(key);
    keyToFrequencyMap.put(key, currentFrequency + 1);
    return cache.get(key);
  }

  public V put(K key, V value) {
    if (capacity <= 0) {
      return null;
    }
    if (cache.containsKey(key)) {
      V put = cache.put(key, value);
      cache.get(key);
      return put;
    }

    if (cache.size() >= capacity) {
      K evictedKey = frequencyToKeysMap.get(maxFrequency).iterator().next();
      cache.remove(evictedKey);
      keyToFrequencyMap.remove(evictedKey);
      frequencyToKeysMap.get(maxFrequency).remove(evictedKey);
      if (frequencyToKeysMap.get(maxFrequency).isEmpty()) {
        frequencyToKeysMap.remove(maxFrequency);
        maxFrequency--;
        while (frequencyToKeysMap.get(maxFrequency).isEmpty()) {
          maxFrequency--;
        }
      }
    }

    frequencyToKeysMap.computeIfAbsent(1, x -> new LinkedHashSet<>()).add(key);
    keyToFrequencyMap.put(key, 1);
    cache.put(key, value);
    return null;
  }
}
