package epp.binaryTree;

public class FindKthUnbalncedNode {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(4,
                new BinaryTreeNode<>(2,new BinaryTreeNode<>(1),new BinaryTreeNode<>(3)),
                new BinaryTreeNode<>(6,new BinaryTreeNode<>(5),new BinaryTreeNode<>(7,
                        null,new BinaryTreeNode<>(8,null,new BinaryTreeNode<>(9))))
        );
        System.out.println(root);
        Result result =   findKthUnbalancedNode(root,2);
        System.out.println(result.node);

    }

    private static Result findKthUnbalancedNode(BinaryTreeNode<Integer> root, int k) {
        if(root==null){
            return new Result(null,0);
        }
        Result left = findKthUnbalancedNode(root.left, k);
        if(left.node!=null){
            return left;
        }
        Result right = findKthUnbalancedNode(root.right, k);
        if(right.node!=null){
            return right;
        }
        if(Math.abs(left.size- right.size)>=k){
            return new Result(root, left.size+ right.size+1) ;
        }
        return new Result(null, left.size+ right.size+1);
    }

    private static class Result{
        BinaryTreeNode<Integer> node;
        int size;

        public Result(BinaryTreeNode<Integer> node, int size) {
            this.node = node;
            this.size = size;
        }
    }
}
