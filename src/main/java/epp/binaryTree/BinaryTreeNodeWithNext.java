package epp.binaryTree;

public class BinaryTreeNodeWithNext<T extends Comparable<T>> extends BinaryTreeNode<T>{

    public BinaryTreeNodeWithNext<T> next;

    public BinaryTreeNodeWithNext(T data) {
        super(data);
    }

    public BinaryTreeNodeWithNext(T data, BinaryTreeNodeWithNext<T> left, BinaryTreeNodeWithNext<T> right) {
       super(data,left,right);
    }
    @Override
    public String toString() {

        return   BTreePrinter.getNodeAsString(this);

    }
}
