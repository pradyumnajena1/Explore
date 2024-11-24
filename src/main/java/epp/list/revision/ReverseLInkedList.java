package epp.list.revision;

import epp.list.LinkedListNode;

import java.util.Arrays;

public class ReverseLInkedList {
    public static void main(String[] args) {
        LinkedListNode<Integer> linkedListNode = LinkedListNode.createList(Arrays.asList(1, 2, 3, 4, 5, 6));
        System.out.println(linkedListNode);
        LinkedListNode<Integer> reversedListNode =  reverseList(linkedListNode);
        System.out.println(reversedListNode);
    }

    public static <T> LinkedListNode<T> reverseList(LinkedListNode<T> linkedListNode) {
       LinkedListNode<T> prev = null;
       LinkedListNode<T> current = linkedListNode;

       while (current!=null){
           LinkedListNode<T> next = current.next;
           current.next = prev;
           prev = current;
           current = next;
       }
        return prev;
    }
}
