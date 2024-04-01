package amaze;

import epp.Pair;

public class ConvertTreeToDLL {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10,
                new TreeNode(12,
                        new TreeNode(25),
                        new TreeNode(30)),
                new TreeNode(15,
                        new TreeNode(36),
                        null));
        TreeNode head = convertTreeToDLL(root);
        printList(head);
    }

    private static void printList(TreeNode head) {
        while (head != null) {
            System.out.println(head.data);
            head = head.right;
        }
    }

    private static TreeNode convertTreeToDLL(TreeNode root) {
        Pair<TreeNode, TreeNode> list = convertTreeToDLLHelper(root);
        return list.getFirst();
    }

    private static Pair<TreeNode, TreeNode> convertTreeToDLLHelper(TreeNode root) {
        if (root == null) {
            return null;
        }
        Pair<TreeNode, TreeNode> result = new Pair<>(root, root);
        if (root.left != null) {
            Pair<TreeNode, TreeNode> leftList = convertTreeToDLLHelper(root.left);
            result = mergeList(leftList, result);
        }
        if (root.right != null) {
            Pair<TreeNode, TreeNode> rightList = convertTreeToDLLHelper(root.right);
            result = mergeList(result, rightList);
        }
        return result;
    }

    private static Pair<TreeNode, TreeNode> mergeList(Pair<TreeNode, TreeNode> leftList,
                                                      Pair<TreeNode, TreeNode> rightList) {

        leftList.getSecond().right = rightList.getFirst();
        rightList.getFirst().left = leftList.getSecond();
        Pair<TreeNode, TreeNode> result = new Pair<>(leftList.getFirst(), rightList.getSecond());
        return result;
    }
}
