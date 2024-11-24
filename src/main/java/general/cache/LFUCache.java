package general.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache<K, V> {
  private int capacity;
  private Map<K, V> cache;
  private Map<K, Integer> keyToFrequencyMap;
  private Map<Integer, LinkedHashSet<K>> frequencyToKeysMap;
  private int minFrequency;

  public LFUCache(int capacity) {
    this.capacity = capacity;
    cache = new HashMap<>();
    keyToFrequencyMap = new HashMap<>();
    frequencyToKeysMap = new HashMap<>();
    minFrequency = 0;
  }

  public V get(K key) {
    if (!cache.containsKey(key)) {
      return null;
    }
    Integer currentFrequency = keyToFrequencyMap.get(key);
    frequencyToKeysMap.get(currentFrequency).remove(key);

    if (frequencyToKeysMap.get(currentFrequency).isEmpty()) {
      frequencyToKeysMap.remove(currentFrequency);
      if (currentFrequency == minFrequency) {
        minFrequency++;
      }
    }
    keyToFrequencyMap.put(key, currentFrequency + 1);
    frequencyToKeysMap.computeIfAbsent(currentFrequency + 1, x -> new LinkedHashSet<>()).add(key);
    return cache.get(key);
  }

  public V put(K key, V value) {
    if (capacity <= 0) {
      return null;
    }
    if (cache.containsKey(key)) {
      cache.put(key, value);
      return get(key);
    }

    if (cache.size() >= capacity) {
      LinkedHashSet<K> leastFrequentKeys = frequencyToKeysMap.get(minFrequency);
      K evictKey = leastFrequentKeys.iterator().next();
      leastFrequentKeys.remove(evictKey);
      if (leastFrequentKeys.isEmpty()) {
        frequencyToKeysMap.remove(minFrequency);
      }
      cache.remove(evictKey);
      keyToFrequencyMap.remove(evictKey);
    }
    cache.put(key, value);
    keyToFrequencyMap.put(key, 1);
    frequencyToKeysMap.computeIfAbsent(1, x -> new LinkedHashSet<>()).add(key);
    minFrequency = 1;
    return null;
  }
}
