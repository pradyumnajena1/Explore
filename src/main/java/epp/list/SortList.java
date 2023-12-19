package epp.list;

import java.util.Arrays;

public class SortList {

    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(Arrays.asList(2,9,3,4,1,5,7,17));
        list = sortList(list);
        System.out.println(list);
    }

    private static LinkedListNode<Integer> sortList(LinkedListNode<Integer> list) {
        if(list==null || list.next==null){
            return list;
        }

        LinkedListNode<Integer> sortedListHead = list;
        LinkedListNode<Integer> sortedListTail = list;
        list = list.next;
        sortedListTail.next = null;

        LinkedListNode<Integer> current = list;
        LinkedListNode<Integer> prev = null;
        LinkedListNode<Integer> next = null;

        while (current!=null){
            next = current.next;

            prev = current;
            prev.next = null;

            if(sortedListTail.data.compareTo(prev.data)<=0){
                sortedListTail.next = prev;
                sortedListTail = prev;
            }else{
                LinkedListNode<Integer> scurrent = sortedListHead;
                LinkedListNode<Integer> sprev = null;
                LinkedListNode<Integer> snext = null;

                while (scurrent.data .compareTo( prev.data)<=0){
                    snext = scurrent.next;

                    sprev = scurrent;
                    scurrent = snext;
                }
                if(sprev!=null){

                    sprev.next = prev;
                    prev.next = scurrent;
                }else{
                  prev.next =   sortedListHead;
                  sortedListHead = prev;
                }



            }


            current = next;
        }




        return sortedListHead;
    }
}
