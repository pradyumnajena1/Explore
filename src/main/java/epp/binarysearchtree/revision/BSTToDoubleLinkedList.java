package epp.binarysearchtree.revision;

import epp.Pair;
import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;
import epp.binarysearchtree.BSTUtils;

import java.util.ArrayList;
import java.util.List;

public class BSTToDoubleLinkedList {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomSortedArray(10, 1, 10);
        BinaryTreeNode<Integer> root = BSTUtils.buildBSTFromSortedArray(values);
        List<Integer> inorder = BinaryTreeUtils.inOrderTraversal(root);
        System.out.println(inorder);
        Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> list = convertBSTToDoubleLinkedList(root);


        System.out.println(collectListData(list));
    }

    private static List<Integer> collectListData(Pair< BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> list) {
        assert list.getFirst().left==null;
        assert list.getSecond().right==null;
        List<Integer> values = new ArrayList<>();
        BinaryTreeNode<Integer> head = list.getFirst();
        while (head !=null){
            values.add(head.data);
            BinaryTreeNode<Integer> next = head.right;
            if(next!=null){
                assert head==next.left;
            }
            head = next;
        }
        return values;
    }

    public static Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> convertBSTToDoubleLinkedList(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> left = convertBSTToDoubleLinkedList(root.left);
        Pair<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> right = convertBSTToDoubleLinkedList(root.right);

        root.left = null;
        root.right = null;

        if (left != null) {
            root.left = left.getSecond();
            left.getSecond().right = root;

        }



        if (right != null) {
            right.getFirst().left = root;
            root.right = right.getFirst();
        }
        return new Pair<>(left != null ? left.getFirst() : root, right != null ? right.getSecond() : root);
    }
}
