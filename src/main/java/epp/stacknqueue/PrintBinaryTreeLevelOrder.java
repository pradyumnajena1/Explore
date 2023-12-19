package epp.stacknqueue;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class PrintBinaryTreeLevelOrder {
    public static void main(String[] args) {
        TreeNode root =new TreeNode(4,
                new TreeNode(2,
                        new TreeNode(1),
                        new TreeNode(3)
                ),
                new TreeNode(6,
                        new TreeNode(5),
                        new TreeNode(7)
                )
        );
        printBinaryTreeLevelOrder(root);
    }

    private static void printBinaryTreeLevelOrder(TreeNode node) {
        if(node==null){
            return;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(node);
        int count  = queue.size();
        while (!queue.isEmpty()){
            TreeNode treeNode = queue.poll();
            System.out.print(treeNode.data+" ");
            if(treeNode.left!=null){
                queue.offer(treeNode.left);
            }
            if(treeNode.right!=null){
                queue.offer(treeNode.right);
            }

            if(--count==0){
                System.out.println();
                count = queue.size();
            }

        }

    }

    private static void printLevel(List<Integer> list) {
        System.out.println(list);

    }
}
