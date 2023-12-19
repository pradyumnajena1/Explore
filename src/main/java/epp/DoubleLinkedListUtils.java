package epp;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class DoubleLinkedListUtils {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomSortedArray(11, 1, 10);
        DoubleLinkedList<Integer> list = createDoubleLinkedList(Arrays.stream(values).boxed().toArray(Integer[]::new));
        System.out.println(list);
        DoubleLinkedList<Integer>[] lists = DoubleLinkedList.splitInHalf(list);
        System.out.println(lists[0]);
        System.out.println(lists[1]);
    }

    public static <T> int getLength(DoubleLinkedListNode<T> list1) {
        int size = 0;
        DoubleLinkedListNode<T> current = list1;
        while (current != null) {
            current = current.getNext();
            size++;
        }
        return size;
    }


    public static <T> DoubleLinkedListNode<T> getNode(DoubleLinkedListNode<T> list, T data) {
        DoubleLinkedListNode<T> current = list;
        while (current != null && !current.getData().equals(data)) {
            current = current.getNext();
        }
        return current;
    }

    public static <T> DoubleLinkedList<T> createDoubleLinkedList(T[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        DoubleLinkedListNode<T> head, tail;

        head = tail = new DoubleLinkedListNode<>(values[0]);
        for (int i = 1; i < values.length; i++) {
            DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(values[i]);
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }

        return new DoubleLinkedList<>(head, tail);
    }
}
