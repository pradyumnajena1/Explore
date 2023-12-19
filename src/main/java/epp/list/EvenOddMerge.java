package epp.list;

import java.util.Arrays;

public class EvenOddMerge {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        LinkedListNode<Integer> evenOddMergedList = evenOddMerge(list);
        System.out.println(evenOddMergedList);
    }

    private static LinkedListNode<Integer> evenOddMerge(LinkedListNode<Integer> list) {
        LinkedListNode<Integer> evenHead = null;
        LinkedListNode<Integer> evenTail = null;
        LinkedListNode<Integer> oddHead  = null;
        LinkedListNode<Integer> oddTail  = null;

        LinkedListNode<Integer> current = list;
        LinkedListNode<Integer> prev = null;
        LinkedListNode<Integer> next = null;
        int count =1;
        while (current!=null){
            next = current.next;

            prev = current;
            current = next;


            prev.next = null;
            if(count%2==1){
                if(oddHead==null){
                    oddTail = oddHead = prev;
                }else {
                    oddTail.next = prev;
                    oddTail = prev;
                }
            }else{
                if(evenHead==null){
                    evenTail = evenHead = prev;
                }else {
                    evenTail.next = prev;
                    evenTail = prev;
                }
            }

            count++;
        }
        evenTail.next = oddHead;
        return evenHead;
    }
}
