package epp.list;

import java.util.Arrays;

public class ReverseSingleLinkedList2 {


    public static void main(String[] args) {
        LinkedListNode<Integer> head = LinkedListNode.createList(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(head);
        LinkedListNode<Integer> newHead = reverseList(head);
        System.out.println(newHead);

    }

    public static LinkedListNode<Integer> reverseList(LinkedListNode<Integer> list) {
        if(list==null|| list.next==null){
            return list;
        }
        LinkedListNode<Integer> prev = null;
        LinkedListNode<Integer> current = list;
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
