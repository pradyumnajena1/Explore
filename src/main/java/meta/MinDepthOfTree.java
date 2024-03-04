package meta;

public class MinDepthOfTree {

    public static void main(String[] args) {
        System.out.println(getMinDepth(new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3))));
        System.out.println(getMinDepth( new TreeNode(10,new TreeNode(5),null) ));

    }

    private static int getMinDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        if(root.left==null){
            return 1+ getMinDepth(root.right);
        }
        if(root.right==null){
            return 1+getMinDepth(root.left);
        }
        return 1 + Math.min(getMinDepth(root.left),getMinDepth(root.right));
    }

    static class TreeNode{
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
}
