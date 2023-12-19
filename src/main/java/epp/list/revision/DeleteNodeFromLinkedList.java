package epp.list.revision;

import epp.list.LinkedListNode;

public class DeleteNodeFromLinkedList {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(1, 2, 3, 4, 5, 6, 7);
        System.out.println(list);
        LinkedListNode<Integer> node = LinkedListNode.getNode(list, 4);
        System.out.println(node);
        deleteNode(node);
        System.out.println(list);
    }

    private static void deleteNode(LinkedListNode<Integer> node) {
        if(node==null || node.next==null){
            throw new IllegalArgumentException("Last node cant be deleted");
        }
        LinkedListNode<Integer> nextNode = node.next;
        node.data =  nextNode.data;
        node.next = nextNode.next;
        nextNode.next=null;
    }
}
