package epp.list.revision;

import epp.list.LinkedListNode;

public class ListPivoting {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(2,3,5,3,2);
        System.out.println(list);
        list= pivotList(list,3);
        System.out.println(list);
    }

    private static LinkedListNode<Integer> pivotList(LinkedListNode<Integer> list, int value) {
        LinkedListNode<Integer> smallerHead = null;
        LinkedListNode<Integer> smallerTail = null;

        LinkedListNode<Integer> equalHead = null;
        LinkedListNode<Integer> equalTail = null;

        LinkedListNode<Integer> biggerHead = null;
        LinkedListNode<Integer> biggerTail = null;

        LinkedListNode<Integer> current = list;
        LinkedListNode<Integer> next = null;
        while (current!=null){
            next = current.next;
            current.next = null;
            if(current.data<value){
                if(smallerHead==null){
                    smallerHead=smallerTail=current;
                }else{
                    smallerTail.next=current;
                    smallerTail=current;
                }

            }else if(current.data==value) {
                if(equalHead==null){
                    equalHead=equalTail=current;
                }else{
                    equalTail.next=current;
                    equalTail=current;
                }
            }else{

                if(biggerHead==null){
                    biggerHead=biggerTail=current;
                }else{
                    biggerTail.next=current;
                    biggerTail=current;
                }
            }

            current=next;

        }
        System.out.println(smallerHead);
        System.out.println(equalHead);
        System.out.println(biggerHead);
        if(smallerTail!=null){

            smallerTail.next=equalHead;
        }
        equalTail.next=biggerHead;
        return smallerHead!=null? smallerHead:equalHead;
    }
}
