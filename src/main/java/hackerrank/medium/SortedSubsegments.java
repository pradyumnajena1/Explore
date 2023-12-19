package hackerrank.medium;

import epp.Interval;
import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;
import epp.binarysearchtree.BSTUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class SortedSubsegments {
    public static void main(String[] args) {

       /* ArrayList<Integer> list = new ArrayList<>(List.of(4, 3, 2, 1));
        list.subList(0,3).sort(Comparator.naturalOrder());
        System.out.println(list);
        list.subList(1,4).sort(Comparator.naturalOrder());
        System.out.println(list);*/
        System.out.println(sortedSubsegments(0,
                new ArrayList<Integer>(List.of(4, 3, 2, 1)),
                new ArrayList<List<Integer>>(List.of(List.of(0, 2), List.of(1, 3)))
        ));

       /* System.out.println(sortedSubsegments(1,
                new ArrayList<Integer>(List.of(3, 2, 1)),
                new ArrayList<List<Integer>>(List.of(List.of(0, 1) ))
        ));*/
    }

    public static int sortedSubsegments(int k, List<Integer> a, List<List<Integer>> queries) {
        // Write your code here

        TreeSet<Integer> tree = new TreeSet<>();
         for(List<Integer> query:queries){
             int start = query.get(0);
             int end = query.get(1);
             if(start<=k && k<=end){

                tree.addAll(a.subList(start,end+1));
             }
         }
        return a.get(k);

    }



}
