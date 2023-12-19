package epp.list.revision;

import epp.list.LinkedListNode;

public class FindFirstCommonNodeWithCircle {
    public static void main(String[] args) {
        LinkedListNode<Integer> list1 = LinkedListNode.createList(1, 2, 3, 5, 6, 7, 8, 9,10,11,12,13);
        LinkedListNode<Integer> node5 = LinkedListNode.getNode(list1, 5);
        LinkedListNode<Integer> list2 = LinkedListNode.createList(1, 2, 4 );
        LinkedListNode<Integer> tail = LinkedListNode.getNode(list2, 4);
        tail.next = node5;
        LinkedListNode<Integer> node13 = LinkedListNode.getNode(list1, 13);
        LinkedListNode<Integer> node8 = LinkedListNode.getNode(list1, 8);
        node13.next = node8;
        System.out.println(list1);
        System.out.println(list2);
        LinkedListNode<Integer> commonNode =  findFirstCommonNodeWithCircle(list1,list2);
        System.out.println(commonNode );
    }

    private static LinkedListNode<Integer> findFirstCommonNodeWithCircle(LinkedListNode<Integer> list1, LinkedListNode<Integer> list2) {
        LinkedListNode<Integer> startOfCircleOfList1 = TestForCircularity.findStartOfCircle(list1);
        LinkedListNode<Integer> startOfCircleOfList2 = TestForCircularity.findStartOfCircle(list2);
        if(startOfCircleOfList1==startOfCircleOfList2){
            int length1 = getLengthTill(list1, startOfCircleOfList1);

            int length2 = getLengthTill(list2, startOfCircleOfList2);
            if(length1>=length2){
                int diff = length1-length2;
                while (diff>0){
                    list1 = list1.next;
                    diff--;
                }
            }else{
                int diff = length2-length1;
                while (diff>0){
                    list2 = list2.next;
                    diff--;
                }
            }
            while (list1!=list2){
                list1=list1.next;
                list2=list2.next;
            }
            return list1;
        }else{
            return null;
        }


    }

    private static int getLengthTill(LinkedListNode<Integer> list, LinkedListNode<Integer> stopNode) {
        LinkedListNode<Integer> temp = list;
        int length = 0;
        while (temp!= stopNode){
            temp=temp.next;
            length++;
        }
        return length;
    }
}
