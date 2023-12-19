package epp.list.revision;

import epp.list.LinkedListNode;

public class SortLinkedListUsingInsertionSort {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(3,2,2,11,7,5,11);
        System.out.println(list);
        list =  sortList(list);
        System.out.println(list);
    }

    private static LinkedListNode<Integer> sortList(LinkedListNode<Integer> list) {
        LinkedListNode<Integer> current =list;
        LinkedListNode<Integer> next =null;
        LinkedListNode<Integer> sortedList =null;
        while (current!=null){
            next = current.next;
            current.next=null;

            LinkedListNode<Integer> cp = sortedList;
            LinkedListNode<Integer> prev = null;
            while (cp!=null&& current.data > cp.data){
                prev = cp;
                cp=cp.next;
            }
            if(prev==null){
                current.next = cp;
                sortedList = current;
            }else{
                prev.next = current;
                current.next = cp;
            }




            current=next;
        }

        return sortedList;
    }
}
