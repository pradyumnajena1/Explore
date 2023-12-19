package epp.hashmap.revision;

import epp.DoubleLinkedList;
import epp.DoubleLinkedListNode;

import java.util.*;

public class LRUCache<K, V> {

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(5);
        cache.put(1, "hello1");
        cache.put(2, "hello2");
        cache.put(3, "hello3");
        cache.put(4, "hello4");
        cache.put(5, "hello5");
        System.out.println(cache);
        cache.get(2);
        System.out.println(cache);

        cache.get(4);
        System.out.println(cache);
        cache.put(6, "hello6");
        System.out.println(cache);
        System.out.println(cache.remove(3));
        System.out.println(cache);
    }

    private int size;
    private DoubleLinkedList<KeyValuePair<K, V>> list;
    private Map<K, DoubleLinkedListNode<KeyValuePair<K, V>>> map;

    public LRUCache(int size) {
        this.size = size;
        this.map = new HashMap<>();
        this.list=new DoubleLinkedList<>();
    }

    public V put(K key, V value) {
        if (map.containsKey(key)) {
            DoubleLinkedListNode<KeyValuePair<K, V>> linkedListNode = map.get(key);
            list.removeNode(linkedListNode);
            V oldValue = linkedListNode.getData().value;
            linkedListNode.getData().value = value;
            list.addAtEnd(linkedListNode);
            return oldValue;

        } else {
            DoubleLinkedListNode<KeyValuePair<K, V>> node = new DoubleLinkedListNode<>(new KeyValuePair<>(key, value));
            list.addAtEnd(node);
            map.put(key, node);
            if (map.size() > size) {
                remove(list.getFirst().key);
            }
            return null;
        }
    }


    public V remove(K key) {
        if (!map.containsKey(key)) {
            return null;
        } else {
            DoubleLinkedListNode<KeyValuePair<K, V>> node = map.remove(key);
            list.removeNode(node);
            return node.getData().value;
        }
    }

    public V get(K key) {
        if (map.containsKey(key)) {
            DoubleLinkedListNode<KeyValuePair<K, V>> node = map.get(key);
            list.removeNode(node);
            list.addAtEnd(node);
            return node.getData().value;
        }
        return null;

    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", LRUCache.class.getSimpleName() + "[", "]");
        stringJoiner.add(map.toString());
        stringJoiner.add(Arrays.toString(list.toArray()));
        return stringJoiner.toString();
    }


    private static class KeyValuePair<K, V> {
        private K key;
        private V value;

        public KeyValuePair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", KeyValuePair.class.getSimpleName() + "[", "]").add("key=" + key).add("value=" + value).toString();
        }
    }
}
