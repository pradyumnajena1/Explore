package epp.recursion;

import epp.binaryTree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

public class EnumerateBinaryTrees {
    public static void main(String[] args) {
        List<BinaryTreeNode<Integer>> trees = getAllBinaryTrees(3);
        for(BinaryTreeNode<Integer> root:trees){
            System.out.println(root);
        }
        System.out.println(trees.size());
    }

    private static List<BinaryTreeNode<Integer>> getAllBinaryTrees(int n) {

        return getAllBinaryTrees(1,n);
    }

    private static List<BinaryTreeNode<Integer>> getAllBinaryTrees(int start, int end) {
        if(start>end){
            ArrayList<BinaryTreeNode<Integer>> binaryTreeNodes = new ArrayList<>();
            binaryTreeNodes.add(null);
            return binaryTreeNodes;
        }
        if(start==end){
            ArrayList<BinaryTreeNode<Integer>> binaryTreeNodes = new ArrayList<>();
            binaryTreeNodes.add(new BinaryTreeNode<>(start));
            return binaryTreeNodes;
        }
        List<BinaryTreeNode<Integer>> trees = new ArrayList<>();
        for(int i=start;i<=end;i++){
            List<BinaryTreeNode<Integer>> lefts = getAllBinaryTrees(start, i - 1);
            List<BinaryTreeNode<Integer>> rights = getAllBinaryTrees(i + 1, end);
            for(BinaryTreeNode<Integer> left:lefts){
                for(BinaryTreeNode<Integer> right:rights){
                    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(i);
                    root.left = left;
                    root.right = right;
                    trees.add(root);
                    BinaryTreeNode<Integer> root2 = new BinaryTreeNode<>(i);
                    root2.left = right;
                    root2.right = left;
                    trees.add(root2);
                }
            }
        }
        return trees;
    }
}
