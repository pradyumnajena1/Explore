package epp.list.revision;

import epp.list.LinkedListNode;
import java.util.Arrays;
import java.util.List;

public class EvenOddMerge {

    public static void main(String[] args){
        LinkedListNode<Integer> list = LinkedListNode.createList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(list);
      list =   evenOddMerge(list);
    System.out.println(list);
    }
    public static LinkedListNode<Integer> evenOddMerge(LinkedListNode<Integer> list){
        LinkedListNode<Integer> dummyEvenHead = new LinkedListNode<>(0,null);
        LinkedListNode<Integer> dummyOddHead = new LinkedListNode<>(0,null);

        List<LinkedListNode<Integer>> dummyHeads = Arrays.asList(dummyEvenHead, dummyOddHead);
        int turn  = 0;
        while (list!=null){
            dummyHeads.get(turn).next = list;
            dummyHeads.set(turn,dummyHeads.get(turn).next);

            list=list.next;
            turn = turn ^1;
        }
        dummyHeads.get(1).next = null;
        dummyHeads.get(0).next = dummyOddHead.next;
        return dummyEvenHead.next;
    }
}
