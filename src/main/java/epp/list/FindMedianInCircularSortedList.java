package epp.list;

import java.util.Arrays;

public class FindMedianInCircularSortedList {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(Arrays.asList(2,2,3,6,17));
        LinkedListNode<Integer> node6 = LinkedListNode.getNode(list, 6);
        LinkedListNode<Integer> node17 = LinkedListNode.getNode(list, 17);
        node17.next = list;

      int median =  findMedian(node6);
        System.out.println(median);

    }

    private static int findMedian(LinkedListNode<Integer> list) {
        LinkedListNode<Integer> current = list;
        LinkedListNode<Integer> prev = null;
        LinkedListNode<Integer> next = null;
        LinkedListNode<Integer> startNode = null;

        do{
            next = current.next;
            if(current.data>next.data){
                startNode = next;
            }

            prev = current;
            current = next;

        }while (current!=list);
        System.out.println(startNode.data);
        LinkedListNode<Integer> fastPtr = startNode;
        LinkedListNode<Integer> slowPtr = startNode;
        do{
            fastPtr = fastPtr.next;
            if(fastPtr==startNode){
                break;
            }
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next;

        }while (fastPtr!=startNode);



        return slowPtr.data;
    }
}
