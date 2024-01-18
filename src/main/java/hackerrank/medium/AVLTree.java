package hackerrank.medium;

import epp.binaryTree.BinaryTreeNode;

public class AVLTree<T extends Comparable<T>> {
    private BinaryTreeNode<AVLTreeData<T>> head;

    private int height(BinaryTreeNode<AVLTreeData<T>> head) {
        if (head == null) {
            return -1;
        }
        return head.data.height;
    }
    private int balance(BinaryTreeNode<AVLTreeData<T>> head){
        if (head == null) {
            return 0;
        }
        return height(head.left) - height(head.right);
    }
    public void insert(T value){
        head = insert(head,value);
    }

    private BinaryTreeNode<AVLTreeData<T>> insert(BinaryTreeNode<AVLTreeData<T>> head, T value) {
        AVLTreeData<T> treeData = new AVLTreeData<>(value);
        BinaryTreeNode<AVLTreeData<T>> newNode = new BinaryTreeNode<>(treeData);
        if(head==null){
            return newNode;
        }
        if(head.data.compareTo(treeData)<0){
            head.right = insert(head.right,value);
        }else{
            head.left = insert(head.left,value);
        }
        head.data.height = 1 + Math.max(height(head.left),height(head.right));
        head = rotate(head, treeData);

        return head;
    }

    private BinaryTreeNode<AVLTreeData<T>> rotate(BinaryTreeNode<AVLTreeData<T>> head, AVLTreeData<T> treeData) {

        int balance = balance(head);

        //right
        if(balance<-1){
            if(head.right.data.compareTo(treeData)<0){
                // right right
              head =  rotateLeft(head);
            }else{
                //right left
                head.right = rotateRight(head.right);
                head = rotateLeft(head);
            }
        }else if(balance>1){
            if(head.left.data.compareTo(treeData)<0){
                // left right
                head.left = rotateLeft(head.left);
                head = rotateRight(head);
            }else{
                //left left
                head = rotateRight(head);
            }
        }
        return head;
    }

    private BinaryTreeNode<AVLTreeData<T>> rotateRight(BinaryTreeNode<AVLTreeData<T>> x) {

        BinaryTreeNode<AVLTreeData<T>> y = x.left;
        BinaryTreeNode<AVLTreeData<T>> T2 = y.right;

        x.left = T2;
        y.right = x;

        x.data.height = 1 + Math.max(height(x.left),height(x.right));
        y.data.height = 1 + Math.max(height(y.left),height(y.right ));

        return y;
    }

    private BinaryTreeNode<AVLTreeData<T>> rotateLeft(BinaryTreeNode<AVLTreeData<T>> x) {
        BinaryTreeNode<AVLTreeData<T>> y = x.right;
        BinaryTreeNode<AVLTreeData<T>> T2 = y.left;

        x.right = T2;
        y.left = x;
        x.data.height = 1 + Math.max(height(x.left),height(x.right));
        y.data.height = 1 + Math.max(height(y.left),height(y.right ));
        return y;
    }

    private static  class AVLTreeData<T extends Comparable<T>> implements Comparable<AVLTreeData<T> >{
        T data;
        int height;

        public AVLTreeData(T data) {
            this.data = data;
            this.height =0;
        }

        @Override
        public int compareTo(AVLTreeData o) {
            return this.data.compareTo((T) o.data);
        }

        @Override
        public String toString() {
            return "{" +
                    "data=" + data +
                    ", height=" + height +
                    '}';
        }
    }

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        System.out.println(tree.head);
        tree.insert(6);
        System.out.println(tree.head);
    }

}
