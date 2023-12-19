package epp.binarysearchtree.revision;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;

import java.util.Arrays;

/**
 * min at root, each node on left is less than all nodes in right
 */
public class MinFirstBST {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10, 1, 10);
        int key = values[6];
        System.out.println(key);
        BinaryTreeNode<Integer> root = createMinFirstBST(values);
        System.out.println(root);
        BinaryTreeNode<Integer> node = findItemInMinFirstBST(root, key);
        System.out.println(node);
    }

    private static BinaryTreeNode<Integer> findItemInMinFirstBST(BinaryTreeNode<Integer> root, int key) {
        if (root == null ) {
            return null;
        }
        int compare = root.data.compareTo(key);
        if(compare>0){
            return null;
        }
        if (compare == 0) {
            return root;
        }

        if (root.right == null || root.right.data.compareTo(key) > 0) {

            return findItemInMinFirstBST(root.left, key);
        } else {
            return findItemInMinFirstBST(root.right, key);
        }

    }


    private static BinaryTreeNode<Integer> createMinFirstBST(int[] values) {
        Arrays.sort(values);
        return createMinFirstBST(values, 0, values.length - 1);
    }

    private static BinaryTreeNode<Integer> createMinFirstBST(int[] values, int start, int end) {
        if (start > end) {
            return null;
        }
        //   System.out.println(Arrays.stream(values, start, end + 1).boxed().collect(Collectors.toList()));
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(values[start]);
        int mid = (start + 1 + end) / 2;
        while (mid + 1 <= end && values[mid] == values[mid + 1]) {
            mid++;
        }
        root.left = createMinFirstBST(values, start + 1, mid);
        root.right = createMinFirstBST(values, mid + 1, end);

        return root;
    }
}
