package epp.list.revision;

import epp.list.LinkedListNode;

public class TestForCommonNode {
    public static void main(String[] args) {
        LinkedListNode<Integer> list1 = LinkedListNode.createList(1, 2, 3, 5, 6, 7, 8, 9);
        LinkedListNode<Integer> node5 = LinkedListNode.getNode(list1, 5);
        LinkedListNode<Integer> list2 = LinkedListNode.createList(1, 2,  4 );
        LinkedListNode<Integer> tail = LinkedListNode.getNode(list2, 4);
        tail.next = node5;
        LinkedListNode<Integer> commonNode =  findFirstCommonNode(list1,list2);
        System.out.println(commonNode );
    }

    private static LinkedListNode<Integer> findFirstCommonNode(LinkedListNode<Integer> list1, LinkedListNode<Integer> list2) {
        int list1Length =  LinkedListNode.getLength(list1);
        int list2Length =  LinkedListNode.getLength(list2);

        if(list1Length<=list2Length){
            int diff = list2Length-list1Length;
            while (diff>0){
                list2=list2.next;
            }
        }else{
            int diff = list1Length-list2Length;
            while (diff>0){
                list1=list1.next;
            }
        }
        while ( list1!=null && list1!=list2){
            list2 = list2.next;
            list1 = list1.next;
        }

        return list1;
    }
}
