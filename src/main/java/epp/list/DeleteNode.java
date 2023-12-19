package epp.list;

import java.util.Arrays;

public class DeleteNode {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(Arrays.asList(1,2,3,4,5,6));
        LinkedListNode<Integer> node = LinkedListNode.getNode(list, 6);
        LinkedListNode<Integer> newList = deleteNode(list,node);
        System.out.println(newList);
    }

    private static LinkedListNode<Integer> deleteNode(LinkedListNode<Integer> list, LinkedListNode<Integer> node) {
        if(list==null || node==null){
            return list;
        }
        if(node.next==null){
            return list;
        }
        node.data = node.next.data;

        LinkedListNode<Integer> temp = node.next;
        node.next = node.next.next;
        temp.next = null;
        return list;

    }
}
