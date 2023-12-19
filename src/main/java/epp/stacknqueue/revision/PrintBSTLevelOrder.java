package epp.stacknqueue.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;

import java.util.ArrayDeque;
import java.util.Queue;

public class PrintBSTLevelOrder {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = BSTUtils.buildBST(10, 1, 10);
        System.out.println(root);
        printBinaryTreeLevelOrder(root);
        printBinaryTreeLevelOrder2(root);
    }

    private static void printBinaryTreeLevelOrder(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        Queue<BinaryTreeNode<Integer>> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Queue<BinaryTreeNode<Integer>> nextqueue = new ArrayDeque<>();
            while (!queue.isEmpty()) {
                BinaryTreeNode<Integer> poll = queue.poll();
                System.out.print(poll.data + ",");
                if (poll.left != null) {
                    nextqueue.offer(poll.left);
                }
                if (poll.right != null) {
                    nextqueue.offer(poll.right);
                }
            }
            System.out.println();
            queue = nextqueue;

        }

    }

    /**
     * using only one queue
     *
     * @param root
     */
    private static void printBinaryTreeLevelOrder2(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        Queue<BinaryTreeNode<Integer>> queue = new ArrayDeque<>();
        queue.offer(root);
        int count = queue.size();
        while (!queue.isEmpty()){
            BinaryTreeNode<Integer> poll = queue.poll();
            if(poll.left!=null){
                queue.offer(poll.left);
            }
            if(poll.right!=null){
                queue.offer(poll.right);
            }
            System.out.print(poll.data+",");
            count--;
            if(count==0){
                count=queue.size();
                System.out.println();
            }
        }
    }
}
