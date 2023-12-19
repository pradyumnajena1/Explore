package epp.binaryTree;

public class InOrderNextNode {
    public static void main(String[] args) {
        BinaryTreeNodeWithParent<Integer> root = new BinaryTreeNodeWithParent<>(8,
                new BinaryTreeNodeWithParent<>(5,new BinaryTreeNodeWithParent<>(3,new BinaryTreeNodeWithParent<>(0),
                        new BinaryTreeNodeWithParent<>(2)),
                        new BinaryTreeNodeWithParent<>(17,null,new BinaryTreeNodeWithParent<>(12,new BinaryTreeNodeWithParent<>(1),null))),
                new BinaryTreeNodeWithParent<>(21,new BinaryTreeNodeWithParent<>(10,new BinaryTreeNodeWithParent<>(14,null,new BinaryTreeNodeWithParent<>(19)),
                        new BinaryTreeNodeWithParent<>(4)),
                        new BinaryTreeNodeWithParent<>(7,null,new BinaryTreeNodeWithParent<>(8)))
        );
        System.out.println(root);
        BinaryTreeNodeWithParent<Integer> nextNode = getInorderNextNode(root.right.left.right);
        System.out.println( nextNode==null?null: nextNode.data);

    }

    private static BinaryTreeNodeWithParent<Integer> getInorderNextNode(BinaryTreeNodeWithParent<Integer> node) {
        if(node.right!=null){
            return minNode(node.right);
        }
        BinaryTreeNodeWithParent current  = node;
        BinaryTreeNodeWithParent parent  = node.parent;
        while (parent!=null && current!=parent.left){
            current = parent;
            parent = parent.parent;
        }

        return parent;
    }

    private static BinaryTreeNodeWithParent<Integer> minNode(BinaryTreeNodeWithParent<Integer> node) {
        BinaryTreeNodeWithParent<Integer> current = node;
        while (current.left!=null){
            current = current.left;
        }
        return current;
    }
}
