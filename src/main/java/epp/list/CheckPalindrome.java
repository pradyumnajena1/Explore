package epp.list;

import java.util.Arrays;

import static epp.list.LinkedListNode.splitInHalf;

public class CheckPalindrome {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(Arrays.asList(1,2,3,4,5,5,4,3,2,1));
        boolean palindrome = isPalindrome(list);
        System.out.println(palindrome);
    }

    private static boolean isPalindrome(LinkedListNode<Integer> list) {
        if(list==null || list.next==null){
            return true;
        }
        LinkedListNode<Integer>[] splits = splitInHalf(list);
        LinkedListNode<Integer> firstHalf = splits[0];
        LinkedListNode<Integer> secondHalfHalf = splits[1];
        secondHalfHalf =   reverseList(secondHalfHalf);

        while (firstHalf!=null && secondHalfHalf!=null){
            if(!firstHalf.data.equals(secondHalfHalf.data)){
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalfHalf=secondHalfHalf.next;
        }

        return true;
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
