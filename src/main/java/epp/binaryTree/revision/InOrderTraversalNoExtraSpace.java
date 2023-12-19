package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeNodeWithParent;
import epp.binaryTree.BinaryTreeNodeWithSize;
import epp.binarysearchtree.BSTUtils;

import java.util.ArrayList;
import java.util.List;

public class InOrderTraversalNoExtraSpace {
    public static void main(String[] args) {
        BinaryTreeNodeWithParent<Integer> root = new BinaryTreeNodeWithParent<>(8,
                new BinaryTreeNodeWithParent<>(5,new BinaryTreeNodeWithParent<>(3,new BinaryTreeNodeWithParent<>(0),
                        new BinaryTreeNodeWithParent<>(2)),
                        new BinaryTreeNodeWithParent<>(17,null,new BinaryTreeNodeWithParent<>(12,new BinaryTreeNodeWithParent<>(1),null))),
                new BinaryTreeNodeWithParent<>(21,new BinaryTreeNodeWithParent<>(10,new BinaryTreeNodeWithParent<>(14,null,new BinaryTreeNodeWithParent<>(19)),
                        new BinaryTreeNodeWithParent<>(4)),
                        new BinaryTreeNodeWithParent<>(7,null,new BinaryTreeNodeWithParent<>(8)))
        );
        System.out.println(root);
        List<Integer> traversal = inOrderTraversalNoExtraSpace(root);
        System.out.println(traversal);
    }

    private static List<Integer> inOrderTraversalNoExtraSpace(BinaryTreeNodeWithParent<Integer> root) {
        List<Integer> result = new ArrayList<>();
        if(root==null){
            return result;
        }
        BinaryTreeNodeWithParent<Integer> current = getMinNode(root);
        while (current!=null){
            result.add(current.data);
            current = getInOrderSuccessor(current);
        }
        return result;
    }

    private static BinaryTreeNodeWithParent<Integer> getInOrderSuccessor(BinaryTreeNodeWithParent<Integer> node) {
       if(node==null){
           return null;
       }
       if(node.right!=null){
           return getMinNode(node.right);
       }
        BinaryTreeNodeWithParent<Integer> current = node;
        BinaryTreeNodeWithParent<Integer> parent = node.parent;

        while (parent!=null&&parent.right==current){
            current=parent;
            parent=parent.parent;
        }
        return parent;
    }

    private static <T extends Comparable<T>> BinaryTreeNodeWithParent<T> getMinNode(BinaryTreeNodeWithParent<T> root) {
        if(root==null){
            return null;
        }
        BinaryTreeNodeWithParent<T> min = root;
        while (min.left!=null){
            min = min.left;
        }
        return min;
    }
}
