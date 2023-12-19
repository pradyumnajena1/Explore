package epp.stacknqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class JumplistOrder {
    public static void main(String[] args) {
        JumpListNode jumpListNode = getPostingList();
        List<Integer> order = getJumpFirstOrder(jumpListNode);
        System.out.println(order);

        jumpListNode = getPostingList();
        order = getJumpFirstOrderIter(jumpListNode);
        System.out.println(order);
    }

    private static List<Integer> getJumpFirstOrder(JumpListNode jumpListNode) {
        List<Integer> result = new ArrayList<>();
        getJumpFirstOrderRecurse(jumpListNode, result);
        return result;
    }

    private static List<Integer> getJumpFirstOrderIter(JumpListNode jumpListNode) {
        Stack<JumpListNode> stack = new Stack<>();
        List<Integer> order = new ArrayList<>();
        JumpListNode current = jumpListNode;
        while (!stack.empty() || (current != null)) {

            if (current != null) {
                stack.push(current);

                JumpListNode jump = (JumpListNode) current.jump;
                if (jump.order == -1) {
                    current = jump;
                } else {
                    current = null;
                }
            } else {
                current = stack.pop();
                order.add(current.data);
                current.order = 1;
                JumpListNode jump = (JumpListNode) current.next;
                if (jump.order == -1) {
                    current = jump;
                } else {
                    current = null;
                }
            }
        }


        return order;
    }

    private static void getJumpFirstOrderRecurse(JumpListNode jumpListNode, List<Integer> order) {
        if (jumpListNode == null) {
            return;
        }
        if (jumpListNode.order == -1) {
            jumpListNode.order = 1;
            order.add(jumpListNode.data);

        }
        if (jumpListNode.jump != null && ((JumpListNode) jumpListNode.jump).order == -1) {
            getJumpFirstOrderRecurse((JumpListNode) jumpListNode.jump, order);
        }
        getJumpFirstOrderRecurse((JumpListNode) jumpListNode.next, order);
    }


    private static JumpListNode getPostingList() {
        JumpListNode n1 = new JumpListNode(1);
        JumpListNode n2 = new JumpListNode(2);
        JumpListNode n3 = new JumpListNode(3);
        JumpListNode n4 = new JumpListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = null;

        n1.jump = n3;
        n2.jump = n4;
        n3.jump = n2;
        n4.jump = n4;
        return n1;
    }
}
