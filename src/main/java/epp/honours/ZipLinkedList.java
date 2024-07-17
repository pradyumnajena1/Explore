package epp.honours;

import epp.list.LinkedListNode;

public class ZipLinkedList {
    public static void main(String[] args){
        LinkedListNode<Integer> list = LinkedListNode.createList(1,2,3,4,5,6,7);


        LinkedListNode<Integer> result = zipList(list);
        System.out.println(result);
    }

    private static LinkedListNode<Integer> zipList(LinkedListNode<Integer> list) {
        if(list==null || list.next==null) {
            return list;
        }
        LinkedListNode<Integer> slow = list;
        LinkedListNode<Integer> fast = list;
        while(fast!=null && fast.next!=null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        LinkedListNode<Integer> secondHalfHead = slow.next;
        slow.next = null;
        LinkedListNode<Integer> firstHalfHead = list;
        secondHalfHead = reverseList(secondHalfHead);

        LinkedListNode<Integer> secondListIter = secondHalfHead;
        LinkedListNode<Integer> firstListIter = firstHalfHead;

        while (secondListIter!=null) {
            LinkedListNode<Integer> temp = secondListIter.next;

            secondListIter.next = firstListIter.next;
            firstListIter.next = secondListIter;

            secondListIter = temp;
            firstListIter = firstListIter.next.next;
        }


        return list;
    }

    private static LinkedListNode<Integer> reverseList(LinkedListNode<Integer> list) {
        if(list==null||list.next==null){
            return list;
        }
        LinkedListNode<Integer> current = list;
        LinkedListNode<Integer> prev = null;
        while (current!=null){
            LinkedListNode<Integer> next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}
