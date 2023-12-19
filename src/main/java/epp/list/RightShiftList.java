package epp.list;

import java.util.Arrays;

public class RightShiftList {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(Arrays.asList(1,2,3,4,5,6,7,8,9));
        LinkedListNode<Integer> newList = rotateRight(list,3);
        System.out.println(newList);
    }

    private static LinkedListNode<Integer> rotateRight(LinkedListNode<Integer> list, int numPositions) {
        LinkedListNode<Integer> frontPtr = list;
        int count =1;
        while (frontPtr!=null && count<numPositions){
            frontPtr = frontPtr.next;
            count++;
        }
        if(frontPtr==null){
            return list;
        }
        LinkedListNode<Integer> backPtr = list;
        LinkedListNode<Integer> prevPtr = null;
        while (frontPtr.next!=null){
            frontPtr = frontPtr.next;

            prevPtr = backPtr;
            backPtr = backPtr.next;
        }

        if(prevPtr!=null){
            prevPtr.next = null;
        }
          backPtr =   reverseList(backPtr);
        LinkedListNode<Integer> temp = backPtr;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = list;
        return backPtr;
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
}
