package hackerrank.medium;

import java.util.TreeMap;

public class MedianCalculator {
    private TreeMap<Long, Long> smaller = new TreeMap<>();
    private int smallerSize = 0;
    private TreeMap<Long, Long> bigger = new TreeMap<>();
    private int biggerSize = 0;

    public void add(Long value) {
        if (smaller.isEmpty()) {
            addItem(value,smaller);
            System.out.println(this);
            return;
        }

        if (smallerSize == biggerSize) {

            if (bigger.firstKey() <= value) {
                Long firstKey = bigger.firstKey();
                removeItem(firstKey, bigger);
                addItem(firstKey, smaller);
                addItem(value, bigger);
            } else {
                addItem(value, smaller);
            }


        } else {
            if (smaller.lastKey() >= value) {

                Long lastKey = smaller.lastKey();
                removeItem(lastKey, smaller);
                addItem(lastKey, bigger);
                addItem(value, smaller);
            } else {
                addItem(value, bigger);
            }

        }
         System.out.println(this);
    }

    private void addItem(Long key, TreeMap<Long, Long> treeMap) {
        treeMap.put(key, treeMap.getOrDefault(key, 0L) + 1);
        if(treeMap==smaller){
            smallerSize++;
        }else{
            biggerSize++;
        }
    }

    private void removeItem(Long key, TreeMap<Long, Long> treeMap) {
        Long count = treeMap.get(key);
        if (count.equals(1L)) {
            treeMap.remove(key);
        } else {
            treeMap.put(key, count - 1L);
        }
        if(treeMap==smaller){
            smallerSize--;
        }else{
            biggerSize--;
        }
    }

    @Override
    public String toString() {
        return "MedianCalculator{" + "smaller=" + smaller + ", bigger=" + bigger + '}';
    }

    public boolean remove(Long value) {
        if (!smaller.containsKey(value) && !bigger.containsKey(value)) {
             System.out.println(this);
            return false;
        }
        if (smaller.containsKey(value)) {
            removeItem(value, smaller);

            if (smallerSize < biggerSize) {
                Long firstKey = bigger.firstKey();
                removeItem(firstKey, bigger);
                addItem(firstKey, smaller);
            }
        } else {


            removeItem(value, bigger);

            if (smallerSize > biggerSize + 1) {
                Long lastKey = smaller.lastKey();
                addItem(lastKey, bigger);
                removeItem(lastKey, smaller);
            }
        }
         System.out.println(this);
        return true;
    }

    public int size() {
        return smallerSize + biggerSize;
    }

    public Double getMedian() {
        if (smallerSize > biggerSize) {
            return Double.valueOf(smaller.lastKey());
        } else {
            return (double) (smaller.lastKey() + bigger.firstKey()) / 2;
        }
    }
}
