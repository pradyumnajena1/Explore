package epp.list;

import java.util.Arrays;

public class AddIntegers {

    public static void main(String[] args) {
        LinkedListNode<Integer> l1 = LinkedListNode.createList(Arrays.asList(3,9,4));
        LinkedListNode<Integer> l2 = LinkedListNode.createList(Arrays.asList(7,9,9,9,9,9,9));
        LinkedListNode<Integer> result = addListInteger(l1,l2);
        System.out.println(result);
    }

    private static LinkedListNode<Integer> addListInteger(LinkedListNode<Integer> l1, LinkedListNode<Integer> l2) {
        if(l1 == null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        LinkedListNode<Integer> resultHead = null;
        LinkedListNode<Integer> resultTail = null;

        int carry =0;
        while (l1!=null || l2!=null || carry>0){
            int sum =( l1!=null? l1.data:0) +( l2!=null? l2.data:0)+carry;
            carry = sum/10;
            sum = sum%10;

            LinkedListNode<Integer> resultNode = new LinkedListNode<>(sum);
            if(resultHead==null){
                resultHead = resultTail = resultNode;
            }else{
                resultTail.next = resultNode;
                resultTail = resultNode;
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
