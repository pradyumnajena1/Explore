package epp.binaryTree;

public class FindKThNodeInOrderTraversalWithSize {
    public static void main(String[] args) {
        BinaryTreeNodeWithSize<Integer> root = new BinaryTreeNodeWithSize<>(8,
                new BinaryTreeNodeWithSize<>(5,new BinaryTreeNodeWithSize<>(3,new BinaryTreeNodeWithSize<>(0),
                        new BinaryTreeNodeWithSize<>(2)),
                        new BinaryTreeNodeWithSize<>(17,null,new BinaryTreeNodeWithSize<>(12,new BinaryTreeNodeWithSize<>(1),null))),
                new BinaryTreeNodeWithSize<>(21,new BinaryTreeNodeWithSize<>(10,new BinaryTreeNodeWithSize<>(14,null,new BinaryTreeNodeWithSize<>(19)),
                        new BinaryTreeNodeWithSize<>(4)),
                        new BinaryTreeNodeWithSize<>(7,null,new BinaryTreeNodeWithSize<>(8)))
        );
        System.out.println(root);
        root.fillSize();
        System.out.println(root);

        BinaryTreeNodeWithSize<Integer> node = findKthNodeInOrderTraverse(root,7);
        System.out.println(node);

    }

    private static BinaryTreeNodeWithSize<Integer> findKthNodeInOrderTraverse(BinaryTreeNodeWithSize<Integer> root, int i) {
         if(root==null ){
             return null;
         }

         if(root.left!=null && root.left.size>=i){
             return findKthNodeInOrderTraverse(root.left,i);
         }else if( ( root.left!=null? root.left.size:0 )+1==i){
             return root;
         }else if(root.right!=null ){
             return findKthNodeInOrderTraverse(root.right,i-1-(root.left!=null?root.left.size:0));
         }
         return null;
    }



}
