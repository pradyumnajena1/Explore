package epp.list;

import java.util.Arrays;

import static epp.list.LinkedListNode.getLength;

public class CheckOverlappingLists {
    public static void main(String[] args) {
        LinkedListNode<Integer> list1 = LinkedListNode.createList(Arrays.asList(1,2,3,4,5,6));
        LinkedListNode<Integer> list2 = LinkedListNode.createList(Arrays.asList(10,11,12));
        LinkedListNode<Integer> node_4 = LinkedListNode.getNode(list1, 4);
        LinkedListNode<Integer> node_12 = LinkedListNode.getNode(list2, 12);

        System.out.println(list1);
        System.out.println(list2);
        node_12.next = node_4;
        System.out.println(list1);
        System.out.println(list2);
        LinkedListNode<Integer> jointNode = getFirstOverlappingNode(list1,list2);
        System.out.println(jointNode);
    }

    private static LinkedListNode<Integer> getFirstOverlappingNode(LinkedListNode<Integer> list1, LinkedListNode<Integer> list2) {
        int l1 = getLength(list1);
        int l2 = getLength(list2);

        LinkedListNode<Integer> p1 = list1;
        LinkedListNode<Integer> p2 = list2;
        if(l1>l2){
            int diff = l1-l2;
            while (diff-->0){
                p1 = p1.next;
            }
        }else if(l2>l1){
            int diff = l2-l1;
            while (diff-->0){
                p2 = p2.next;
            }
        }
        while (p1!=null&&p2!=null){
            if(p1==p2){
                return p1;
            }
            p2 = p2.next;
            p1=p1.next;
        }

        return null;
    }


}
