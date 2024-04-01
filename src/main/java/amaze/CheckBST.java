package amaze;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CheckBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(5));
        System.out.println(isBST(root));
        System.out.println(isBST2(root));
    }

    private static boolean isBST(TreeNode root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBST(TreeNode root, int minValue, int maxValue) {
        if (root == null) {
            return true;
        }
        if (root.data < minValue || root.data > maxValue) {
            return false;
        }
        return isBST(root.left, minValue, root.data - 1) && isBST(root.right, root.data + 1, maxValue);
    }

    private static boolean isBST2(TreeNode root){
        return isBST2(root,new AtomicReference<Integer>(null));
    }

    private static boolean isBST2(TreeNode root, AtomicReference<Integer> prev) {
        if(root==null){
            return true;
        }
        if(!isBST2(root.left,prev)){
            return false;
        }
        if(prev.get()!=null&& root.data<= prev.get()){
            return false;
        }
        prev.set(root.data);
        return isBST2(root.right,prev);
    }
}
