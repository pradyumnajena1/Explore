package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;

public class TestBalancedTree {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(4,
                new BinaryTreeNode<>(2,new BinaryTreeNode<>(1),new BinaryTreeNode<>(3)),
                new BinaryTreeNode<>(6,new BinaryTreeNode<>(5),new BinaryTreeNode<>(7,
                        null,new BinaryTreeNode<>(8,null,new BinaryTreeNode<>(9))))
        );
        System.out.println(root);
        System.out.println(isBalancedTree(root));
    }

    public static boolean isBalancedTree(BinaryTreeNode<Integer> root) {

        int height = getHeight(root );
        if(height==-1){
            return false;
        }

        return true;
    }

    private static int getHeight(BinaryTreeNode<Integer> root) {
        if(root==null){
            return 0;
        }
        int leftHeight = getHeight(root.left);
        if(leftHeight==-1){
            return -1;
        }
        int rightHeight = getHeight(root.right);
        if(rightHeight==-1){
            return -1;
        }
        if(Math.abs(leftHeight-rightHeight)>1){
            return -1;
        }
        return 1+Math.max(leftHeight,rightHeight);
    }
}
