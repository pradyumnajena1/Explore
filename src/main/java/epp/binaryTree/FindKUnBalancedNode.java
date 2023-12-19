package epp.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class FindKUnBalancedNode {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(4,
                new BinaryTreeNode<>(2,new BinaryTreeNode<>(1),new BinaryTreeNode<>(3)),
                new BinaryTreeNode<>(6,new BinaryTreeNode<>(5),new BinaryTreeNode<>(7,
                        null,new BinaryTreeNode<>(8,null,new BinaryTreeNode<>(9))))
        );
        System.out.println(root);
      BinaryTreeNode<Integer> unbalancedNode =   findKUnbalancedNode(root,1);
        System.out.println(unbalancedNode);
    }

    private static BinaryTreeNode<Integer> findKUnbalancedNode(BinaryTreeNode<Integer> root, int k) {

        if(root==null){
            return null;
        }
        List<BinaryTreeNode<Integer>> nodeHolder = new ArrayList<>();
        nodeHolder.add(null);
        int leftCount = getNodeCount(root.left,k,nodeHolder);
        if(leftCount == -1){
           return  nodeHolder.get(0);
        }
        int rightCount = getNodeCount(root.right,k,nodeHolder);
        if(rightCount==-1){
            return nodeHolder.get(0);
        }
        if(Math.abs(leftCount-rightCount)>k){
            return root;
        }
        return null;
    }

    private static int getNodeCount(BinaryTreeNode<Integer> root, int k, List<BinaryTreeNode<Integer>> nodeHolder) {
        if(root==null){
            return 0;
        }
        int left = getNodeCount(root.left, k, nodeHolder);
        if(left==-1){
            return -1;
        }
        int right = getNodeCount(root.right, k, nodeHolder);
        if(right==-1){
            return -1;
        }
        if(Math.abs(left-right)>k){
            nodeHolder.set(0,root);
            return -1;
        }
        return left+right+1;
    }
}
