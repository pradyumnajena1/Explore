package epp.list.revision;

import epp.list.LinkedListNode;

import java.util.Arrays;
import java.util.ListIterator;

public class ReverseSublist {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println(list);
        list =   reverseSublist(list,3,7);
        System.out.println(list);

        list = LinkedListNode.createList( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        list =   reverseSublist(list,3,10);
        System.out.println(list);
    }

    private static LinkedListNode<Integer> reverseSublist(LinkedListNode<Integer> list, int start, int end) {
        if(list==null||start==end){
            return list;
        }
         LinkedListNode<Integer> dummy = new LinkedListNode<>(0,list);
        LinkedListNode<Integer> prev = dummy;
        for(int i=1;i<start;i++){
            prev = prev.next;
        }

        LinkedListNode<Integer> current = prev.next;
        LinkedListNode<Integer> next = current.next;
        for(int i=0;i<end-start;i++){

            current.next = next.next;
            next.next = prev.next;

            prev.next = next;

            next = current.next;
        }
        return dummy.next;
    }

}
