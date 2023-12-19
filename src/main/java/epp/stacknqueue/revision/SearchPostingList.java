package epp.stacknqueue.revision;

import epp.list.PostingListNode;
import epp.stacknqueue.JumpListNode;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class SearchPostingList {
    public static void main(String[] args) {
        JumpListNode postingList = getPostingList();
        System.out.println(postingList);
        searchPostingListRecursive(postingList);
        postingList = getPostingList();
        System.out.println(postingList);
        searchPostingListIterative(postingList );
    }

    private static void searchPostingListIterative(JumpListNode jumpListNode) {
        Stack<JumpListNode> stack = new Stack<>();
        int order=0;
        stack.push(jumpListNode);
        while (!stack.isEmpty()){
            jumpListNode = stack.pop();
            if(jumpListNode!=null&&jumpListNode.order==-1){
                jumpListNode.order = order++;
                System.out.println(jumpListNode.data+" "+ jumpListNode.order);
                stack.push((JumpListNode) jumpListNode.next);
               stack.push((JumpListNode) jumpListNode.jump);
            }
        }

    }

    private static void searchPostingListRecursive(JumpListNode jumpListNode) {
        searchPostingListRecursive(jumpListNode, new AtomicInteger(0));
    }

    private static void searchPostingListRecursive(JumpListNode jumpListNode, AtomicInteger order) {
        if (jumpListNode != null && jumpListNode.order==-1 ) {
            jumpListNode.order = order.getAndIncrement();
            System.out.println(jumpListNode.data+" "+ jumpListNode.order);
            searchPostingListRecursive((JumpListNode) jumpListNode.jump,order);
            searchPostingListRecursive((JumpListNode) jumpListNode.next,order);
        }

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
