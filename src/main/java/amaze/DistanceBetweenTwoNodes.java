package amaze;

import java.util.concurrent.atomic.AtomicInteger;

public class DistanceBetweenTwoNodes {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)),
                new TreeNode(3,
                        new TreeNode(6,
                                null,
                                new TreeNode(8)),
                        new TreeNode(7)));
        System.out.println(findDistance(root, 4, 5));
        System.out.println(findDistance(root, 4, 6));
        System.out.println(findDistance(root, 3, 4));
        System.out.println(findDistance(root, 2, 4));
        System.out.println(findDistance(root, 5, 8));
    }

    private static int findDistance(TreeNode root, int a, int b) {
        System.out.println("a = " + a + ", b = " + b);
        TreeNode lca = FindLCA.findLCA2(root, a, b);
        System.out.println(lca.data);
        int levela = findLevel(root, a);
        int levelb = findLevel(root, b);
        int levellca = findLevel(root, lca.data);

        return levela + levelb - 2 * levellca;
    }

    private static int findLevel(TreeNode root, int a) {

       return findLevel(root, a, 0 );

    }

    private static int findLevel(TreeNode root, int a, int level) {
        if (root == null) {
            return -1;
        }
        if (root.data == a) {
            return level;
        }
        int res = findLevel(root.left, a, level + 1);
        if (res == -1) {
            res = findLevel(root.right, a, level + 1);
        }
        return res;
    }
}
