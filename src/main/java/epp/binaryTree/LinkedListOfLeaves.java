package epp.binaryTree;

import epp.list.LinkedListNode;

public class LinkedListOfLeaves {
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
        LinkedListNode<BinaryTreeNode<Integer>> linkList = getLinkListofLeaves(root);
        System.out.println(linkList);

    }

    private static LinkedListNode<BinaryTreeNode< Integer>> getLinkListofLeaves(BinaryTreeNode<Integer> root) {
        LinkedListNode<BinaryTreeNode< Integer>>[] list = new LinkedListNode[2];
        getLinkListofLeaves(root,list);
        return list[0];
    }

    private static void getLinkListofLeaves(BinaryTreeNode<Integer> root, LinkedListNode<BinaryTreeNode< Integer>>[] list) {
        if(root==null){
            return;
        }
        getLinkListofLeaves(root.left,list);


        processNode(root, list);
        getLinkListofLeaves(root.right,list);
    }

    private static void processNode(BinaryTreeNode<Integer> root, LinkedListNode<BinaryTreeNode<Integer>>[] list) {
        if(root.left==null && root.right==null){
            LinkedListNode<BinaryTreeNode<Integer>> newNode = new LinkedListNode<>(root);
            if(list[0] == null){
                list[0] = list[1] = newNode;
            }else{
                list[1].next = newNode;
                list[1] = newNode;
            }
        }

    }
}
