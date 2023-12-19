package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeNodeWithParent;

public class FindLCA {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> a = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> b = new BinaryTreeNode<>(8, null, null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(4,
                new BinaryTreeNode<>(2,new BinaryTreeNode<>(1),new BinaryTreeNode<>(3)),
                new BinaryTreeNode<>(6, a,new BinaryTreeNode<>(7,
                        new BinaryTreeNode<>(9), b))
        );
        System.out.println(root);
        BinaryTreeNode<Integer> lca =  lca(root,a,b);
        System.out.println(lca.data);

    }

    private static BinaryTreeNode<Integer> lca(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> a, BinaryTreeNode<Integer> b) {
       if(root==null){
           return null;
       }
       if(root==a||root==b){
           return root;
       }
        BinaryTreeNode<Integer> lres = lca(root.left, a, b);
        BinaryTreeNode<Integer> rres = lca(root.right, a, b);
        if(lres!=null&&rres!=null){
            return root;
        }
        return lres!=null?lres:rres;
    }
}
