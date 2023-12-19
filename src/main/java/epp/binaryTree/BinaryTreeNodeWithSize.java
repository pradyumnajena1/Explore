package epp.binaryTree;

import java.util.Objects;

public class BinaryTreeNodeWithSize<T extends Comparable<T>> {
    public int size = 0;
    public T data;
    public BinaryTreeNodeWithSize<T> left;
    public BinaryTreeNodeWithSize<T> right;

    public BinaryTreeNodeWithSize(T data) {
        this.data=data;
    }
    public BinaryTreeNodeWithSize(T data, BinaryTreeNodeWithSize<T> left, BinaryTreeNodeWithSize<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;


    }
    public BinaryTreeNodeWithSize(T data, BinaryTreeNodeWithSize<T> left, BinaryTreeNodeWithSize<T> right, int size) {
        this(data,left,right);
        this.size = size;

    }
    @Override
    public String toString() {

        return   BTreeWithSizePrinter.getNodeAsString(this);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTreeNodeWithSize<?> that = (BinaryTreeNodeWithSize<?>)   o;
        return data.equals(that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
    public void fillSize(){
        fillSize(this);
    }

    private void fillSize(BinaryTreeNodeWithSize<T> root) {
        if(root==null){
            return;
        }
        int totalSize = 1;
        if(root.left==null && root.right==null){
            root.size = totalSize;
            return;
        }
        if(root.left!=null){
            fillSize(root.left);
            totalSize+=root.left.size;
        }
        if(root.right!=null){
            fillSize(root.right);
            totalSize+=root.right.size;
        }

        root.size=totalSize;
    }
}
