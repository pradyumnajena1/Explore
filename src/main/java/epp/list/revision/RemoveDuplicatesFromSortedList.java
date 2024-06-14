package epp.list.revision;

import epp.list.LinkedListNode;

public class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(1,1,1,2,3,3,4,5,6,7,7,8,9,10,10);
        System.out.println(list);
        removeDuplicatesFromSortedList(list);
        System.out.println(list);
    }

    private static void removeDuplicatesFromSortedList(LinkedListNode<Integer> list) {
        LinkedListNode<Integer> iter = list;
        while (iter!=null){
            LinkedListNode<Integer> nextDistinct = iter.next;
            while (nextDistinct!=null && nextDistinct.data == iter.data){
                nextDistinct = nextDistinct.next;
            }
            iter.next = nextDistinct;
            iter = nextDistinct;
        }

    }
}
