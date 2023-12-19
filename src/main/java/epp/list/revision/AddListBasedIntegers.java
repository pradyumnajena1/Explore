package epp.list.revision;

import epp.list.LinkedListNode;

public class AddListBasedIntegers {
    public static void main(String[] args) {
        LinkedListNode<Integer> l1 = LinkedListNode.createList(3,1,4);
        LinkedListNode<Integer> l2 = LinkedListNode.createList(7,8,9);
        LinkedListNode<Integer> sum = addListBasedIntegers(l1,l2);
        System.out.println(sum);
    }

    private static LinkedListNode<Integer> addListBasedIntegers(LinkedListNode<Integer> l1, LinkedListNode<Integer> l2) {
        LinkedListNode<Integer> resultHead = null;
        LinkedListNode<Integer> resultTail = null;
        int carry =0;
        while (l1!=null || l2!=null || carry>0){
            int value = carry;
            if(l1!=null){
                value+=l1.data;
            }
            if(l2!=null){
                value+=l2.data;
            }
            carry = value/10;
            int digit = value%10;
            LinkedListNode newNode = new LinkedListNode(digit);

            if(resultHead==null){
                resultHead=resultTail=newNode;
            }else{
                resultTail.next=newNode;
                resultTail=newNode;
            }
            if(l1!=null){

                l1 = l1.next;
            }
            if(l2!=null){

                l2 = l2.next;
            }
        }

        return resultHead;
    }
}
