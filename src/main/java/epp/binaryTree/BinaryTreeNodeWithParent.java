package epp.binaryTree;

import java.util.Objects;

public class BinaryTreeNodeWithParent<T extends Comparable<T>>{
    public T data;
    public BinaryTreeNodeWithParent<T> left;
    public BinaryTreeNodeWithParent<T> right;
    public BinaryTreeNodeWithParent<T> parent;

    public BinaryTreeNodeWithParent(T data) {
        this.data=data;
    }
    public BinaryTreeNodeWithParent(T data, BinaryTreeNodeWithParent<T> left, BinaryTreeNodeWithParent<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;

      if(  left!=null)left.parent=this;
      if(  right!=null)right.parent=this;


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

    @Override
    public String toString() {

        return   BTreeWithParentPrinter.getNodeAsString(this);

    }


}
