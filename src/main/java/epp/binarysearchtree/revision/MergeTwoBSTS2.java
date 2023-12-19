package epp.binarysearchtree.revision;

import epp.Pair;
import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;
import epp.binarysearchtree.BSTUtils;

import java.util.ArrayList;
import java.util.List;

public class MergeTwoBSTS2 {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root1 = BSTUtils.buildBST(5, 1, 20);
        BinaryTreeNode<Integer> root2 = BSTUtils.buildBST(5, 1, 20);
        System.out.println(root1);
        System.out.println(root2);

        BinaryTreeNode<Integer> root = mergeBSTS2(root1, root2);
        System.out.println(root);
    }

    public static BinaryTreeNode<Integer> mergeBSTS2(BinaryTreeNode<Integer> root1, BinaryTreeNode<Integer> root2) {
        Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> list1 = BSTToDoubleLinkedList.convertBSTToDoubleLinkedList(root1);
        Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> list2 = BSTToDoubleLinkedList.convertBSTToDoubleLinkedList(root2);
        printDoubleLinkedList(list1);
        printDoubleLinkedList(list2);
        Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> list = mergeDoubleLinkedLists(list1, list2);
        printDoubleLinkedList(list);

        BinaryTreeNode<Integer> root = createBSTFromDoubleLinkedList(list);
        List<Integer> integers = BinaryTreeUtils.inOrderTraversal(root);
        System.out.println(integers);
        return root;
    }

    private static Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> mergeDoubleLinkedLists(Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> list1, Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> list2) {
        Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> result = new Pair<>(null, null);
        BinaryTreeNode<Integer> head1 = list1.getFirst();
        BinaryTreeNode<Integer> head2 = list2.getFirst();

        BinaryTreeNode<Integer> node = null;
        while (head1 != null && head2 != null) {
            int compare = head1.data.compareTo(head2.data);

            if (compare <= 0) {
                node = head1;
                head1 = head1.right;
            } else {
                node = head2;
                head2 = head2.right;
            }
            node.right = null;
            node.left = null;
            addToResult(result, node);
        }
        if (head1 != null) {
            result.getSecond().right = head1;
            head1.left = result.getSecond();
            result.setSecond(list1.getSecond());

        }
        if (head2 != null) {
            result.getSecond().right = head2;
            head2.left = result.getSecond();
            result.setSecond(list2.getSecond());
        }
        assert result.getFirst().left == null;
        assert result.getSecond().right == null;
        return result;
    }

    private static Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> addToResult(Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> result, BinaryTreeNode<Integer> node) {
        node.left = null;
        node.right = null;

        if (result.getFirst() == null) {
            result.setFirst(node);
            result.setSecond(node);
        } else {
            BinaryTreeNode<Integer> tail = result.getSecond();
            tail.right = node;
            node.left = tail;
            result.setSecond(node);
        }
        return result;
    }

    private static void printDoubleLinkedList(Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> list) {
        BinaryTreeNode<Integer> head = list.getFirst();

        List<Integer> collector = new ArrayList<>();
        while (head != null) {
            collector.add(head.data);
            head = head.right;
        }
        System.out.println(collector);

    }

    private static BinaryTreeNode<Integer> createBSTFromDoubleLinkedList(Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> list) {
        int size = getSize(list.getFirst());
        return createBSTFromDoubleLinkedList(list, 0, size - 1);
    }

    private static int getSize(BinaryTreeNode<Integer> head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.right;
        }
        return count;
    }

    private static BinaryTreeNode<Integer> createBSTFromDoubleLinkedList(Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> list, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        BinaryTreeNode<Integer> left = createBSTFromDoubleLinkedList(list, start, mid - 1);
        BinaryTreeNode<Integer> root = list.getFirst();
        list.setFirst(root.right);
        BinaryTreeNode<Integer> right = createBSTFromDoubleLinkedList(list, mid + 1, end);
        root.right = right;
        root.left = left;
        return root;

    }

}
