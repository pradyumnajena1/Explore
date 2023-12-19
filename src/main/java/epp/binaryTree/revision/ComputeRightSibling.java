package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNodeWithNext;

import java.util.ArrayDeque;
import java.util.Queue;

public class ComputeRightSibling {
    public static void main(String[] args) {
        BinaryTreeNodeWithNext<Integer> root = new BinaryTreeNodeWithNext<>(8,
                new BinaryTreeNodeWithNext<>(5, new BinaryTreeNodeWithNext<>(3),
                        new BinaryTreeNodeWithNext<>(17,new BinaryTreeNodeWithNext<>(21),
                                new BinaryTreeNodeWithNext<>(23))),
                new BinaryTreeNodeWithNext<>(21, new BinaryTreeNodeWithNext<>(10),
                        new BinaryTreeNodeWithNext<>(7,new BinaryTreeNodeWithNext<>(18),
                                new BinaryTreeNodeWithNext<>(19)))
        );
        System.out.println(root);
        computeRightSibling2(root);
    }


    private static void computeRightSibling2(BinaryTreeNodeWithNext<Integer> root) {
        if (root == null || root.isLeafNode()) {
            return;
        }
        Queue<BinaryTreeNodeWithNext<Integer>> queue = new ArrayDeque<>();
        queue.offer(root);
        int count = 1;
        BinaryTreeNodeWithNext<Integer> prev = null;
        while (!queue.isEmpty()) {
            BinaryTreeNodeWithNext<Integer> current = queue.poll();
            count--;
            if (current.left != null) {
                queue.offer((BinaryTreeNodeWithNext<Integer>) current.left);
            }
            if (current.right != null) {
                queue.offer((BinaryTreeNodeWithNext<Integer>) current.right);
            }
            if (prev != null) {
                prev.right = current;
                System.out.println(prev.data+"->"+(current!=null?current.data:null));
            }
            if (count == 0) {
                count = queue.size();
                prev = null;
            } else {
                prev = current;
            }
        }
    }
}
