package epp.binarysearchtree.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;
import epp.binarysearchtree.BSTUtils;

import java.util.ArrayList;
import java.util.List;

public class VerifyBSTProperty {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = BSTUtils.buildBST(10, 1, 20);
        System.out.println(root);
        System.out.println(isValidBST(root));
        System.out.println(isValidBST2(root));
        System.out.println(isValidBST3(root));

        root.left.left.data = 21;
        System.out.println(root);
        System.out.println(isValidBST(root));
        System.out.println(isValidBST2(root));
        System.out.println(isValidBST3(root));
    }

    public static boolean isValidBST(BinaryTreeNode<Integer> root) {
        if (root == null || root.isLeafNode()) {
            return true;
        }
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isValidBST(BinaryTreeNode<Integer> root, int minValue, int maxValue) {
        if (root == null) {
            return true;
        }
        if (root.data < minValue || root.data > maxValue) {
            return false;
        }
        return isValidBST(root.left, minValue, root.data) && isValidBST(root.right, root.data, maxValue);
    }

    public static boolean isValidBST2(BinaryTreeNode<Integer> root) {
        if (root == null || root.isLeafNode()) {
            return true;
        }
        List<Integer> values = BinaryTreeUtils.inOrderTraversal(root);
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) < values.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidBST3(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return true;
        }
        return isValidBST3(root, new ArrayList<Integer>());
    }

    private static boolean isValidBST3(BinaryTreeNode<Integer> root, List<Integer> collector) {
        if (root != null) {
            if (!isValidBST3(root.left, collector)) {
                return false;
            }
            if (collector.size() > 0 && collector.get(collector.size() - 1) > root.data) {
                return false;
            }
            collector.add(root.data);
            return isValidBST3(root.right, collector);
        }
        return true;
    }


}
