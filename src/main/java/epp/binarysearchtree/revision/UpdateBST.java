package epp.binarysearchtree.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;

/**
 * tree of unique value
 * insert or delete must not change content of any node
 */
public class UpdateBST {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = null;
       root =  insertData(root,6);
       root =  insertData(root,4);
       root =  insertData(root,8);
       root =  insertData(root,3);
       root =  insertData(root,7);
       root =  insertData(root,11);
        System.out.println(root);

        root = deleteKey(root,8);
        System.out.println(root);
    }

    public static <T extends Comparable<T>> BinaryTreeNode<T>  insertData(BinaryTreeNode<T> root,T key){
        if(root==null){
            return new BinaryTreeNode<>(key);
        }
        int compare = root.data.compareTo(key);
        if(compare ==0){
            System.out.println("key already present");
             return root;
         }else if(compare<0){
            root.right = insertData(root.right,key);
        }else {
            root.left = insertData(root.left,key);
        }
        return root;
    }
    public static <T extends Comparable<T>> BinaryTreeNode<T> deleteKey(BinaryTreeNode<T> root,T key){
        return  deleteKey(root,null,key);
    }

    private static <T extends Comparable<T>> BinaryTreeNode<T> deleteKey(BinaryTreeNode<T> root,
                                                                         BinaryTreeNode<T> parent, T key) {
        if(root==null){
            System.out.println("key not present");
            return root;
        }
        int compare = root.data.compareTo(key);
        if(compare ==0){
            if(root.left==null){
                return root.right;
            }
            if(root.right==null){
                return root.left;
            }
            BinaryTreeNode<T> minNode = BinaryTreeUtils.getMinNode(root.right);
            root.right= deleteKey(root.right,minNode.data);
            copyChildrenAndParentPointers(root, parent, minNode);
            return minNode;


        }else if(compare<0){
            root.right = deleteKey(root.right,root,key);
        }else {
            root.left = deleteKey(root.left,root,key);
        }
        return root;
    }

    private static <T extends Comparable<T>> void copyChildrenAndParentPointers(BinaryTreeNode<T> root, BinaryTreeNode<T> parent, BinaryTreeNode<T> minNode) {
        minNode.left = root.left;
        minNode.right = root.right;
        root.left=null;
        root.right=null;
        if(parent !=null){
            if(parent.left== root){
                parent.left= minNode;
            }else{
                parent.right= minNode;
            }
        }
    }

}
