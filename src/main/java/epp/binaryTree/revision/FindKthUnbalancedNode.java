package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;

public class FindKthUnbalancedNode {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(4,
                new BinaryTreeNode<>(2,new BinaryTreeNode<>(1),new BinaryTreeNode<>(3)),
                new BinaryTreeNode<>(6,new BinaryTreeNode<>(5),new BinaryTreeNode<>(7,
                        new BinaryTreeNode<>(9),new BinaryTreeNode<>(8,null,null)))
        );
        System.out.println(root);
        Result kthUnbalancedNode = getKthUnbalancedNode(root, 1);
        System.out.println(kthUnbalancedNode);
    }
    private static class Result{
        BinaryTreeNode<Integer> node;
        int size;

        public Result(BinaryTreeNode<Integer> node, int size) {
            this.node = node;
            this.size = size;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(node!=null?node.data:null);
            stringBuilder.append("size="+size);
            return stringBuilder.toString();
        }
    }

    public static Result getKthUnbalancedNode(BinaryTreeNode<Integer> root, int k){
        if(root==null){
            return new Result(null,0);
        }
        Result leftSize = getKthUnbalancedNode(root.left,k);
        if(leftSize.node!=null){
            return leftSize;
        }
        Result rightSize = getKthUnbalancedNode(root.right,k);
        if(rightSize.node!=null){
            return rightSize;
        }
        int size = leftSize.size + rightSize.size + 1;

        if(Math.abs(leftSize.size- rightSize.size)>k){
            return new Result(root, size);
        }
        return new Result(null, size);
    }
}
