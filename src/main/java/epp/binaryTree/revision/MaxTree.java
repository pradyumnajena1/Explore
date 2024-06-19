package epp.binaryTree.revision;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Let A be an array of n distinct integers. Let the index of the maximum
 * element of A be m. Define the max-tree on A to be the binary tree on the entries of A
 * in which the root contains the maximum element of A, the left child is the max-tree
 * on A[0 : m- 1] and the right child is the max-tree on A[m +1 : n— 1]. Design an 0(n)
 * algorithm for building the max-tree of A.
 */
public class MaxTree {
    public static void main(String[] args){
        int[] values = new int[]{10, 5, 3, 7,2,8,1,9,4,6};// ArrayUtils.randomArray(5, 1, 10);
        System.out.println(Arrays.toString(values));
        BinaryTreeNode<Integer> root = buildMaxTree(values);
        System.out.println(root);

        root = buildMinTree(values);
        System.out.println(root);
    }

    public static BinaryTreeNode<Integer> buildMaxTree(int[] values) {
        if(values==null || values.length==0){
            return null;
        }
        Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();
        for (int i = 0; i < values.length; i++) {

            BinaryTreeNode<Integer> current = new BinaryTreeNode<>(values[i]);
            while (!stack.isEmpty() && stack.peek().data < values[i]) {
                current.left = stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.peek().right = current;
            }
            stack.push(current);
        }
        return stack.descendingIterator().next();
    }


    public static BinaryTreeNode<Integer> buildMinTree(int[] values) {
        if(values==null || values.length==0){
            return null;
        }
        Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();
        for (int i = 0; i < values.length; i++) {
            BinaryTreeNode<Integer> current = new BinaryTreeNode<>(values[i]);
            while (!stack.isEmpty() && stack.peek().data > values[i]) {
                current.left = stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.peek().right = current;
            }
            stack.push(current);
        }
        return stack.descendingIterator().next();
    }

}
