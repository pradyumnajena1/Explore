package epp.list;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(Arrays.asList(1,2,3,3,4,5,5,6,7,8,9));
        removeDuplicates(list);
        System.out.println(list);
    }

    private static void removeDuplicates(LinkedListNode<Integer> list) {
        LinkedListNode<Integer> current =list;
        LinkedListNode<Integer> prev =null;
        LinkedListNode<Integer> next =null;
        while (current!=null){

            next = current.next;

            if(prev!=null && prev.data.equals(current.data)){
                prev.next = next;
                current.next = null;

            }else{
                prev = current;
            }


            current = next;
        }

    }
}
