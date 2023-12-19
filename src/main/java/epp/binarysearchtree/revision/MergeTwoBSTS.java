package epp.binarysearchtree.revision;

import epp.Pair;
import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;
import epp.binarysearchtree.BSTUtils;

import java.util.ArrayList;
import java.util.List;

public class MergeTwoBSTS {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root1 = BSTUtils.buildBST(5, 1, 20);
        BinaryTreeNode<Integer> root2 = BSTUtils.buildBST(5, 1, 20);
        System.out.println(root1);
        System.out.println(root2);

        BinaryTreeNode<Integer> root = mergeBSTS(root1, root2);
        System.out.println(root);



    }

    public static BinaryTreeNode<Integer> mergeBSTS(BinaryTreeNode<Integer> root1, BinaryTreeNode<Integer> root2) {
        List<Integer> values = BinaryTreeUtils.inOrderTraversal(root1);
        for (int value : values) {
            root2 = BSTUtils.insertNode(root2, value);
        }
        return root2;
    }

    /**
     * no new creation of node allowed, only left n right pointer can be changed
     *
     * @param root1
     * @param root2
     * @return
     */








}
