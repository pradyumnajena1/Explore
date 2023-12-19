package epp.recursion.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * longest path from a node to another in a given tree
 */
public class DiameterOfATree {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = BSTUtils.buildBST(10, 1, 20);
        System.out.println(root);
          List<Integer> longestPath = findLongestPathInTree(root);
        System.out.println(longestPath);
    }

    private static List<Integer> findLongestPathInTree(BinaryTreeNode<Integer> root) {
        if(root==null){
            return new ArrayList<>();
        }
        List<Integer> leftResult = findLongestPathInTree(root.left);
        List<Integer> rightResult = findLongestPathInTree(root.right);

        List<Integer> leftPath = longestPathFromRoot(root.left);
        List<Integer> rightPath = longestPathFromRoot(root.right);
        List<Integer> longestPathThroughRoot = merge(leftPath, root, rightPath);

        if(leftResult.size()>rightResult.size() && leftResult.size()>longestPathThroughRoot.size()){
            return leftResult;
        }else if(rightResult.size()>leftResult.size() && rightResult.size()>longestPathThroughRoot.size()){
            return rightResult;
        }else{
            return longestPathThroughRoot;
        }

    }

    private static List<Integer> longestPathFromRoot(BinaryTreeNode<Integer> root) {
        ArrayList<Integer> result = new ArrayList<>();
        if(root==null){
            return result;
        }
        result.add(root.data);
        List<Integer> left = longestPathFromRoot(root.left);
        List<Integer> right = longestPathFromRoot(root.right);
        if(left.size()>right.size()){
            result.addAll(left);
        }else{
            result.addAll(right);
        }
        return result;
    }

    private static List<Integer> merge(List<Integer> leftPath, BinaryTreeNode<Integer> root, List<Integer> rightPath) {
        List<Integer> result = new ArrayList<>();
        for(int i=leftPath.size()-1;i>=0;i--){
            result.add(leftPath.get(i));
        }
        result.add(root.data);
        for(int i=0;i< rightPath.size();i++){
            result.add(rightPath.get(i));
        }
        return result;
    }
}
