package epp.binaryTree.revision;



public class ConstructRightSibling {

    public static void main(String[] args){
        BinaryTreeNodeWithNext<Integer> root = new BinaryTreeNodeWithNext<>(8,
                new BinaryTreeNodeWithNext<>(5,new BinaryTreeNodeWithNext<>(3),
                        new BinaryTreeNodeWithNext<>(17)),
                new BinaryTreeNodeWithNext<>(21,new BinaryTreeNodeWithNext<>(10 ),
                        new BinaryTreeNodeWithNext<>(7))
        );
        System.out.println(root);
        constructRightSibling(root);
        printLevelWiseUsingRightSibling(root);

    }

    private static void printLevelWiseUsingRightSibling(BinaryTreeNodeWithNext<Integer> root) {
        BinaryTreeNodeWithNext<Integer> leftStart = root;
        while(leftStart!=null){
            BinaryTreeNodeWithNext<Integer> iterator = leftStart;
            while(iterator!=null){
                System.out.print(" "+iterator.data);
                iterator = iterator.next;
            }
            leftStart = leftStart.left ;
      System.out.println();
        }
    }

    private static void constructRightSibling(BinaryTreeNodeWithNext<Integer> root) {

        BinaryTreeNodeWithNext<Integer> leftStart = root;
        while(leftStart!=null && leftStart.left!=null){
            populateLowerLevelNextField(leftStart);
            leftStart = leftStart.left ;
        }
    }

    private static void populateLowerLevelNextField(BinaryTreeNodeWithNext<Integer> leftStart) {
        BinaryTreeNodeWithNext<Integer> iterator = leftStart;
        while(iterator!=null){
            if(iterator.left!=null){
                iterator.left.next = iterator.right;
            }
            if(iterator.right!=null){
                iterator.right.next = iterator.next!=null?iterator.next.left:null;
            }
            iterator = iterator.next;
        }
    }

    private static class BinaryTreeNodeWithNext<T extends Comparable<T>>{
        T data;
        BinaryTreeNodeWithNext<T> left,right,next;

        public BinaryTreeNodeWithNext(T data) {
            this.data = data;
        }

        public BinaryTreeNodeWithNext(T data, BinaryTreeNodeWithNext<T> left, BinaryTreeNodeWithNext<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }


}
