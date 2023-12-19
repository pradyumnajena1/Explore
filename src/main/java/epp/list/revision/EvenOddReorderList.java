package epp.list.revision;

import epp.list.LinkedListNode;

public class EvenOddReorderList {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(0,1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(list);
        list = evenOddReorderList(list);
        System.out.println(list);
    }

    private static LinkedListNode<Integer> evenOddReorderList(LinkedListNode<Integer> list) {
        LinkedListNode<Integer> evenHead = null;
        LinkedListNode<Integer> evenTail = null;
        LinkedListNode<Integer> oddHead = null;
        LinkedListNode<Integer> oddTail = null;

        LinkedListNode<Integer> current = list;
        LinkedListNode<Integer> next = null;

        int index = 0;
        while (current!=null){
            next = current.next;
            current.next = null;
            if(index%2==0){
                if(evenHead==null){
                    evenHead=evenTail=current;
                }else{
                    evenTail.next = current;
                    evenTail = current;
                }
            }else{
                if(oddHead==null){
                    oddHead=oddTail=current;
                }else{
                    oddTail.next = current;
                    oddTail = current;
                }
            }


            current = next;
            index++;
        }
        evenTail.next = oddHead;

        return evenHead;
    }
}
