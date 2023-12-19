package epp.binarysearchtree.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;
import epp.binarysearchtree.BSTUtils;

import java.util.ArrayList;
import java.util.List;

public class RangeQuery {
    public static void main(String[] args) {
        //int[] values =  ArrayUtils.randomSortedArray(10, 1, 20);
        int[] values = { 10, 10, 10, 10};// ArrayUtils.randomSortedArray(10, 1, 20);
        BinaryTreeNode<Integer> root = BSTUtils.buildBSTFromSortedArray(values);
        System.out.println(root);
        List<Integer> itemsInRange = findItemsInRange(root, 10, 11);
        System.out.println(itemsInRange);
        itemsInRange = findItemsInRange2(root, 10, 11);
        System.out.println(itemsInRange);

    }

    private static List<Integer> findItemsInRange(BinaryTreeNode<Integer> root, int low, int high) {
        List<Integer> collector = new ArrayList<>();
        findItemsInRange(root, low, high, collector);
        return collector;
    }

    private static void findItemsInRange(BinaryTreeNode<Integer> root, int low, int high, List<Integer> collector) {
        if (root == null) {
            return;
        }
        if (root.data < low) {
            findItemsInRange(root.right, low, high, collector);
        } else if (root.data > high) {
            findItemsInRange(root.left, low, high, collector);

        } else {
            findItemsInRange(root.left, low, high, collector);
            collector.add(root.data);
            findItemsInRange(root.right, low, high, collector);
        }
    }

    private static List<Integer> findItemsInRange2(BinaryTreeNode<Integer> root, int low, int high) {
        BinaryTreeNode<Integer> minNode = BinaryTreeUtils.findFirstNodeEqualOrBigger(root, low);
        // BinaryTreeNode<Integer> maxNode = findLastNodeEqualOrSmaller(root,high);
        ArrayList<Integer> result = new ArrayList<>();
        if (minNode == null) {
            return result;
        }
        BinaryTreeNode<Integer> current = minNode;
        while (current != null && current.data.compareTo(high) <= 0) {
            result.add(current.data);
            current =BinaryTreeUtils.getInOrderSuccessor(root, current);
        }

        return result;

    }






}
