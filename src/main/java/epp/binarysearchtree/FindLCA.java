package epp.binarysearchtree;

import epp.binaryTree.BinaryTreeNode;

public class FindLCA {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(4,
                new BinaryTreeNode<>(2,new BinaryTreeNode<>(1),new BinaryTreeNode<>(4)),
                new BinaryTreeNode<>(5,new BinaryTreeNode<>(5),new BinaryTreeNode<>(6)));
        System.out.println(root);
        BinaryTreeNode<Integer> lca = findLCA(root,5,6);
        System.out.println(lca);
    }

    private static BinaryTreeNode<Integer> findLCA(BinaryTreeNode<Integer> root, int i, int j) {
        BinaryTreeNode<Integer> current = root;
        BinaryTreeNode<Integer> lca = root;
        while (current!=null){
            if(current.data> Math.max(i,j)){
                current = current.left;
            }else if(current.data< Math.min(i,j)){
                current = current.right;
            }else{
                lca = current;
                break;
            }
        }
        return lca;
    }
}
