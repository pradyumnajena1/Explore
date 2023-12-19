package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;
import epp.list.LinkedListNode;

public class LinkedListOfLeafNodes {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = BSTUtils.buildBST(20, 1, 20);
        System.out.println(root);
        LinkedListNode<BinaryTreeNode<Integer>> list = getLeafNodesAsLinkedList(root);
        while (list!=null){
            System.out.println(list.data.data);
            list=list.next;
        }
    }
    private static class ResultCollector {
        LinkedListNode<BinaryTreeNode<Integer>> head = null;
        LinkedListNode<BinaryTreeNode<Integer>> tail = null;

        private   void collect(BinaryTreeNode<Integer> root) {
            LinkedListNode<BinaryTreeNode<Integer>> linkedListNode = new LinkedListNode<>(root);
            if ( head == null) {
                 head=  tail= linkedListNode;
            } else {
                 tail.next = linkedListNode;
                 tail=  linkedListNode;
            }
        }
    }

    private static LinkedListNode<BinaryTreeNode<Integer>> getLeafNodesAsLinkedList(BinaryTreeNode<Integer> root) {
        ResultCollector container = new ResultCollector();
        getLeafNodesAsLinkedList(root, container);
        return container.head;
    }

    private static void getLeafNodesAsLinkedList(BinaryTreeNode<Integer> root, ResultCollector resultCollector) {
        if (root == null) {
            return;
        }
        getLeafNodesAsLinkedList(root.left, resultCollector);
        if (root.isLeafNode()) {
            resultCollector.collect(root);
        }
        getLeafNodesAsLinkedList(root.right, resultCollector);
    }


}
