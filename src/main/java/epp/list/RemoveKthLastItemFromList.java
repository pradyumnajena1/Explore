package epp.list;

import java.util.Arrays;

public class RemoveKthLastItemFromList {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(Arrays.asList(1,2,3,4,5,6,7,8,9));
        LinkedListNode<Integer> newList = removeLastItem(list,3);
        System.out.println(newList);
    }

    private static LinkedListNode<Integer> removeLastItem(LinkedListNode<Integer> list, int lastIndex) {
        LinkedListNode<Integer> frontPtr = list;
        int count = 1;
        while (frontPtr!=null && count<lastIndex){
            frontPtr=frontPtr.next;
            count++;
        }
        if(frontPtr==null){
            return list;
        }
        System.out.println(frontPtr.data);
        LinkedListNode<Integer> backPtr = list;
        LinkedListNode<Integer> prev = null;
        while (frontPtr.next!=null){
            frontPtr = frontPtr.next;
            prev = backPtr;
            backPtr = backPtr.next;
        }
        if(prev!=null){

            prev.next = backPtr.next;
            backPtr.next=null;
        }else {
            LinkedListNode<Integer> temp = backPtr.next;
            backPtr.data = temp.data;
            backPtr.next = temp.next;
            temp.next = null;
        }


        return list;
    }
}
