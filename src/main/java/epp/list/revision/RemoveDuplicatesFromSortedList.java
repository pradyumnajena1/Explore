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
        if(list==null || list.next==null){
            return;
        }
        LinkedListNode<Integer> prev = list;
        LinkedListNode<Integer> current = list.next;
        LinkedListNode<Integer> next = null;
        while (current!=null){
            next = current.next;
            if(current.data==prev.data){
                //delete current node
                prev.next = current.next;
                current.next = null;

            }else{
                prev = current;
            }
            current=next;
        }

    }
}
