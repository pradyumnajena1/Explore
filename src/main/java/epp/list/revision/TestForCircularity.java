package epp.list.revision;

import epp.list.LinkedList;
import epp.list.LinkedListNode;

import java.util.Arrays;

public class TestForCircularity {
    public static void main(String[] args) {
        LinkedListNode<Integer> head = LinkedListNode.createList( 1,2,3,4,5,6,7,8,9);
        System.out.println(head);
        LinkedListNode<Integer> tail = LinkedListNode.getNode(head, 9);
        LinkedListNode<Integer> node5 = LinkedListNode.getNode(head, 5);
        tail.next = node5;
        LinkedListNode<Integer> startOfCircle =  findStartOfCircle(head);
        System.out.println(startOfCircle==null?null:startOfCircle.data);


    }

    public static <T> LinkedListNode<T> findStartOfCircle(LinkedListNode<T> list) {
       LinkedListNode<T> fast = list;
       LinkedListNode<T> slow = list;
      do{
           fast = fast.next;
           if(fast!=null){
               fast = fast.next;
               slow = slow.next;
           }
       } while (fast!=null && fast!=slow);

       if(fast==null){
           return null;
       }
       slow = list;
       while (fast!=slow){
           fast=fast.next;
           slow=slow.next;
       }

        return fast;
    }
}
