package epp.binarysearchtree.revision;

import epp.Pair;
import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;

import java.util.ArrayList;
import java.util.List;

public class FixAlmostBST {
    public static void main(String[] args) {
        int[] sortedArray = ArrayUtils.randomSortedUniqueArray(10, 1, 10);
        BinaryTreeNode<Integer> root = BSTUtils.buildBSTFromSortedArray(sortedArray);
        System.out.println(root);
        int[] randomNodes = {3, 8};
        ArrayUtils.printArray(randomNodes);
        BinaryTreeNode<Integer> node1 = BSTUtils.findNode(root, sortedArray[randomNodes[0]]);
        BinaryTreeNode<Integer> node2 = BSTUtils.findNode(root, sortedArray[randomNodes[1]]);
        swapNodeData(node1, node2);
        System.out.println(root);
        root = fixAlmostBST(root);
        System.out.println(root);


        root = BSTUtils.buildBSTFromSortedArray(sortedArray);
        System.out.println(root);
        randomNodes = new int[]{3, 8};
        ArrayUtils.printArray(randomNodes);
        node1 = BSTUtils.findNode(root, sortedArray[randomNodes[0]]);
        node2 = BSTUtils.findNode(root, sortedArray[randomNodes[1]]);
        swapNodeData(node1, node2);
        System.out.println(root);
        root = fixAlmostBST2(root);
        System.out.println(root);


    }

    /**
     * collect the changed node while doing inorder traversal instead of collecting then acting
     * so the o(n) additional space can be avoided
     *
     * @param root
     * @return
     */
    private static BinaryTreeNode<Integer> fixAlmostBST2(BinaryTreeNode<Integer> root) {

        Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> pair = new Pair<>(null, null);
        inorderTraversal(root, new Pair<>(null, null), pair);
        swapNodeData(pair.getFirst(), pair.getSecond());
        return root;
    }

    private static void inorderTraversal(BinaryTreeNode<Integer> root, Pair<BinaryTreeNode<Integer>,
            BinaryTreeNode<Integer>> prev,
                                         Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> pair) {
        if (root != null) {
            System.out.println(prev.getFirst() == null ? null : prev.getFirst().data);
            inorderTraversal(root.left, prev, pair);
            if (prev.getFirst() != null && root.data.compareTo(prev.getFirst().data) < 0) {
                pair.setSecond(root);
                if (pair.getFirst() == null) {
                    pair.setFirst(prev.getFirst());
                }
            }
            prev.setFirst(root);
            inorderTraversal(root.right, prev, pair);
        }
    }

    private static BinaryTreeNode<Integer> fixAlmostBST(BinaryTreeNode<Integer> root) {

        List<BinaryTreeNode<Integer>> collect = new ArrayList<>();
        inorderTraversal(root, collect);
        Integer p1 = null;
        Integer p2 = null;
        for (int i = 1; i < collect.size(); i++) {
            if (collect.get(i).data.compareTo(collect.get(i - 1).data) < 0) {
                p2 = i;
                if (p1 == null) {
                    p1 = i - 1;
                }
            }
        }
        swapNodeData(collect.get(p1), collect.get(p2));


        return root;
    }


    private static void inorderTraversal(BinaryTreeNode<Integer> root, List<BinaryTreeNode<Integer>> collect) {
        if (root != null) {
            inorderTraversal(root.left, collect);
            collect.add(root);
            inorderTraversal(root.right, collect);
        }
    }

    private static void swapNodeData(BinaryTreeNode<Integer> node1, BinaryTreeNode<Integer> node2) {
        int temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }
}
