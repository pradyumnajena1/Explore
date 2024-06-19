package epp.binaryTree;

public class NextSiblingFromPerfectBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNodeWithNext<Integer> root = new BinaryTreeNodeWithNext<>(8,
                new BinaryTreeNodeWithNext<>(5,new BinaryTreeNodeWithNext<>(3),
                        new BinaryTreeNodeWithNext<>(17)),
                new BinaryTreeNodeWithNext<>(21,new BinaryTreeNodeWithNext<>(10 ),
                        new BinaryTreeNodeWithNext<>(7))
        );
        System.out.println(root);



    }


}
