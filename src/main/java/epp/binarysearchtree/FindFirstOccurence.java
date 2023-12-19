package epp.binarysearchtree;

import epp.binaryTree.BinaryTreeNode;

public class FindFirstOccurence {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(4,
                new BinaryTreeNode<>(2,new BinaryTreeNode<>(1),new BinaryTreeNode<>(4)),
                new BinaryTreeNode<>(5,new BinaryTreeNode<>(5),new BinaryTreeNode<>(6)));
        System.out.println(root);
        BinaryTreeNode<Integer> key = findFirstOccurence(root,4);
        System.out.println(key);

    }

    private static BinaryTreeNode<Integer> findFirstOccurence(BinaryTreeNode<Integer> root, int key) {
        BinaryTreeNode<Integer> current = root;
        BinaryTreeNode<Integer> found = null;
        while (current!=null){
            if(current.data==key){
                found = current;
                current = current.left;
            }else if(current.data<key){
                current = current.right;
            }else{
                current = current.right;
            }
        }
        return found;
    }
}
