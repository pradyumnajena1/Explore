package epp.binarysearchtree.revision;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;

public class FindLCAInBST {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomSortedArray(10, 1, 30);
        BinaryTreeNode<Integer> root = BSTUtils.buildBSTFromSortedArray(values);
        System.out.println(root);
        ArrayUtils.printArray(values);
        BinaryTreeNode<Integer> lca = findLCA(root, values[1], values[3]);
        System.out.println(lca);
    }

    private static BinaryTreeNode<Integer> findLCA(BinaryTreeNode<Integer> root, int smaller, int bigger) {
        BinaryTreeNode<Integer> current = root;
        BinaryTreeNode<Integer> LCA = null;
        while (current != null) {

            int compare_sm = current.data.compareTo(smaller);
            int compare_big = current.data.compareTo(bigger);
            if (compare_sm == 0 || compare_big == 0) {
                LCA = current;
                break;
            }
            if (compare_sm < 0) {
                current = current.right;
            } else if (compare_big > 0) {
                current = current.left;
            } else {
                LCA = current;
                break;
            }
        }
        return LCA;
    }
}
