package epp.binarysearchtree.revision;

import epp.DoubleLinkedList;
import epp.DoubleLinkedListNode;
import epp.DoubleLinkedListUtils;
import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoubleLinkedListToBST {
    public static void main(String[] args) {
        Integer[] values = Arrays.stream(ArrayUtils.randomSortedUniqueArray(10, 1, 20)).boxed().toArray(Integer[]::new);
        ArrayUtils.printArray(values);
        DoubleLinkedList<Integer> list = DoubleLinkedListUtils.createDoubleLinkedList(values);
        System.out.println(list);
        DoubleLinkedListNode<Integer> root = createTreeFromDDL(list);
        List<Integer> inorderTraversal =  inorderTraversal(root);
        System.out.println(inorderTraversal);

    }

    private static List<Integer> inorderTraversal(DoubleLinkedListNode<Integer> root) {
        ArrayList<Integer> collector = new ArrayList<>();
          inorderTraversal(root, collector);
          return collector;
    }

    private static void inorderTraversal(DoubleLinkedListNode<Integer> root, ArrayList<Integer> collector) {
        if(root==null){
            return;
        }
        inorderTraversal(root.getPrev(),collector);
        collector.add(root.getData());
        inorderTraversal(root.getNext(),collector);
    }

    public static <T extends Comparable<T>> DoubleLinkedListNode<T> createTreeFromDDL(DoubleLinkedList<T> list) {
        int size = list.getSize();
        DoubleLinkedListNode<T> root = createTreeFromDDL(list, 0, size - 1);
        return root;
    }

    private static <T extends Comparable<T>> DoubleLinkedListNode<T> createTreeFromDDL(DoubleLinkedList<T> list, int start, int end) {

        if (start > end)
            return null;
        int mid = (start + end) / 2;
        DoubleLinkedListNode<T> left = createTreeFromDDL(list, start, mid - 1);
        DoubleLinkedListNode<T> root = list.removeNodeFromFirst();
        DoubleLinkedListNode<T> right = createTreeFromDDL(list, mid + 1, end);
        root.setPrev(left);
        root.setNext(right);
        return root;
    }
}
