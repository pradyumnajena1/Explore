package epp.hashmap;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K,V> {
    private Map<K,ValueWrapper<K,V>> map = new HashMap<>();
    private LinkedListNode<K> head;
    private LinkedListNode<K> tail;

    public LRUCache() {
    }
    public void put(K key,V value){

        LinkedListNode<K> node = new LinkedListNode<>(key);
        ValueWrapper<K, V> previous = map.put(key, new ValueWrapper<>(value, node));
        if(previous==null){

            addNode(node);
        }else{
            removeNode(previous.node);
            addNode(node);
        }
    }
    public V get(K key){
        ValueWrapper<K, V> valueWrapper = map.get(key);
        if(valueWrapper==null){
            return null;
        }

        removeNode(valueWrapper.node);
        addNode(valueWrapper.node);
        return valueWrapper.value;
    }
    public void  delete(K key){
        ValueWrapper<K, V> remove = map.remove(key);
        if(remove!=null){
            removeNode(remove.node);
        }
    }

    private void removeNode(LinkedListNode<K> node) {
        if(head==tail ){
            if(node==head){

                head=tail=null;
            }
        }else if(head==node){
            removeHead(node);
        }else if(tail==node){
            removeTail(node);
        }else{
            if(node.prev!=null){
                node.prev.next = node.next;
            }
            if(node.next!=null){
                node.next.prev = node.prev;
            }
            node.next = null;
            node.prev=null;
        }


    }

    private void removeTail(LinkedListNode<K> node) {
        tail = tail.prev;
        tail.next=null;
        node.prev=null;
    }

    private void removeHead(LinkedListNode<K> node) {
        head = head.next;
        head.prev = null;
        node.next=null;
    }

    private void addNode(LinkedListNode<K> node) {
        if(head==null){
            head = tail = node;
        }else{
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    private void printList(){
        LinkedListNode<K> current = head;
        while (current!=null){
            System.out.print(current.data+"->");
            current = current.next;
        }
        System.out.println("NULL");
    }


    private static class ValueWrapper<K,V>{
        V value;
        LinkedListNode<K> node;

        public ValueWrapper(V value, LinkedListNode<K> node) {
            this.value = value;
            this.node = node;
        }
    }

    private static class LinkedListNode<K>{
        K data;
        LinkedListNode<K> next;
        LinkedListNode<K> prev;

        public LinkedListNode(K data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        LRUCache<String ,String> cache = new LRUCache<>();
        cache.put("hello","world");
        cache.put("hello2","world2");
        cache.printList();
        cache.get("hello");
        cache.printList();
    }
}
