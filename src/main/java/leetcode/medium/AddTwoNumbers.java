package leetcode.medium;

/**
 * 2. Add Two Numbers
 * Medium
 * Topics
 * Companies
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode result = solution.addTwoNumbers(createList(new int[]{2,4,3}), createList(new int[]{5,6,4}));
        print(result);

        result = solution.addTwoNumbers(createList(new int[]{9,9,9,9,9,9,9}), createList(new int[]{9,9,9,9}));
        print(result);
    }

    private static ListNode createList(int[] ints) {
        ListNode current = null;
        ListNode head = null;
        for(int value:ints){
            if(current==null){
                current = new ListNode(value);
                head = current;
            }else{
                current.next = new ListNode(value);
                current = current.next;
            }
        }
        return head;
    }

    private static void print(ListNode node) {
        while (node!=null){
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            return addTwoNumbers(l1, l2, 0);
        }

        private ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {
            int sum = carry;
            if (l1 == null && l2 == null) {
                return sum==0?null: new ListNode(sum);
            }
            if (l1 != null) {
                sum += l1.val;
            }
            if (l2 != null) {
                sum += l2.val;
            }
            int digit = sum % 10;
            carry = sum / 10;
            ListNode node = new ListNode(digit);
            node.next = addTwoNumbers(l1 == null ? null : l1.next, l2 == null ? null : l2.next, carry);
            return node;
        }


    }
}
