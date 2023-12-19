package leetcode.hard;

import java.util.concurrent.atomic.AtomicInteger;

public class BinaryTreeMaxpath {
    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {

        System.out.println(maxPathSum(new TreeNode(-2,new TreeNode(-1),null)));
    }
    public static int maxPathSum(TreeNode root) {
        if(root==null){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        if(root.left!=null){
            max = Math.max(max,maxPathSum(root.left));
        }
        if(root.right!=null){
            max = Math.max(max,maxPathSum(root.right));
        }
        int maxThroughRoot = root.val;
        maxThroughRoot = Math.max(maxThroughRoot,maxThroughRoot+maxPathFrom(root.left));
        maxThroughRoot = Math.max(maxThroughRoot,maxThroughRoot+maxPathFrom(root.right));


        return Math.max(maxThroughRoot,  max);
    }
    public static int maxPathFrom(TreeNode root){
         if(root==null){
             return 0;
         }
        AtomicInteger pathSum = new AtomicInteger(0);
        AtomicInteger maxSum = new AtomicInteger(Integer.MIN_VALUE);
        maxPathFromRecursive(root, pathSum,maxSum);
        return maxSum.get();
    }

    private static void maxPathFromRecursive(TreeNode root, AtomicInteger pathSum, AtomicInteger maxSum) {
        if(root==null){
            return;
        }
        pathSum.addAndGet(root.val);
        maxSum.set(Math.max(maxSum.get(),pathSum.get()));
        maxPathFromRecursive(root.left,pathSum, maxSum);
        maxPathFromRecursive(root.right,pathSum, maxSum);
        pathSum.addAndGet(-root.val);
    }
}
