package meta;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PrintBinaryTreeLevelwise {
    public static void main(String[] args) {
        TreeNode root = createTree();
        printTreeLevelwise(root);
    }

    private static void printTreeLevelwise(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int size=1;
        List<Integer> level=new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            level.add(poll.data);
            if(poll.left!=null)
                queue.offer(poll.left);
            if(poll.right!=null)
                queue.offer(poll.right);
            size--;
            if(size==0){
                System.out.println(level);
                level = new ArrayList<>();
                size=queue.size();
            }

        }
    }

    private static TreeNode createTree() {
        return new  TreeNode(4, new  TreeNode(2,
                new  TreeNode(1), new  TreeNode(3)),
                new  TreeNode(6, new  TreeNode(5),
                        new  TreeNode(7)));
    }


    private static class TreeNode{
        int data;
        TreeNode left,right;

        public TreeNode(int data) {
            this.data = data;
        }

        public TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
