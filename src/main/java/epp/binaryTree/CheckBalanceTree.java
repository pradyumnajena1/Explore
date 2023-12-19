package epp.binaryTree;

public class CheckBalanceTree {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(4,
                                                    new BinaryTreeNode<>(2,new BinaryTreeNode<>(1),new BinaryTreeNode<>(3)),
                                                    new BinaryTreeNode<>(6,new BinaryTreeNode<>(5),new BinaryTreeNode<>(7,
                                                            null,new BinaryTreeNode<>(8,null,new BinaryTreeNode<>(9))))
                                           );
        System.out.println(root);

        boolean balanced  = checkBalanced(root);
        System.out.println(balanced);
    }

    private static boolean checkBalanced(BinaryTreeNode<Integer> root) {
        if(root==null){
            return true;
        }
        int height = getHeight(root);
        if(height==-2){
            return false;
        }
        return true;
    }

    private static int getHeight(BinaryTreeNode<Integer> root) {
        if(root==null){
            return -1;
        }
        int left = getHeight(root.left);
        if(left==-2){
            return -2;
        }
        int right = getHeight(root.right);
        if(right==-2){
            return -2;
        }
        if(Math.abs(left-right)>1){
            return -2;
        }
        return Math.max(left,right)+1;
    }
}
