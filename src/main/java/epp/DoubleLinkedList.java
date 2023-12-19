package epp;

import java.util.StringJoiner;

public class DoubleLinkedList<T> {
    public static void main(String[] args) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        DoubleLinkedListNode<Integer> node1 = new DoubleLinkedListNode<>(1);
        list.addAtEnd(node1);

        DoubleLinkedListNode<Integer> node2 = new DoubleLinkedListNode<>(2);
        list.addAtEnd(node2);

        DoubleLinkedListNode<Integer> node3 = new DoubleLinkedListNode<>(3);
        list.addAtEnd(node3);
        System.out.println(list);
        list.removeNode(node2);
        System.out.println(list);
        list.addAtEnd(node2);
        System.out.println(list);


    }
    private DoubleLinkedListNode<T> first;
    private DoubleLinkedListNode<T> last;
    private int size = 0;

    public DoubleLinkedList() {
    }

    public DoubleLinkedList(DoubleLinkedListNode<T> first, DoubleLinkedListNode<T> last) {
        this.first = first;
        this.first.setPrev(null);
        this.last = last;
        this.last.setNext(null);
        this.size = DoubleLinkedListUtils.getLength(first);
    }

    public void removeNode(DoubleLinkedListNode<T> node) {
        DoubleLinkedListNode<T> prev = node.getPrev();
        DoubleLinkedListNode<T> next = node.getNext();
        if (prev != null) {
            prev.setNext(next);
            node.setPrev(null);
        } else {
            first = next;
        }

        if (next != null) {
            next.setPrev(prev);
            node.setNext(null);
        } else {
            last = prev;
        }
        size--;
    }
    public DoubleLinkedListNode<T> removeNodeFromFirst( ) {
        if(first==null){
            return null;
        }
        DoubleLinkedListNode<T> temp = first;
        removeNode(first);
        return temp;

    }

        public void addAtEnd(DoubleLinkedListNode<T> node) {
        if (first == null) {
            first = last = node;
        } else {
            last.setNext(node);
            node.setPrev(last);
            last = node;
        }
        size++;
    }

    public T getFirst() {
        return first.getData();
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (DoubleLinkedListNode<T> x = first; x != null; x = x.getNext()) {

            result[i++] = x.getData();
        }
        return result;
    }

    public static <T> DoubleLinkedList<T>[] splitInHalf(DoubleLinkedList<T> list){
        DoubleLinkedListNode<T> fastPtr = list.first;
        DoubleLinkedListNode<T> slowPtr = list.first;

        while (fastPtr.getNext()!=null){
            fastPtr = fastPtr.getNext();
            if(fastPtr.getNext()!=null){
                slowPtr = slowPtr.getNext();
                fastPtr = fastPtr.getNext();
            }
        }


        DoubleLinkedListNode<T> temp = slowPtr.getNext();
        slowPtr.setNext(  null);
        temp.setPrev(null);

        DoubleLinkedList first = new DoubleLinkedList(list.first, slowPtr);
        DoubleLinkedList second = new DoubleLinkedList(temp, list.last);
        return new DoubleLinkedList[]{first, second};
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DoubleLinkedList.class.getSimpleName() + "[", "]")
                .add("size=" + size)
                .add("list=" + first)
                .toString();
    }

    public int getSize() {
        return size;
    }
}
