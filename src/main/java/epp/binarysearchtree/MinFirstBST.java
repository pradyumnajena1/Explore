package epp.binarysearchtree;

import epp.binaryTree.BinaryTreeNode;

public class MinFirstBST {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(2,
                new BinaryTreeNode<>(3,null , new BinaryTreeNode<>(5,new BinaryTreeNode<>(7),new BinaryTreeNode<>(11))),
                new BinaryTreeNode<>(13,new BinaryTreeNode<>(17),new BinaryTreeNode<>(19,new BinaryTreeNode<>(23),
                        null))

        );

        System.out.println(root);
        boolean isPresent = isPresent(root,20);
        System.out.println(isPresent);
    }

    private static boolean isPresent(BinaryTreeNode<Integer> root, int value) {

        if(root==null || value< root.data){
            return false;
        }

        if(root.data==value){
            return true;
        }
        if(root.right!=null && value>=root.right.data){
            return isPresent(root.right,value);
        }else{
            return isPresent(root.left,value);
        }

    }
}
