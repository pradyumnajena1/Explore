package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;

public class TestSymmetric {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(314,
                new BinaryTreeNode<>(6,null,new BinaryTreeNode<>(2,null,new BinaryTreeNode<>(3))),
                new BinaryTreeNode<>(6,new BinaryTreeNode<>(2,new BinaryTreeNode<>(3),null),null));
        System.out.println(root);
        System.out.println(isSymmetric(root));

        root = new BinaryTreeNode<>(314,
                new BinaryTreeNode<>(6,null,new BinaryTreeNode<>(561,null,new BinaryTreeNode<>(3))),
                new BinaryTreeNode<>(6,new BinaryTreeNode<>(2,new BinaryTreeNode<>(3),null),null));
        System.out.println(root);
        System.out.println(isSymmetric(root));

        root = new BinaryTreeNode<>(314,
                new BinaryTreeNode<>(6,null,new BinaryTreeNode<>(2,null,new BinaryTreeNode<>(3))),
                new BinaryTreeNode<>(6,new BinaryTreeNode<>(2,null,null),null));
        System.out.println(root);
        System.out.println(isSymmetric(root));
    }

    private static boolean isSymmetric(BinaryTreeNode<Integer> root) {
        if(root==null){
            return true;
        }

        return isMirrorImage(root.left,root.right);
    }

    private static boolean isMirrorImage(BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right) {
        if(left==null&&right==null){
            return true;
        }
        if(left==null||right==null){
            return false;
        }
        if(left.data!=right.data){
            return false;
        }
        return isMirrorImage(left.left,right.right) && isMirrorImage(left.right,right.left);
    }
}
