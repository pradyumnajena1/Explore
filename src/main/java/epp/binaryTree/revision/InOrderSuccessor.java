package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeNodeWithParent;
import epp.binarysearchtree.BSTUtils;

public class InOrderSuccessor {
    public static void main(String[] args) {
        BinaryTreeNodeWithParent<Integer> root = BSTUtils.buildBSTWithParentWithUniqueValues(10, 1, 10);
        System.out.println(root);
        BinaryTreeNodeWithParent<Integer> node = BSTUtils.findNode(root, 4);
        BinaryTreeNodeWithParent<Integer> successorNode = getInOrderSuccessor(node);
        System.out.println(successorNode.data);

    }

    private static BinaryTreeNodeWithParent<Integer> getInOrderSuccessor(BinaryTreeNodeWithParent<Integer> node) {
        if(node==null){
            return null;
        }
        BinaryTreeNodeWithParent<Integer> iter = node;
        if(iter.right!=null){
            iter = iter.right;
            while (iter.left!=null){
                iter = iter.left;
            }
            return iter;
        }
        BinaryTreeNodeWithParent<Integer> current = node ;
        while (current.parent!=null&&current.parent.right==current){
            current=current.parent;
        }
        return current.parent;
    }


}
