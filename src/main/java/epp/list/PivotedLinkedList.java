package epp.list;

import java.util.Arrays;

public class PivotedLinkedList {

    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(Arrays.asList(3,3,4,9,7,5,2,2,3,3,5,7));
        LinkedListNode<Integer> pivotedList = pivotList(list,3);
        System.out.println(pivotedList);
    }

    private static LinkedListNode<Integer> pivotList(LinkedListNode<Integer> list, int pivot) {
        LinkedListNode<Integer> smallerHead= null;
        LinkedListNode<Integer> smallerTail= null;
        LinkedListNode<Integer> equalHead= null;
        LinkedListNode<Integer> equalTail= null;
        LinkedListNode<Integer> biggerHead= null;
        LinkedListNode<Integer> biggerTail= null;

        LinkedListNode<Integer> current= list;
        LinkedListNode<Integer> prev= null;
        LinkedListNode<Integer> next= null;
        while (current!=null){

            next = current.next;

            prev = current;
            prev.next = null;
            if(prev.data.compareTo(pivot)<0){
                if(smallerHead==null){
                    smallerTail = smallerHead = prev;
                }else{
                    smallerTail.next = prev;
                    smallerTail = prev;
                }

            }else if(prev.data.compareTo(pivot)==0){
                if(equalHead==null){
                    equalTail = equalHead = prev;
                }else{
                    equalTail.next = prev;
                    equalTail = prev;
                }
            }else{
                if(biggerHead==null){
                    biggerTail = biggerHead = prev;
                }else{
                    biggerTail.next = prev;
                    biggerTail = prev;
                }
            }
            current = next;
        }
        smallerTail.next = equalHead;
        equalTail.next = biggerHead;


        return smallerHead;
    }
}
