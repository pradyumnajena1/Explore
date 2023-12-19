package epp.list.revision;

import epp.list.LinkedListNode;

public class RemoveKthLastNode {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(list);
        list = deleteKthLastNode(list,9);
        System.out.println(list);
    }

    private static LinkedListNode<Integer> deleteKthLastNode(LinkedListNode<Integer> list, int n) {

        LinkedListNode<Integer> front = list;

        for(int i=1;i<n && front!=null;i++){
            front = front.next;
        }
        if(front==null){
            return list;
        }
        if(front.next==null){
             LinkedListNode<Integer> nextNode = list.next;
             list.data = nextNode.data;
             list.next = nextNode.next;
             nextNode.next=null;
             return list;

        }


        LinkedListNode<Integer> back = list;
        LinkedListNode<Integer> prev = null;
        while (front.next!=null){
            front = front.next;
            prev = back;
            back = back.next;
        }

        prev.next = back.next;
        back.next = null;
        return list;
    }
}
