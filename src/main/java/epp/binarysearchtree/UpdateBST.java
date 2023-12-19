package epp.binarysearchtree;

import epp.binaryTree.BinaryTreeNode;

public class UpdateBST {

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = BSTUtils.buildBSTWithUniqueValues(10, 1, 20);
        System.out.println(root);
        root = insert(root,7);
        root = insert(root,13);
        root = insert(root,15);
        System.out.println(root);

        root = delete(root,7);
        root = delete(root,15);
        root = delete(root,13);
        System.out.println(root);
    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> insert(BinaryTreeNode<T> root, T value) {
        if(root==null){
            return new BinaryTreeNode<>(value);
        }
        if(root.data.compareTo(value)<0){
            root.right= insert(root.right,value);
        }else if(root.data.compareTo(value)>0){
            root.left= insert(root.left,value);
        }else{
            System.out.println("value already present "+value);
        }
        return root;
    }
    public static <T extends Comparable<T>> BinaryTreeNode<T> insert(BinaryTreeNode<T> root,
                                                                     BinaryTreeNode<T> newNode) {
        if(root==null){
            return newNode;
        }
        if(root.data.compareTo(newNode.data)<0){
            root.right= insert(root.right,newNode);
        }else if(root.data.compareTo(newNode.data)>0){
            root.left= insert(root.left,newNode);
        }else{
            System.out.println("value already present "+newNode.data);
        }
        return root;
    }
    public static <T extends Comparable<T>> BinaryTreeNode<T> delete(BinaryTreeNode<T> root,T value){
        if(root==null){
            System.out.println("value nt found "+value);
            return root;
        }
        if(root.data.compareTo(value)>0){
            root.left= delete(root.left,value);
        }else if(root.data.compareTo(value)<0){
            root.right = delete(root.right,value);
        }else{
            System.out.println("value successfully deleted "+value);
            if(root.left==null){
                return root.right;
            }
            else if(root.right==null){
                return root.left;
            }
            BinaryTreeNode<T> min = findMin(root.right);
            root.data = min.data;
            root.right = delete(root.right,min.data);



        }

        return root;
    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> delete(BinaryTreeNode<T> root,BinaryTreeNode<T> newNode){
        if(root==null){
            System.out.println("value nt found "+newNode.data);
            return root;
        }
        if(root.data.compareTo(newNode.data)>0){
            root.left= delete(root.left,newNode);
        }else if(root.data.compareTo(newNode.data)<0){
            root.right = delete(root.right,newNode);
        }else{
            System.out.println("value successfully deleted "+newNode.data);
            if(root.left==null){
                return root.right;
            }
            else if(root.right==null){
                return root.left;
            }
            BinaryTreeNode<T> min = findMin(root.right);
            root.data = min.data;
            root.right = delete(root.right,min.data);



        }

        return root;
    }


    private static <T extends Comparable<T>> BinaryTreeNode<T> findMin(BinaryTreeNode<T> root) {
        BinaryTreeNode<T> current = root;
        while (current.left!=null){
            current = current.left;
        }
        return current;
    }
}
