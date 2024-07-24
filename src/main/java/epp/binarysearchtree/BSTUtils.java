package epp.binarysearchtree;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeNodeWithParent;
import epp.binaryTree.BinaryTreeNodeWithSize;
import epp.binaryTree.BinaryTreeUtils;
import epp.binarysearchtree.revision.MostVisitedPageTracker;

import java.util.Arrays;

public class BSTUtils {

    public static BinaryTreeNode<Integer> buildBST(int size, int min, int max) {
        int[] sortedArray = ArrayUtils.randomSortedArray(size, min, max);
        return buildBSTFromSortedArray(sortedArray);
    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> buildBSTFromSortedArray(T[] values) {
        return buildBSTFromSortedArray(values, 0, values.length - 1);
    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> buildBSTFromSortedArray(T[] values, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new BinaryTreeNode<T>(values[start]);
        }
        // to keep equals on left
        int mid = (start + end) / 2;
        while (mid < end && values[mid].compareTo(values[mid + 1]) == 0) {
            mid = mid + 1;
        }
        BinaryTreeNode<T> root = new BinaryTreeNode<>(values[mid]);
        root.left = buildBSTFromSortedArray(values, start, mid - 1);
        root.right = buildBSTFromSortedArray(values, mid + 1, end);

        return root;
    }

    public static BinaryTreeNode<Integer> buildBSTFromSortedArray(int[] sortedArray) {
        return buildBSTFromSortedArray(sortedArray, 0, sortedArray.length - 1);
    }

    public static BinaryTreeNode<Integer> buildBSTWithUniqueValues(int size, int min, int max) {
        int[] uniqueArray = ArrayUtils.randomUniqueArray(size, min, max);
        Arrays.sort(uniqueArray);
        return buildBSTFromSortedArray(uniqueArray);
    }

    public static BinaryTreeNodeWithSize<Integer> buildBSTWithSize(int size, int min, int max) {
        int[] sortedArray = ArrayUtils.randomArray(size, min, max);
        Arrays.sort(sortedArray);
        BinaryTreeNodeWithSize<Integer> root = buildBSTWithSizeFromSortedArray(sortedArray);
        return root;
    }

    public static BinaryTreeNodeWithParent<Integer> buildBSTWithParent(int size, int min, int max) {
        int[] sortedArray = ArrayUtils.randomArray(size, min, max);
        Arrays.sort(sortedArray);
        BinaryTreeNodeWithParent<Integer> root = buildBSTWithParentFromSortedArray(sortedArray);
        return root;
    }

    public static BinaryTreeNodeWithParent<Integer> buildBSTWithParentFromSortedArray(int[] sortedArray) {
        BinaryTreeNodeWithParent<Integer> root = buildBSTWithParentFromSortedArray(sortedArray, 0, sortedArray.length - 1);

        return root;
    }

    private static BinaryTreeNodeWithParent<Integer> buildBSTWithParentFromSortedArray(int[] sortedArray, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new BinaryTreeNodeWithParent<>(sortedArray[start]);
        }
        // to keep equals on right
        int mid = (start + end) / 2;
        while (mid-1 >=start   && sortedArray[mid] == sortedArray[mid - 1]) {
            mid = mid - 1;
        }
        BinaryTreeNodeWithParent<Integer> root = new BinaryTreeNodeWithParent<>(sortedArray[mid]);
        root.left = buildBSTWithParentFromSortedArray(sortedArray, start, mid - 1);
        if (root.left != null) {

            root.left.parent = root;
        }
        root.right = buildBSTWithParentFromSortedArray(sortedArray, mid + 1, end);
        if (root.right != null) {

            root.right.parent = root;
        }
        return root;
    }


    public static BinaryTreeNodeWithSize<Integer> buildBSTWithSizeWithUniqueValues(int size, int min, int max) {
        int[] uniqueArray = ArrayUtils.randomUniqueArray(size, min, max);
        Arrays.sort(uniqueArray);
        BinaryTreeNodeWithSize<Integer> root = buildBSTWithSizeFromSortedArray(uniqueArray);
        return root;
    }

    public static BinaryTreeNodeWithParent<Integer> buildBSTWithParentWithUniqueValues(int size, int min, int max) {
        int[] uniqueArray = ArrayUtils.randomUniqueArray(size, min, max);
        Arrays.sort(uniqueArray);
        BinaryTreeNodeWithParent<Integer> root = buildBSTWithParentFromSortedArray(uniqueArray);
        return root;
    }

    private static BinaryTreeNodeWithSize<Integer> buildBSTWithSizeFromSortedArray(int[] uniqueArray) {
        BinaryTreeNodeWithSize<Integer> root = buildBSTWithSizeFromSortedArray(uniqueArray, 0, uniqueArray.length - 1);
        root.fillSize();
        return root;
    }

    private static BinaryTreeNodeWithSize<Integer> buildBSTWithSizeFromSortedArray(int[] sortedArray, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new BinaryTreeNodeWithSize<>(sortedArray[start]);
        }
        // to keep equals on right
        int mid = (start + end) / 2;
        while (mid -1>=start   && sortedArray[mid] == sortedArray[mid - 1]) {
            mid = mid - 1;
        }
        BinaryTreeNodeWithSize<Integer> root = new BinaryTreeNodeWithSize<>(sortedArray[mid]);
        root.left = buildBSTWithSizeFromSortedArray(sortedArray, start, mid - 1);
        root.right = buildBSTWithSizeFromSortedArray(sortedArray, mid + 1, end);
        return root;
    }


    public static BinaryTreeNode<Integer> buildBSTFromSortedArray(int[] sortedArray, int start, int end) {

        if (start > end) {
            return null;
        }
        if (start == end) {
            return new BinaryTreeNode<>(sortedArray[start]);
        }
        int mid = (start + end) / 2;

        while (mid - 1 >= start && sortedArray[mid] == sortedArray[mid - 1]) {
            mid--;
        }
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(sortedArray[mid]);
        root.left = buildBSTFromSortedArray(sortedArray, start, mid - 1);
        root.right = buildBSTFromSortedArray(sortedArray, mid + 1, end);
        return root;
    }

    public static BinaryTreeNode<Integer> convertToDoubleLinkedList(BinaryTreeNode<Integer> root) {
        BinaryTreeNode<Integer>[] endPoints = convertToListRecurse(root);

        return endPoints[0];
    }

    public static BinaryTreeNode<Integer>[] convertToListRecurse(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return new BinaryTreeNode[]{null, null};
        }
        BinaryTreeNode<Integer>[] leftEndpoints = convertToListRecurse(root.left);
        BinaryTreeNode<Integer>[] rightEndpoints = convertToListRecurse(root.right);

        root.right = rightEndpoints[0];
        if (rightEndpoints[0] != null) {

            rightEndpoints[0].left = root;
        }

        root.left = leftEndpoints[1];
        if (leftEndpoints[1] != null) {

            leftEndpoints[1].right = root;
        }

        return new BinaryTreeNode[]{leftEndpoints[0] != null ? leftEndpoints[0] : root, rightEndpoints[1] != null ? rightEndpoints[1] : root};
    }

    public static BinaryTreeNode<Integer> findNode(BinaryTreeNode<Integer> root, int key) {
        if (root == null) {
            return null;
        }
        if (root.data.compareTo(key) < 0) {
            return findNode(root.right, key);
        } else if (root.data.compareTo(key) > 0) {
            return findNode(root.left, key);
        } else {
            return root;
        }

    }

    public static BinaryTreeNodeWithParent<Integer> findNode(BinaryTreeNodeWithParent<Integer> root, int key) {
        if (root == null) {
            return null;
        }
        if (root.data.compareTo(key) < 0) {
            return findNode(root.right, key);
        } else if (root.data.compareTo(key) > 0) {
            return findNode(root.left, key);
        } else {
            return root;
        }

    }

    /**
     * keeps duplicates to right
     * @param root
     * @param data
     * @return
     * @param <T>
     */
    public static <T extends Comparable<T>> BinaryTreeNode<T> insertNode(BinaryTreeNode<T> root, T data) {
        if (root == null) {
            return new BinaryTreeNode<>(data);
        }
        if (data.compareTo(root.data) >= 0) {
            root.right = insertNode(root.right, data);
        } else {
            root.left = insertNode(root.left, data);
    }

        return root;
    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> insertNode(BinaryTreeNode<T> root,
                                                                         BinaryTreeNode<T> newNode) {
        newNode.left=null;
        newNode.right=null;
        if (root == null) {
            return newNode;
        }
        if (newNode.data.compareTo(root.data) >= 0) {
            root.right = insertNode(root.right, newNode);
        } else {
            root.left = insertNode(root.left, newNode);
        }

        return root;
    }

    /**
     * delete a node , since the tree allows duplicates
     * the type of the value must implement equals method.
     * @param root
     * @param value
     * @return
     * @param <T>
     */

    public static<T extends Cloneable & Comparable<T>> BinaryTreeNode<T> deleteNode(BinaryTreeNode<T> root,
                                                                                   T value) {

        if (value.compareTo(root.data) == 0 && value.equals( root.data )) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            BinaryTreeNode<T> minNode = BinaryTreeUtils.getMinNode(root.right);
            // we shd clone the data, existing refs might manipulate it
            root.data =cloneItem(minNode.data)  ;
            root.right = deleteNode(root.right, minNode.data);

        } else if (value.compareTo(root.data) >= 0) {
            root.right = deleteNode(root.right, value);
        } else {
            root.left = deleteNode(root.left, value);
        }

        return root;
    }

    /**
     * deletes a node present in the tree and a reference is given to the node
     * thr tree supports duplicates which are packed to right
     * @param root
     * @param node node to be deleted
     * @return
     * @param <T>
     */
    public static<T extends Cloneable & Comparable<T>> BinaryTreeNode<T> deleteNode(BinaryTreeNode<T> root,
                                                                        BinaryTreeNode<T> node) {

    System.out.println(root.data +" "+ node.data);

        if (node.data.compareTo(root.data) == 0 && node.data.equals(root.data)) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            BinaryTreeNode<T> minNode = BinaryTreeUtils.getMinNode(root.right);
            // we shd clone the data, existing refs might manipulate it
            root.data =cloneItem(minNode.data) ;
            root.right = deleteNode(root.right, minNode);

        } else if (node.data.compareTo(root.data) >= 0) {
            root.right = deleteNode(root.right, node);
        } else {
            root.left = deleteNode(root.left, node);
        }

        return root;
    }

    @SuppressWarnings("unchecked")
    public static<T extends Cloneable & Comparable<T>> T cloneItem(T item) {
        try {
            return (T) item.getClass().getMethod("clone").invoke(item);
        } catch (Exception e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }

}
