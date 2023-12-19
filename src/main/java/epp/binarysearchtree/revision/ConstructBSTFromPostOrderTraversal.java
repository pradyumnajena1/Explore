package epp.binarysearchtree.revision;

import epp.LinearSearchUtils;
import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;
import epp.binarysearchtree.BSTUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class ConstructBSTFromPostOrderTraversal {
    public static void main(String[] args) {
        int[] values =   ArrayUtils.randomSortedArray(10, 1, 10);
        ArrayUtils.printArray(values);


        BinaryTreeNode<Integer> root = BSTUtils.buildBSTFromSortedArray(values);
        System.out.println(root);
        List<Integer> postOrderTraversal = BinaryTreeUtils.postOrderTraversal(root);


        System.out.println(postOrderTraversal);
        root = constructBSTFromPostOrderTraversal(postOrderTraversal);
        System.out.println(root);

        System.out.println(postOrderTraversal);
        root = constructBSTFromPostOrderTraversal2(postOrderTraversal, Integer.MIN_VALUE, Integer.MAX_VALUE,
                (Integer x) -> x + 1);
        System.out.println(root);

    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> constructBSTFromPostOrderTraversal(List<T> postOrderTraversal) {

        return constructBSTFromPostOrderTraversal(postOrderTraversal, 0, postOrderTraversal.size() - 1);
    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> constructBSTFromPostOrderTraversal2(List<T> postOrderTraversal, T minValue, T maxValue, Function<T, T> nextValue) {


        return constructBSTFromPostOrderTraversal2(postOrderTraversal, minValue, maxValue, new AtomicInteger(postOrderTraversal.size() - 1), nextValue);
    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> constructBSTFromPostOrderTraversal(List<T> postOrderTraversal, int start, int end) {
        if (start > end) {
            return null;
        }
        BinaryTreeNode<T> root = new BinaryTreeNode<>(postOrderTraversal.get(end));
        if (start == end) {
            return root;
        }
        int index = LinearSearchUtils.findLastItemSmallerThanEquals(postOrderTraversal, start, end - 1, root.data);

        if (index >= start) {
            root.left = constructBSTFromPostOrderTraversal(postOrderTraversal, start, index);
            root.right = constructBSTFromPostOrderTraversal(postOrderTraversal, index + 1, end - 1);
        } else {
            root.right = constructBSTFromPostOrderTraversal(postOrderTraversal, start, end - 1);
        }

        return root;
    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> constructBSTFromPostOrderTraversal2(List<T> postOrderTraversal, T minValue, T maxValue, AtomicInteger i, Function<T, T> function) {
        if (i.get() == -1) {
            return null;
        }
        T rootData = postOrderTraversal.get(i.get());
        if (rootData.compareTo(minValue) < 0 || rootData.compareTo(maxValue) > 0) {
            return null;
        }
        i.decrementAndGet();
        BinaryTreeNode<T> right = constructBSTFromPostOrderTraversal2(postOrderTraversal, function.apply(rootData),
                maxValue
                , i,
                function);
        BinaryTreeNode<T> left = constructBSTFromPostOrderTraversal2(postOrderTraversal, minValue, rootData, i, function);
        return new BinaryTreeNode<>(rootData, left, right);
    }

}
