package epp.list;

import java.util.Arrays;

public class ZipList {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11));
        LinkedListNode<Integer> zipList  =zipList(list);
        System.out.println(zipList);
    }

    private static LinkedListNode<Integer> zipList(LinkedListNode<Integer> list) {
        LinkedListNode<Integer>[] halves = LinkedListNode.splitInHalf(list);
        LinkedListNode<Integer> first = halves[0];
        LinkedListNode<Integer> second = halves[1];
        second = reverseList(second);
        LinkedListNode<Integer>  result = mergeLists(first,second);



        return result;
    }

    public static LinkedListNode<Integer> reverseList(LinkedListNode<Integer> list) {
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

    private static LinkedListNode<Integer> mergeLists(LinkedListNode<Integer> first, LinkedListNode<Integer> second) {
        LinkedListNode<Integer> resultHead=null;
        LinkedListNode<Integer> resultTail=null;
        LinkedListNode<Integer> firstPrev = null;
        LinkedListNode<Integer> secondPrev = null;

        while (first!=null && second!=null){
            firstPrev = first;
            first = first.next;
            secondPrev = second;
            second = second.next;

            firstPrev.next = null;
            secondPrev.next = null;

            if(resultHead==null){
                resultTail = resultHead = firstPrev;
            }else {
                resultTail.next = firstPrev;
                resultTail = firstPrev;
            }
            resultTail.next = secondPrev;
            resultTail = secondPrev;
        }
        return resultHead;
    }
}
