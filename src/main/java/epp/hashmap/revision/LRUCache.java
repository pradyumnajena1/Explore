package epp.hashmap.revision;

import java.util.*;

public class LRUCache {

  private int capacity;
  private LinkedHashMap<String, Integer> map;

  public LRUCache(int capacity) {
    this.capacity = capacity;
    this.map =
        new LinkedHashMap<>(capacity, 1.0f, true) {
          @Override
          protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
            return this.size() > capacity;
          }
        };
  }

  public static void main(String[] args) {}

  public Integer lookUp(String name) {
    if (!map.containsKey(name)) {
      return null;
    }
    return map.get(name);
  }

  public Integer insert(String key, Integer value) {
    // We add the value for key only if key is not present - we don’t update
    // existing values.
    Integer currentValue = map.get(key);
    if (!map.containsKey(key)) {
      map.put(key, value);
      return currentValue;
    } else {
      return null;
    }
  }

  public Integer erase(String key) {
    return map.remove(key);
  }
}
