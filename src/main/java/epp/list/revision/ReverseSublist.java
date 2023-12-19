package epp.list.revision;

import epp.list.LinkedListNode;

import java.util.Arrays;
import java.util.ListIterator;

public class ReverseSublist {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println(list);
        list =   reverseSublist(list,3,10);
        System.out.println(list);

        list = LinkedListNode.createList( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        list =   reverseSublist2(list,3,7);
        System.out.println(list);
    }
    private static LinkedListNode<Integer> reverseSublist2(LinkedListNode<Integer> list,int start,int end){
        LinkedListNode<Integer> current = list;
        LinkedListNode<Integer> prev = null;
        LinkedListNode<Integer> next = null;
        LinkedListNode<Integer> start_prev = null;
        LinkedListNode<Integer> start_node = null;
        int count = 1;
        while (current!=null){
            next = current.next;

            if(count==start){
                start_prev = prev;
                start_node = current;

                // break lists
                if(start_prev!=null){
                  start_prev.next = null;
                }
                current.next = null;
            }
            if(count>start && count<end){
                current.next = prev;
            }
            if(count==end){
                current.next=prev;

                //join lists
                start_node.next = next;
                if(start_prev!=null){
                    start_prev.next = current;
                }
            }

            prev = current;
            current = next;
            count++;
        }
        return start_prev!=null? list:prev;
    }

    private static LinkedListNode<Integer> reverseSublist(LinkedListNode<Integer> list, int start, int end) {
         LinkedListNode<Integer> firstHead = null;
         LinkedListNode<Integer> firstTail = null;
         for(int i=1;i<start && list!=null;i++){
             LinkedListNode<Integer>  temp = list;
             list = list.next;
             temp.next = null;
             if(firstHead==null){
                 firstHead=firstTail=temp;
             }else{
                 firstTail.next=temp;
                 firstTail = temp;
             }
         }
        LinkedListNode<Integer> middleHead = null;
        LinkedListNode<Integer> middleTail = null;
         for(int i=start;i<=end;i++){
             LinkedListNode<Integer>  temp = list;
             list = list.next;

             temp.next = null;
             if(middleHead==null){
                 middleHead=middleTail=temp;
             }else{
                 middleTail.next=temp;
                 middleTail = temp;
             }
         }
         middleTail = middleHead;
        middleHead = reverseList(middleHead);
        if(firstTail!=null){

            firstTail.next = middleHead;
        }
        middleTail.next=list;
        return firstHead!=null?firstHead:middleHead;
    }
    private static LinkedListNode<Integer> reverseList(LinkedListNode<Integer> linkedListNode) {
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
