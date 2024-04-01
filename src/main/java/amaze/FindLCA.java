package amaze;

import java.util.concurrent.atomic.AtomicBoolean;

public class FindLCA {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10,
                new TreeNode(12,
                        new TreeNode(25),
                        new TreeNode(30)),
                new TreeNode(15,
                        new TreeNode(36),
                        null));

        TreeNode lca = findLCA(root, 23, 36);
        if (lca != null) {
            System.out.println(lca.data);
        }

        lca = findLCA2(root, 25, 36);
        if (lca != null) {
            System.out.println(lca.data);
        }

    }

    /**
     * return lca, if any of the node, a or b present
     *
     * @param root
     * @param a
     * @param b
     * @return
     */
    public static TreeNode findLCA(TreeNode root, int a, int b) {
        if (root == null) {
            return null;
        }
        if (root.data == a || root.data == b) {
            return root;
        }
        TreeNode lca_left = findLCA(root.left, a, b);
        TreeNode lca_right = findLCA(root.right, a, b);
        if (lca_left != null && lca_right != null) {
            return root;
        }
        return lca_left != null ? lca_left : lca_right;
    }

    /**
     * returns lca when both a and b present
     *
     * @param root
     * @param a
     * @param b
     * @return
     */
    public static TreeNode findLCA2(TreeNode root, int a, int b) {
        AtomicBoolean aPresent = new AtomicBoolean(false);
        AtomicBoolean bPresent = new AtomicBoolean(false);
        TreeNode lca = findLCA2(root, a, b, aPresent, bPresent);
        if (aPresent.get() && bPresent.get()) {
            return lca;
        }
        return null;
    }

    private static TreeNode findLCA2(TreeNode root, int a, int b, AtomicBoolean a_present, AtomicBoolean b_present) {
        if (root == null) {
            return null;
        }

        if (root.data == a) {
            a_present.set(true);
        }
        if (root.data == b) {
            b_present.set(true);
        }

        TreeNode lca_left = findLCA2(root.left, a, b, a_present, b_present);
        TreeNode lca_right = findLCA2(root.right, a, b, a_present, b_present);

        if (root.data==a || root.data==b) {
            return root;
        }
        if (lca_left != null && lca_right != null) {
            return root;
        }
        return lca_left != null ? lca_left : lca_right;

    }
}
