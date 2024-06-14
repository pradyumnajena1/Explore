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
        if(list==null || list.next==null){
            return true;
        }
        LinkedListNode<Integer> fast = list;
        LinkedListNode<Integer> slow = list;
        while (fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        LinkedListNode<Integer> firstHalfIter = list;
        LinkedListNode<Integer> secondHalfIter = ReverseLInkedList.reverseList(slow);
        while (firstHalfIter!=null && secondHalfIter!=null){
            if(firstHalfIter.data!=secondHalfIter.data){
                return false;
            }
            firstHalfIter = firstHalfIter.next;
            secondHalfIter = secondHalfIter.next;
        }

        return true;
    }
}
