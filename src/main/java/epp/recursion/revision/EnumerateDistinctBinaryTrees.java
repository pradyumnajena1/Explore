package epp.recursion.revision;

import epp.binaryTree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

public class EnumerateDistinctBinaryTrees {
    public static void main(String[] args) {
        List<BinaryTreeNode<Integer>> trees = enumerateBinaryTrees(3);
        System.out.println(trees);
    }

    private static List<BinaryTreeNode<Integer>> enumerateBinaryTrees(int n ) {
        return enumerateBinaryTrees(1,n);
    }

    private static List<BinaryTreeNode<Integer>> enumerateBinaryTrees(int start, int end) {
        List<BinaryTreeNode<Integer>> result = new ArrayList<>();
        if(start>end){
            result.add(null);
            return result;
        }
        for(int i=start;i<=end;i++){

            List<BinaryTreeNode<Integer>> leftTrees = enumerateBinaryTrees(start, i - 1);
            List<BinaryTreeNode<Integer>> rightTrees = enumerateBinaryTrees(i + 1, end);
            for(BinaryTreeNode<Integer> left:leftTrees){
                for(BinaryTreeNode<Integer> right:rightTrees){
                    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }
}
