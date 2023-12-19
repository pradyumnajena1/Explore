package epp.binarysearchtree.revision;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;

/**
 * given pointer to three bst nodes , check if
 * two of the three are descendant and ancestor
 * of third
 */
public class CheckAncestorDescendant {
    public static void main(String[] args) {
        int[] sortedArray ={1, 2, 3, 4, 6, 7, 8, 11, 12, 13, 14, 16, 17, 18, 20};
        //ArrayUtils.randomSortedUniqueArray(15, 1, 20);
        ArrayUtils.printArray(sortedArray);
        BinaryTreeNode<Integer> root = BSTUtils.buildBSTFromSortedArray(sortedArray);
        System.out.println(root);
        BinaryTreeNode<Integer> r = BSTUtils.findNode(root, 11);
        BinaryTreeNode<Integer> s = BSTUtils.findNode(root, 13);
        BinaryTreeNode<Integer> m = BSTUtils.findNode(root, 14);
        System.out.println(r);
        System.out.println(s);
        System.out.println(m);
        boolean checkAncestorAndDescendant = checkAncestorAndDescendant(r, s, m);
        System.out.println(checkAncestorAndDescendant);
    }

    private static boolean checkAncestorAndDescendant(BinaryTreeNode<Integer> r, BinaryTreeNode<Integer> s, BinaryTreeNode<Integer> m) {
        BinaryTreeNode<Integer> curr_r = r;
        BinaryTreeNode<Integer> curr_s = s;

        while (curr_r != null && curr_r != s && curr_s != null && curr_s != r) {
            if (curr_r == m || curr_s == m) {
                return true;
            }
            curr_r = curr_r.data.compareTo(s.data) < 0 ? curr_r.right : curr_r.left;
            curr_s = curr_s.data.compareTo(r.data) < 0 ? curr_s.right : curr_s.left;
        }

        if (curr_r == s || curr_s == r) {
            return false;
        }

        return search(curr_r, s, m) || search(curr_s, r, m);


    }

    private static boolean search(BinaryTreeNode<Integer> curr, BinaryTreeNode<Integer> other, BinaryTreeNode<Integer> m) {
        while (curr != null && curr != other && curr != m) {
            curr = curr.data.compareTo(other.data) < 0 ? curr.right : curr.left;
        }
        return curr == m;
    }

}
