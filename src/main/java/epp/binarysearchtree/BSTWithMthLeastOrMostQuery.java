package epp.binarysearchtree;

import epp.binaryTree.BinaryTreeNodeWithSize;

public class BSTWithMthLeastOrMostQuery {
    public static void main(String[] args) {
        BinaryTreeNodeWithSize<Integer> root = BSTUtils.buildBSTWithSize(10, 1, 10);
        System.out.println(root);
        fillWithItemsOnLeft(root);
        System.out.println(root);
        Integer value =  findNthSmallest(root,5);
        System.out.println(value);

        root =  insert(root,4);
        System.out.println(root);
       root =  insert(root,14);
        System.out.println(root);
    }

    private static BinaryTreeNodeWithSize<Integer> insert(BinaryTreeNodeWithSize<Integer> root, int value) {
        if(root==null){
            return new BinaryTreeNodeWithSize<>(value);
        }
        if(root.data>=value){
            root.left = insert(root.left,value);
            root.size++;
        }else{
            root.right = insert(root.right,value);
        }
        return root;

    }

    private static Integer findNthSmallest(BinaryTreeNodeWithSize<Integer> root, int n) {
        if (root == null) {
            return null;
        }

        if (root.size + 1 == n) {
            return root.data;
        } else if (root.size + 1 < n) {
            return findNthSmallest(root.right, n - root.size-1);
        } else {
            return findNthSmallest(root.left, n);
        }

    }

    private static int fillWithItemsOnLeft(BinaryTreeNodeWithSize<Integer> root) {
        if (root != null) {

            int left = fillWithItemsOnLeft(root.left);
            int right = fillWithItemsOnLeft(root.right);

            root.size = left;
            return left + right + 1;
        }
        return 0;

    }


}
