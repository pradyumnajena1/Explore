package leetcode.hard;

import epp.binaryTree.BinaryTreeNode;

public class BinaryTreeCameras {
    public static void main(String[] args) {
        Solution solution = new Solution();
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(0,new BinaryTreeNode<Integer>(0,new BinaryTreeNode<Integer>(0),new BinaryTreeNode<Integer>(0)),null);
        System.out.println(solution.minCameraCover(root));
        System.out.println(root);

        root = new BinaryTreeNode<Integer>(0,new BinaryTreeNode<Integer>(0,new BinaryTreeNode<Integer>(0,new BinaryTreeNode<Integer>(0,null,new BinaryTreeNode<Integer>(0)),null),null),null );
        System.out.println(solution.minCameraCover(root));
        System.out.println(root);

        root =  new BinaryTreeNode<>(0);
                System.out.println(solution.minCameraCover(root));
        System.out.println(root);
    }

  /*  private static class TreeNode {
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
    }*/

    private static class Solution {
        public int minCameraCover(BinaryTreeNode<Integer> root) {
            return minCameraCover(root, null);
        }

        public int minCameraCover(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> parent) {
            if (root == null) {
                return 0;
            }
            if(root.left==null&&root.right==null){
                if (parent == null ) {
                    root.data = 1;
                    return 1;

                }else if (parent.data == 0) {
                    root.data = 1;
                    return 1;
                }else{
                    root.data=0;
                    return 0;
                }
            }
            if(parent!=null && parent.data==0){
                root.data = 1;
                int set = 1 + minCameraCover(root.left, root) + minCameraCover(root.right, root);
                return set;

            }else{
                root.data = 1;
                int set = 1 + minCameraCover(root.left, root) + minCameraCover(root.right, root);
                root.data = 0;
                int notSet = minCameraCover(root.left, root) + minCameraCover(root.right, root);
                if(set<=notSet){
                    root.data = 1;
                    return set;
                }else{
                    root.data = 0;
                    return notSet;
                }

            }


        }
    }
}
