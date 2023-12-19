package epp.binarysearchtree.revision;

import epp.LinearSearchUtils;
import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;
import epp.binarysearchtree.BSTUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class ConstructBSTFromPreOrderTraversal {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomSortedUniqueArray(15, 1, 20);
        ArrayUtils.printArray(values);


        BinaryTreeNode<Integer> root = BSTUtils.buildBSTFromSortedArray(values);
        System.out.println(root);
        List<Integer> preOrderTraversal = BinaryTreeUtils.preOrderTraversal(root);

        System.out.println(preOrderTraversal);
        root = constructBSTFromPreOrderTraversal(preOrderTraversal);
        System.out.println(root);

        root = constructBSTFromPreOrderTraversal2(preOrderTraversal, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(root);



    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> constructBSTFromPreOrderTraversal(List<T> preOrderTraversal) {

        return constructBSTFromPreOrderTraversal(preOrderTraversal, 0, preOrderTraversal.size() - 1);
    }



    public static <T extends Comparable<T>> BinaryTreeNode<T> constructBSTFromPreOrderTraversal(List<T> preOrderTraversal, int start, int end) {
        if (start > end) {
            return null;
        }
        BinaryTreeNode<T> root = new BinaryTreeNode<>(preOrderTraversal.get(start));
        if (start == end) {
            return root;
        }
        int index = LinearSearchUtils.findFirstItemBiggerThan(preOrderTraversal, start, end, root.data);
        if (index >= start) {
            root.left = constructBSTFromPreOrderTraversal(preOrderTraversal, start + 1, index - 1);
            root.right = constructBSTFromPreOrderTraversal(preOrderTraversal, index, end);
        } else {
            root.left = constructBSTFromPreOrderTraversal(preOrderTraversal, start + 1, end);

        }

        return root;
    }



    public static <T extends Comparable<T>> BinaryTreeNode<T> constructBSTFromPreOrderTraversal2(List<T> preOrderTraversal, T minValue, T maxValue) {


        return constructBSTFromPreOrderTraversal2(preOrderTraversal, minValue, maxValue, new AtomicInteger(0));
    }



    public static <T extends Comparable<T>> BinaryTreeNode<T> constructBSTFromPreOrderTraversal2(List<T> preOrderTraversal, T minValue, T maxValue, AtomicInteger i) {
        if (i.get() == preOrderTraversal.size()) {
            return null;
        }
        T rootData = preOrderTraversal.get(i.get());
        if (rootData.compareTo(minValue) < 0 || rootData.compareTo(maxValue) > 0) {
            return null;
        }
        i.incrementAndGet();
        BinaryTreeNode<T> left = constructBSTFromPreOrderTraversal2(preOrderTraversal, minValue, rootData, i);
        BinaryTreeNode<T> right = constructBSTFromPreOrderTraversal2(preOrderTraversal, rootData, maxValue, i);
        return new BinaryTreeNode<>(rootData, left, right);
    }



}
