package epp.list.revision;

import epp.list.LinkedList;
import epp.list.LinkedListNode;

import java.util.Arrays;

public class MergeSortedLists {
    public static void main(String[] args) {
        LinkedList<Integer> list1 = LinkedList.createList(Arrays.asList(2, 6, 9));
        LinkedList<Integer> list2 = LinkedList.createList(Arrays.asList(1, 5, 11, 13));
        LinkedList<Integer> result = mergeSortedLists(list1, list2);
        System.out.println(result.toString());

        LinkedListNode<Integer> node = mergeSortedLists(LinkedListNode.createList(Arrays.asList(2, 6, 9)), LinkedListNode.createList(Arrays.asList(1, 5
                , 11, 13)));
        System.out.println(node);
    }

    private static LinkedList<Integer> mergeSortedLists(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        LinkedList<Integer> result = new LinkedList<>();
        while (!list1.isEmpty() && !list2.isEmpty()) {
            if (list1.peek() <= list2.peek()) {
                result.addNodeAtEnd(list1.removeNodeFromFront());
            } else {
                result.addNodeAtEnd(list2.removeNodeFromFront());
            }
        }
        if (!list1.isEmpty()) {
            while (!list1.isEmpty()) {
                result.addNodeAtEnd(list1.removeNodeFromFront());
            }
        }
        if (!list2.isEmpty()) {
            while (!list2.isEmpty()) {
                result.addNodeAtEnd(list2.removeNodeFromFront());
            }
        }
        return result;
    }

    private static LinkedListNode<Integer> mergeSortedLists(LinkedListNode<Integer> list1, LinkedListNode<Integer> list2) {
        LinkedListNode<Integer> resultHead = null;
        LinkedListNode<Integer> resultTail = null;
        LinkedListNode<Integer> temp = null;
        while (list1 != null && list2 != null) {
            if (list1.data <= list2.data) {
                temp = list1;
                list1 = list1.next;
            } else {
                temp = list2;
                list2 = list2.next;
            }
            temp.next = null;
            if (resultHead == null) {
                resultTail = resultHead = temp;
            } else {
                resultTail.next = temp;
                resultTail = temp;
            }
        }
        if (list1 != null) {
            resultTail.next = list1;
        }
        if (list2 != null) {
            resultTail.next = list2;
        }
        return resultHead;
    }
}
