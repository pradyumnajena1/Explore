package meta;

import epp.Pair;

public class BinaryTreeToDoublyLinkedList {

    public static void main(String[] args) {
        TreeNode root = buildTree();
        DoubleLinkListNode list = convertToDoubleLinkList(root);
        printList(list);
    }

    private static void printList(DoubleLinkListNode list) {
        while(list!=null){
            System.out.println(list.data);
            list=list.next;
        }
    }

    private static DoubleLinkListNode convertToDoubleLinkList(TreeNode root) {
        Pair<DoubleLinkListNode, DoubleLinkListNode> list = convertToDoubleLinkListHelper(root);
        return list.getFirst();
    }

    private static Pair<DoubleLinkListNode, DoubleLinkListNode> convertToDoubleLinkListHelper(TreeNode root) {
        if (root == null) {
            return null;
        }
        Pair<DoubleLinkListNode, DoubleLinkListNode> left = convertToDoubleLinkListHelper(root.left);
        Pair<DoubleLinkListNode, DoubleLinkListNode> right = convertToDoubleLinkListHelper(root.right);
        DoubleLinkListNode node = new DoubleLinkListNode(root.data);
        Pair<DoubleLinkListNode, DoubleLinkListNode> result = new Pair<>(node, node);
        if (left != null) {
            left.getSecond().next = node;
            node.prev = left.getSecond();
            result.setFirst(left.getFirst());
        }
        if (right != null) {
            node.next = right.getFirst();
            right.getFirst().prev = node;
            result.setSecond(right.getSecond());
        }

        return result;
    }

    private static TreeNode buildTree() {
        return new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(6, new TreeNode(5), new TreeNode(7)));
    }


    private static class TreeNode {
        int data;
        TreeNode left, right;

        public TreeNode(int data) {
            this.data = data;
        }

        public TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private static class DoubleLinkListNode {
        int data;
        DoubleLinkListNode prev, next;

        public DoubleLinkListNode(int data) {
            this.data = data;
        }


    }

}
