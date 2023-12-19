package epp.list.revision;

import epp.list.LinkedListNode;

import java.util.Arrays;

public class ReverseSublistKAtTime {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        System.out.println(list);
        LinkedListNode<Integer> reversedList =  reverseSublistKAtTime(list,3);
        System.out.println(reversedList);
    }

    private static LinkedListNode<Integer> reverseSublistKAtTime(LinkedListNode<Integer> list, int numNodes) {
       LinkedListNode<Integer> current  = list;
       LinkedListNode<Integer> prev  = null;
       LinkedListNode<Integer> next  = null;

       LinkedListNode<Integer> resultHead  = null;
       LinkedListNode<Integer> resultTail  = null;
       LinkedListNode<Integer> sublistHead  = null;
       LinkedListNode<Integer> sublistTail  = null;
       int count =1;

       while (current!=null){
           next = current.next;
           if(count%numNodes==1){
               sublistHead = current;
           }
           if(count%numNodes==0){
               current.next = null;
               sublistTail = sublistHead;
               sublistHead = reverseList(sublistHead);

               if(resultHead==null){
                   resultHead =   sublistHead;
                   resultTail = sublistTail;
               }else{
                   resultTail.next = sublistHead;
                   resultTail = sublistTail;
               }
               sublistHead=null;

           }

           prev = current;
           current = next;
           count++;
       }
       if(sublistHead!=null){

           resultTail.next = sublistHead;
       }


        return resultHead;
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
