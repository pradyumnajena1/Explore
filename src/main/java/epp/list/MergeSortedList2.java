package epp.list;

import java.util.Arrays;

public class MergeSortedList2 {
    public static void main(String[] args) {
        LinkedListNode<Integer> list1 = LinkedListNode.createList(Arrays.asList(2,5,7));
        LinkedListNode<Integer> list2 = LinkedListNode.createList(Arrays.asList(3,11));
        LinkedListNode<Integer> resultList =  mergeSortedLists(list1,list2);
        System.out.println(resultList);
    }

    private static LinkedListNode<Integer> mergeSortedLists(LinkedListNode<Integer> list1, LinkedListNode<Integer> list2) {

        LinkedListNode<Integer> head = null,tail=null;
        while (list1!=null && list2!=null){
            LinkedListNode<Integer> current;
            if(list1.data<=list2.data){
                current = list1;
                list1 = list1.next;
            }else{
                current = list2;
                list2 = list2.next;
            }
            current.next = null;
            if(head==null){
                head = tail = current;
            }else{
                tail.next = current;
                tail = current;
            }
        }
        //add reminders
        if(list1!=null){
            tail.next = list1;
        }
        if(list2!=null){
            tail.next = list2;
        }
        return head;
    }
}
