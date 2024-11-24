package epp.list.revision;

import epp.list.LinkedListNode;

public class ZipList {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11);
        list = zipList(list);
        System.out.println(list);
    }

    private static LinkedListNode<Integer> zipList(LinkedListNode<Integer> list) {
        LinkedListNode<Integer>[] linkedListNodes = LinkedListNode.splitInHalf(list);
        LinkedListNode<Integer> firstPart = linkedListNodes[0];
        LinkedListNode<Integer> secondPart = linkedListNodes[1];
        System.out.println(firstPart);
        System.out.println(secondPart);
        secondPart =  ReverseLInkedList.reverseList(secondPart);
        System.out.println(secondPart);

        LinkedListNode<Integer> resultHeadPtr = null;
        LinkedListNode<Integer> resultTailPtr = null ;

        LinkedListNode<Integer> firstPtr = firstPart;
        LinkedListNode<Integer> secondPtr = secondPart ;

        while (firstPtr!=null&&secondPtr!=null){
            LinkedListNode<Integer> firstPtrNxt = firstPtr.next;
            LinkedListNode<Integer> secondPtrNxt = secondPtr.next;

            firstPtr.next = secondPtr;
            secondPtr.next = null;
            if(resultHeadPtr==null){
                resultHeadPtr = firstPtr;
            }else{
                resultTailPtr.next = firstPtr;
            }
            resultTailPtr = secondPtr;


            firstPtr = firstPtrNxt;
            secondPtr = secondPtrNxt;
        }
        if(firstPtr!=null){
            resultTailPtr.next = firstPtr;
        }
        if(secondPtr!=null){
            resultTailPtr.next = secondPtr;
        }


        return resultHeadPtr;
    }
}
