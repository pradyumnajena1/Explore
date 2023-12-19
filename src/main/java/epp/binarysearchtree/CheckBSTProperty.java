package epp.binarysearchtree;

import epp.binaryTree.BinaryTreeNode;

public class CheckBSTProperty {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(8,
                new BinaryTreeNode<>(5,new BinaryTreeNode<>(3,new BinaryTreeNode<>(0),
                        new BinaryTreeNode<>(2)),
                        new BinaryTreeNode<>(17,null,new BinaryTreeNode<>(12,new BinaryTreeNode<>(1),null))),
                new BinaryTreeNode<>(21,new BinaryTreeNode<>(10,new BinaryTreeNode<>(14,null,new BinaryTreeNode<>(19)),
                        new BinaryTreeNode<>(4)),
                        new BinaryTreeNode<>(7,null,new BinaryTreeNode<>(8))));
        System.out.println(root);
        boolean isBST = checkIsBST(root);
        System.out.println(isBST);

        root = new BinaryTreeNode<>(4,
                new BinaryTreeNode<>(2,new BinaryTreeNode<>(1),new BinaryTreeNode<>(4)),
                new BinaryTreeNode<>(5,new BinaryTreeNode<>(5),new BinaryTreeNode<>(6)));

        System.out.println(root);
          isBST = checkIsBST(root);
        System.out.println(isBST);
    }

    private static boolean checkIsBST(BinaryTreeNode<Integer> root) {
        return checkIsBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    private static boolean checkIsBST(BinaryTreeNode<Integer> root, int minValue, int maxValue) {
        if(root==null){
            return true;
        }
        if( !(minValue<= root.data && maxValue>=root.data)){
            return false;
        }
        return checkIsBST(root.left,minValue,root.data) && checkIsBST(root.right,root.data,maxValue);
    }
}
