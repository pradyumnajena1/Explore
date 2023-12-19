package epp.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeUtils {
    public static void main(String[] args) {
        BinaryTreeNodeWithParent<Integer> root = new BinaryTreeNodeWithParent<>(8,
                new BinaryTreeNodeWithParent<>(5, new BinaryTreeNodeWithParent<>(3, new BinaryTreeNodeWithParent<>(0),
                        new BinaryTreeNodeWithParent<>(2)),
                        new BinaryTreeNodeWithParent<>(17, null, new BinaryTreeNodeWithParent<>(12, new BinaryTreeNodeWithParent<>(1), null))),
                new BinaryTreeNodeWithParent<>(21, new BinaryTreeNodeWithParent<>(10, new BinaryTreeNodeWithParent<>(14, null, new BinaryTreeNodeWithParent<>(19)),
                        new BinaryTreeNodeWithParent<>(4)),
                        new BinaryTreeNodeWithParent<>(7, null, new BinaryTreeNodeWithParent<>(8)))
        );
        System.out.println(root);
        System.out.println(preOrderTraversal(root));
        System.out.println(inOrderTraversal(root));
        System.out.println(postOrderTraversal(root));

    }

    public static <T extends Comparable<T>> List<T> preOrderTraversal(BinaryTreeNode<T> root) {
        List<T> collector = new ArrayList<>();
        preOrderTraversal(root, collector);
        return collector;
    }

    private static <T extends Comparable<T>> void preOrderTraversal(BinaryTreeNode<T> root, List<T> collector) {
        if (root != null) {
            collector.add(root.data);
            preOrderTraversal(root.left, collector);
            preOrderTraversal(root.right, collector);
        }
    }

    public static <T extends Comparable<T>> List<T> postOrderTraversal(BinaryTreeNode<T> root) {
        return postOrderTraversal(root, -1);
    }

    public static <T extends Comparable<T>> List<T> postOrderTraversal(BinaryTreeNode<T> root, int max) {
        List<T> collector = new ArrayList<>();
        postOrderTraversal(root, collector, max);
        return collector;
    }

    private static <T extends Comparable<T>> void postOrderTraversal(BinaryTreeNode<T> root, List<T> collector,
                                                                     int max) {
        if (root != null) {
            postOrderTraversal(root.left, collector, max);
            postOrderTraversal(root.right, collector, max);
            if (max > 0 && collector.size() >= max) {
                return;
            }
            collector.add(root.data);
        }
    }

    public static <T extends Comparable<T>> List<T> preOrderTraversal(BinaryTreeNodeWithParent<T> root) {
        ArrayList<T> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        BinaryTreeNodeWithParent<T> current = root;
        while (current != null) {
            list.add(current.data);
            current = getPreOrderSuccessor(current);
        }
        return list;
    }

    public static <T extends Comparable<T>> List<T> inOrderTraversal(BinaryTreeNodeWithParent<T> root) {
        ArrayList<T> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        BinaryTreeNodeWithParent<T> current = getMinNode(root);
        while (current != null) {
            list.add(current.data);
            current = getInOrderSuccessor(current);
        }
        return list;
    }

    public static <T extends Comparable<T>> List<T> postOrderTraversal(BinaryTreeNodeWithParent<T> root) {
        ArrayList<T> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        BinaryTreeNodeWithParent<T> current = getMinNode(root);
        while (current != null) {
            list.add(current.data);
            current = getPostOrderSuccessor(current);
        }
        return list;
    }

    /**
     * node->left->right
     *
     * @param root
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> BinaryTreeNodeWithParent<T> getPreOrderSuccessor(BinaryTreeNodeWithParent<T> root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            return root.left;
        }
        if (root.right != null) {
            return root.right;
        }
        BinaryTreeNodeWithParent<T> current = root;
        BinaryTreeNodeWithParent<T> parent = root.parent;

        while (parent != null && (current == parent.right || parent.right == null)) {
            current = parent;
            parent = parent.parent;
        }
        // reached root
        if (parent == null) {
            return null;
        }
        return parent.right;

    }

    /**
     * left->node->right
     *
     * @param root
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> BinaryTreeNodeWithParent<T> getInOrderSuccessor(BinaryTreeNodeWithParent<T> root) {
        if (root.right != null) {
            return getMinNode(root.right);
        }
        BinaryTreeNodeWithParent<T> current = root;
        BinaryTreeNodeWithParent<T> parent = root.parent;
        while (parent != null && current == parent.right) {
            current = parent;
            parent = parent.parent;
        }
        return parent;
    }

    /**
     * left->right->node
     *
     * @param root
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> BinaryTreeNodeWithParent<T> getPostOrderSuccessor(BinaryTreeNodeWithParent<T> root) {
        if (root == null) {
            return null;
        }
        BinaryTreeNodeWithParent<T> current = root;
        BinaryTreeNodeWithParent<T> parent = root.parent;
        // reached root
        if (parent == null) {
            return null;
        }
        if (parent.right == current || parent.right == null) {
            return parent;
        }
        current = parent.right;
        current = getDeepestChild(current);
        return current;

    }

    public static <T extends Comparable<T>> BinaryTreeNodeWithParent<T> getDeepestChild(BinaryTreeNodeWithParent<T> root) {
        BinaryTreeNodeWithParent<T> current = root;
        while (current != null && (current.left != null || current.right != null)) {
            current = current.left != null ? current.left : current.right;

        }
        return current;
    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> getDeepestChild(BinaryTreeNode<T> root) {
        BinaryTreeNode<T> current = root;
        while (current != null && (current.left != null || current.right != null)) {
            current = current.left != null ? current.left : current.right;

        }
        return current;
    }

    public static <T extends Comparable<T>> BinaryTreeNodeWithParent<T> getMinNode(BinaryTreeNodeWithParent<T> root) {
        if (root == null) {
            return null;
        }
        BinaryTreeNodeWithParent<T> min = root;
        while (min.left != null) {
            min = min.left;
        }
        return min;
    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> getMinNode(BinaryTreeNode<T> root) {
        if (root == null) {
            return null;
        }
        BinaryTreeNode<T> min = root;
        while (min.left != null) {
            min = min.left;
        }
        return min;
    }

    public static <T extends Comparable<T>> List<T> inOrderTraversal(BinaryTreeNode<T> root) {
        List<T> collector = new ArrayList<>();
        inOrderTraversal(root, collector);
        return collector;
    }


    private static <T extends Comparable<T>> void inOrderTraversal(BinaryTreeNode<T> root, List<T> collector) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, collector);
        collector.add(root.data);
        inOrderTraversal(root.right, collector);
    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> getMaxNode(BinaryTreeNode<T> root) {
        if (root == null) {
            return null;
        }
        BinaryTreeNode<T> max = root;
        while (max.right != null) {
            max = max.right;
        }
        return max;
    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> findFirstNodeEqualOrBigger(BinaryTreeNode<T> root,
                                                                                         T value) {
        BinaryTreeNode<T> node = null;
        while (root != null) {
            int compare = root.data.compareTo(value);
            if (compare < 0) {
                root = root.right;
            } else if (compare == 0) {
                node = root;
                root = root.left;
            } else {
                node = root;
                root = root.left;
            }
        }
        return node;
    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> findLastNodeEqualOrSmaller(BinaryTreeNode<T> root
            , T low) {
        BinaryTreeNode<T> node = null;
        while (root != null) {
            int compare = root.data.compareTo(low);
            if (compare < 0) {
                node = root;
                root = root.right;
            } else if (compare == 0) {
                node = root;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return node;
    }

    // find the next biggest node , tree have duplicates packed to right
    public static <T extends Comparable<T>> BinaryTreeNode<T> getInOrderSuccessor(BinaryTreeNode<T> root,
                                                               BinaryTreeNode<T> node) {
        if (node == null || root == null) {
            return null;
        }
        if (node.right != null) {
            return BinaryTreeUtils.getMinNode(node.right);
        }
        // find the next biggest node
        BinaryTreeNode<T> successor = null;
        while (root != null) {
            int compare = root.data.compareTo(node.data);
            if (compare > 0) {
                successor = root;
                root = root.left;
            } else if (compare < 0) {
                root = root.right;
            } else {
                // tree have duplicates , so check if we reach same node
                // this node hv no right child
                if (root == node) {
                    break;
                }
                root = root.right;

            }
        }
        return successor;
    }
    public static <T extends Comparable<T>> int getHeight(BinaryTreeNode<T> root){
        if(root==null){
            return 0;
        }
        return 1 + Math.max(getHeight(root.left),getHeight(root.right));
    }

    public static <T extends Comparable<T>> int getSize(BinaryTreeNode<T> root){
        if(root==null){
            return 0;
        }
        return 1 +  getSize(root.left)+getSize(root.right);
    }

}
