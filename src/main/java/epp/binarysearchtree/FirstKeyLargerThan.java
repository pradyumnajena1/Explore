package epp.binarysearchtree;

import epp.binaryTree.BinaryTreeNode;

public class FirstKeyLargerThan {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(4,
                new BinaryTreeNode<>(2,new BinaryTreeNode<>(1),new BinaryTreeNode<>(4)),
                new BinaryTreeNode<>(5,new BinaryTreeNode<>(5),new BinaryTreeNode<>(6)));
        System.out.println(root);
        BinaryTreeNode<Integer> firstnodeLargerThan = findFirstNodeLarger(root,3);
        System.out.println(firstnodeLargerThan);
    }

    private static BinaryTreeNode<Integer> findFirstNodeLarger(BinaryTreeNode<Integer> root, int key) {
        BinaryTreeNode<Integer> result = null;
        BinaryTreeNode<Integer> found = null;
        BinaryTreeNode<Integer> current = root;
        while (current!=null){
            if(current.data>key){
                result = current;
                current = current.left;
            }else if(current.data==key) {
                current = current.right;
                found = current;
            }else{
                current = current.right;
            }
        }

        return found!=null? result:null;
    }
}
