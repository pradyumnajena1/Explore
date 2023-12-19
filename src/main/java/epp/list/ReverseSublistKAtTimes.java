package epp.list;

import java.util.Arrays;

public class ReverseSublistKAtTimes {

    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11));
        System.out.println(list);
        LinkedListNode<Integer> result = reverseSublistKAtTimes(list,3);
        System.out.println(result);
    }

    private static LinkedListNode<Integer> reverseSublistKAtTimes(LinkedListNode<Integer> list, int subListSize) {
        if(list==null ){
            return null;
        }

        LinkedListNode<Integer> resultListHead = null;
        LinkedListNode<Integer> resultListTail = null;

        LinkedListNode<Integer> subListHead = null;
        LinkedListNode<Integer> subListTail = null;

        int count = 1;
        LinkedListNode<Integer> prev = null;
        LinkedListNode<Integer> current = list;
        LinkedListNode<Integer> next = null;
        while (current!=null){
            next = current.next;

            if(count%subListSize==1){
                subListHead = current;
            }
            if(count%subListSize==0){
                current.next = null;
                subListTail = subListHead;
                subListHead=  reverseList(subListHead);

                if(resultListHead==null){
                    resultListHead = subListHead;
                    resultListTail = subListTail;
                }else{
                    resultListTail.next = subListHead;
                    resultListTail = subListTail;
                }
                subListHead = null;
            }

            prev = current;
            current = next;
            count++;

        }
        if(subListHead!=null){
            resultListTail.next = subListHead;
        }


        return resultListHead;
    }

    private static LinkedListNode<Integer> reverseList(LinkedListNode<Integer> list) {
        if(list==null|| list.next==null){
            return list;
        }
        LinkedListNode<Integer> prev = null;
        LinkedListNode<Integer> current = list;
        LinkedListNode<Integer> next = null;


        while (current!=null){

            next = current.next;

            current.next = prev;

            prev = current;
            current = next;
        }
        return prev;
    }
}
