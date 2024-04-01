package amaze;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MaximumDifferenceBetweenNodeAndItsAncestor {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(8,
                new TreeNode(3,
                        new TreeNode(1),
                        new TreeNode(6,
                                new TreeNode(4),
                                new TreeNode(7))),
                new TreeNode(10, null, new TreeNode(14, new TreeNode(13), null)));
        System.out.println(findMaxDiff(root));
        System.out.println(findMaxDiff2(root));
    }

    private static int findMaxDiff(TreeNode root) {
        AtomicInteger result = new AtomicInteger(Integer.MIN_VALUE);
        MaxStackImpl maxStack = new MaxStackImpl();
        findMaxDiff(root, maxStack, result);
        return result.get();
    }

    private static void findMaxDiff(TreeNode root, MaxStackImpl maxStack, AtomicInteger result) {
        if(root==null){
            return;
        }
        int diff = maxStack.isEmpty()?0 : maxStack.max() - root.data;
        if(diff > result.get()){
            result.set(diff);
        }
        maxStack.push(root.data);
        findMaxDiff(root.left,maxStack,result);
        findMaxDiff(root.right,maxStack,result);
        maxStack.pop();
    }


    private static int findMaxDiff2(TreeNode root) {
        AtomicInteger result = new AtomicInteger(Integer.MIN_VALUE);

        findMaxDiff(root,result);
        return result.get();
    }

    private static int findMaxDiff(TreeNode root,  AtomicInteger result) {
        if(root==null){
            return Integer.MAX_VALUE;
        }
        int min = Math.min(findMaxDiff(root.left,result), findMaxDiff(root.right,result));
        if(result.get()< root.data-min){
            result.set(root.data-min);
        }
        return Math.min(root.data, min);
    }
}
