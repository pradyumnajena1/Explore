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
        if(node.right!=null){
            return getMinNode(node.right);
        }
        BinaryTreeNodeWithParent<Integer> current = node ;
        BinaryTreeNodeWithParent<Integer> parent = node.parent;
        while (parent!=null&&parent.right==current){
            current=parent;
            parent=parent.parent;
        }
        return parent;
    }

    private static BinaryTreeNodeWithParent<Integer> getMinNode(BinaryTreeNodeWithParent<Integer> node) {
        BinaryTreeNodeWithParent<Integer> current = node;
        while (current!=null&&current.left!=null){
            current = current.left;
        }
        return current;
    }
}
