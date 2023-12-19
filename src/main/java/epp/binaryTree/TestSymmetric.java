package epp.binaryTree;

public class TestSymmetric {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>( 314,
                new BinaryTreeNode<>(6,null,new BinaryTreeNode<>(2,null,new BinaryTreeNode<>(3))),
                new BinaryTreeNode<>(6,new BinaryTreeNode<>(2,new BinaryTreeNode<>(3),null),null));

        System.out.println(root);
        boolean symmetric = testSymmetry(root);
        System.out.println(symmetric);

    }

    private static boolean testSymmetry(BinaryTreeNode<Integer> root) {
        if(root==null){
            return true;
        }

        return isMirrorImage(root.left,root.right);
    }

    private static boolean isMirrorImage(BinaryTreeNode<Integer> root1, BinaryTreeNode<Integer> root2) {
        if(root1==null && root2==null){
            return true;
        }
        if(root1==null && root2!=null || root2==null && root1!=null){
            return false;
        }
        if(root1.data!=root2.data){
            return false;
        }

        return isMirrorImage( root1.left,root2.right) && isMirrorImage(root1.right,root2.left);
    }
}
