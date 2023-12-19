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

    public static LinkedListNode<Integer> reverseList(LinkedListNode<Integer> linkedListNode) {
       LinkedListNode<Integer> prev = null;
       LinkedListNode<Integer> current = linkedListNode;
       LinkedListNode<Integer> next = null;
       while (current!=null){
           next = current.next;
           current.next = prev;
           prev = current;
           current = next;
       }
        return prev;
    }
}
