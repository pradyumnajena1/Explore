package leetcode.medium;

import meta.PrintBinaryTreeLevelwise;

import javax.swing.tree.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * A binary tree is named Even-Odd if it meets the following conditions:
 * <p>
 * The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
 * For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
 * For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).
 * Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false
 */
public class EvenOddTree {

    public static void main(String[] args) {
        boolean evenOddTree = Solution.isEvenOddTree(Solution.createTree());
        System.out.println(evenOddTree);

        evenOddTree = Solution.isEvenOddTree2(Solution.createTree());
        System.out.println(evenOddTree);
    }




    static class Solution {
        private static  TreeNode createTree() {
            return new TreeNode(1,
                    new TreeNode(10,new TreeNode(3,new TreeNode(12),new TreeNode(8)),null),
                    new TreeNode(4,new TreeNode(7,new TreeNode(6),null),new TreeNode(9,null,new TreeNode(2))));
        }
        public static boolean isEvenOddTree2(TreeNode root) {
            List<TreeNode> list = new ArrayList<>();
            int level = 0;
            while (!list.isEmpty()){
                if(!validLevel(list,level)){
                    return false;
                }
                List<TreeNode> nextList = new ArrayList<>();
                for(TreeNode node:list){
                    if(node.left!=null){
                        nextList.add(node.left);
                    }
                    if(node.right!=null){
                        nextList.add(node.right);
                    }
                }
                list = nextList;
                level++;
            }
            return true;
        }

        private static boolean validLevel(List<TreeNode> list, int level) {
            for(int i=0;i<list.size();i++){
                if(level%2==0 && list.get(i).val%2==0){
                    return false;
                }
                if(level%2==1 && list.get(i).val%2==1){
                    return false;
                }
                if(i>0 ){
                    int lastValue = list.get(i - 1).val;
                    int currentValue = list.get(i).val;
                    if(level%2==0 && lastValue >= currentValue){
                        return false;
                    }
                    if(level%2==1 && lastValue  <= currentValue){
                        return false;
                    }
                }
            }
            return true;
        }

        public static boolean isEvenOddTree(TreeNode root) {
            int level = 0;
            Queue<TreeNode> bfsQueue = new ArrayDeque<>();
            bfsQueue.offer(root);
            int count = 1;
            List<Integer> list = new ArrayList<>();
            while (!bfsQueue.isEmpty()) {
                TreeNode polled = bfsQueue.poll();
                if (level % 2 == 0 && polled.val % 2 == 0) {
                    return false;
                }
                if (level % 2 == 1 && polled.val % 2 == 1) {
                    return false;
                }

                if (list.isEmpty()) {
                    list.add(polled.val);
                } else {
                    int lastValue = list.get(list.size() - 1);
                    if (level % 2 == 1 && lastValue <= polled.val) {
                        return false;
                    }
                    if (level % 2 == 0 && lastValue >= polled.val) {
                        return false;
                    }
                    list.add(polled.val);
                }
                if (polled.left != null) {
                    bfsQueue.offer(polled.left);
                }
                if (polled.right != null) {
                    bfsQueue.offer(polled.right);
                }
                count--;
                if (count == 0) {
                    count = bfsQueue.size();
                    list = new ArrayList<>();
                    level++;
                }

            }
            return true;
        }

        private static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }
    }
}
