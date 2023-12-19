package epp.binaryTree;

public class BinaryTreeNodeWithSizeAndParent<T extends Comparable<T>>{
    public T data;
    public BinaryTreeNodeWithSizeAndParent<T> left;
    public BinaryTreeNodeWithSizeAndParent<T> right;
    public BinaryTreeNodeWithSizeAndParent<T> parent;
    public int size;
    public boolean locked;

    public BinaryTreeNodeWithSizeAndParent(T data) {
        this.data = data;
    }

    public BinaryTreeNodeWithSizeAndParent(T data, BinaryTreeNodeWithSizeAndParent<T> left, BinaryTreeNodeWithSizeAndParent<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        if(this.left!=null){
           this.left.parent = this;
        }
        if(this.right!=null){
          this.right.parent = this;
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BinaryTreeWithSizeAndParent{");
        sb.append("data=").append(data);
        sb.append(", size=").append(size);
        sb.append(", locked=").append(locked);
        sb.append('}');
        return sb.toString();
    }
}
