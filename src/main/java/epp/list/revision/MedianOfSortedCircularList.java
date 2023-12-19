package epp.list.revision;

import epp.list.LinkedListNode;

public class MedianOfSortedCircularList {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(17,2,2,3,6);
        LinkedListNode<Integer> node6 = LinkedListNode.getNode(list, 6);
        node6.next=list;
        System.out.println(list);
        LinkedListNode<Integer> median =  findMedian(list);
        System.out.println(median.data);
    }

    private static LinkedListNode<Integer> findMedian(LinkedListNode<Integer> list) {
        int count=1;
        LinkedListNode<Integer> smallest = list;
        LinkedListNode<Integer> current = list;

        while (current.next!=list){
            current=current.next;
            if(current.data< smallest.data){
                smallest=current;
            }
            count++;
        }
          current = smallest;

        for(int i=0;i<count/2;i++){
            current = current.next;
        }
        return current;
    }
}
