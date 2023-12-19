package epp.binaryTree;

import epp.MutablePrimitive;

public class FindKthNodeInOrderTraversal {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(8,
                new BinaryTreeNode<>(5,new BinaryTreeNode<>(3,new BinaryTreeNode<>(0),
                        new BinaryTreeNode<>(2)),
                        new BinaryTreeNode<>(17,null,new BinaryTreeNode<>(12,new BinaryTreeNode<>(1),null))),
                new BinaryTreeNode<>(21,new BinaryTreeNode<>(10,new BinaryTreeNode<>(14,null,new BinaryTreeNode<>(19)),
                        new BinaryTreeNode<>(4)),
                        new BinaryTreeNode<>(7,null,new BinaryTreeNode<>(8)))
        );
        System.out.println(root);

        BinaryTreeNode<Integer> node = findKthNodeInOrderTraverse(root,5);
        System.out.println(node);
    }

    private static BinaryTreeNode<Integer> findKthNodeInOrderTraverse(BinaryTreeNode<Integer> root, int nodeIndex) {
        BinaryTreeNode<Integer>[] resultHolder = new BinaryTreeNode[1];
        MutablePrimitive<Integer> nodeCounter = new MutablePrimitive<>(0);
        findKthNodeInOrderTraverse(root,nodeIndex,nodeCounter,resultHolder);
        return resultHolder[0];
    }

    private static void findKthNodeInOrderTraverse(BinaryTreeNode<Integer> root, int nodeIndex, MutablePrimitive<Integer> nodeCounter, BinaryTreeNode<Integer>[] resultHolder) {
        if(root==null || resultHolder[0]!=null){
            return;
        }
        findKthNodeInOrderTraverse(root.left,nodeIndex, nodeCounter, resultHolder);
        nodeCounter.setValue(nodeCounter.getValue()+1);
        System.out.println(root.data+" "+nodeCounter.getValue());
        if(nodeIndex==nodeCounter.getValue()){
            resultHolder[0] = root;
            return;
        }

        findKthNodeInOrderTraverse(root.right,nodeIndex, nodeCounter, resultHolder);
    }
}
