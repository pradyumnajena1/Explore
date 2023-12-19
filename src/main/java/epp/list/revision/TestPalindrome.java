package epp.list.revision;

import epp.list.LinkedListNode;

public class TestPalindrome {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(1,2,3,4,3,2,1);
        System.out.println(list);
        System.out.println(isPalindrome(list));

        list = LinkedListNode.createList(1,2,3,3,2,1);
        System.out.println(list);
        System.out.println(isPalindrome(list));

        list = LinkedListNode.createList(1,2,3,4,2,1);
        System.out.println(list);
        System.out.println(isPalindrome(list));

        list = LinkedListNode.createList(1 );
        System.out.println(list);
        System.out.println(isPalindrome(list));
    }

    private static boolean isPalindrome(LinkedListNode<Integer> list) {
        LinkedListNode<Integer>[] listNodes = LinkedListNode.splitInHalf(list);
        LinkedListNode<Integer> first = listNodes[0];
        LinkedListNode<Integer> second = listNodes[1];
        System.out.println(first);
        System.out.println(second);
        second= ReverseLInkedList.reverseList(second);
        System.out.println(second);
        while (first!=null&&second!=null){
            if(first.data!=second.data){
                return false;
            }
            first=first.next;
            second=second.next;
        }
        return true;
    }
}
