package epp.sorting.revision;

import epp.list.LinkedListNode;

public class SortLinkedList {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(1, 2, 5, 2, 2, 1, 7, 7, 8, 3, 2, 11);
        System.out.println(list);
        list = sortList(list);
        System.out.println(list);
    }

    private static LinkedListNode<Integer> sortList(LinkedListNode<Integer> list) {
        if (list == null || list.next == null) {
            return list;
        }
        LinkedListNode<Integer>[] linkedListNodes = LinkedListNode.splitInHalf(list);
        linkedListNodes[0] = sortList(linkedListNodes[0]);
        linkedListNodes[1] = sortList(linkedListNodes[1]);
        return mergeList(linkedListNodes[0], linkedListNodes[1]);
    }

    private static LinkedListNode<Integer> mergeList(LinkedListNode<Integer> first,
                                                     LinkedListNode<Integer> second) {
        LinkedListNode<Integer> head = null, tail=null;
        while (first != null && second != null) {
            LinkedListNode<Integer> temp;
            if (first.data <= second.data) {
                temp = first;
                first = first.next;

            } else {
                temp = second;
                second = second.next;

            }
            temp.next = null;
            if(head==null){
                head=tail=temp;
            }else{
                tail.next=temp;
                tail = temp;
            }
        }
        if(first!=null){
            tail.next = first;
        }
        if(second!=null){
            tail.next=second;
        }

        return head;
    }
}
