package hackerrank.easy;

import epp.binaryTree.BinaryTreeNode;


import java.util.*;

public class InsertionSortCountShiftsReqd {
    public static void main(String[] args) {
        System.out.println(insertionSort(new ArrayList<>(List.of(2,1,3,1,2))));
        System.out.println(insertionSort2(new ArrayList<>(List.of(2,1,3,1,2))));
        System.out.println(insertionSort3(new ArrayList<>(List.of(2,1,3,1,2))));
    }

    public static int insertionSort(List<Integer> arr) {
        // Write your code here
        int totalShifts = 0;
        TreeSet<Integer> tree = new TreeSet<>();
        for (int i = 0; i < arr.size(); i++) {
            int numElementsHeighr = tree.tailSet(arr.get(i),false).size();
            totalShifts+= numElementsHeighr;
         tree.add(arr.get(i));
        }
        return totalShifts;
    }

    public static int insertionSort2(List<Integer> arr) {
        // Write your code here
        int totalShifts = 0;
        int[] biggerSeen= new int[arr.size()];
        biggerSeen[0]=0;
        TreeSet<Integer> tree = new TreeSet<>();
        for (int i = 1; i < arr.size(); i++) {
            for(int j=i-1;j>=0;j--){
                if(arr.get(j)>arr.get(i)){
                    biggerSeen[i] ++;

                }
            }
        }
      totalShifts=  Arrays.stream(biggerSeen).sum();
        return totalShifts;
    }

    public static int insertionSort3(List<Integer> arr) {
        // Write your code here
        int totalShifts = 0;
        BinaryTreeNode<NodeWithSize<Integer>> tree = null;
       tree=  insert(tree,arr.get(0));
        for (int i = 1; i < arr.size(); i++) {
            int numElementsHeighr = numItemsGreaterThan(tree,arr.get(i)) ;
            totalShifts+= numElementsHeighr;
           tree = insert(tree,arr.get(i));
        }
        return totalShifts;
    }

    private static <T extends Comparable<T>> int numItemsGreaterThan(BinaryTreeNode< NodeWithSize<T>> root, T high) {
        if(root==null){
            return 0;
        }
        int compare = root.data.data.compareTo(high);
        if(compare<=0){
            return numItemsGreaterThan(root.right,high);
        }else  {
            return (root.right!=null?root.right.data.size:0)+1 + numItemsGreaterThan(root.left,high);

        }

    }
    private static<T extends Comparable<T>> BinaryTreeNode<NodeWithSize<T>> insert(BinaryTreeNode<NodeWithSize<T>> root,
                                                                           T key) {
        if(root==null){
            return new BinaryTreeNode<>(new NodeWithSize<>(key,1));

        }
        int compare = root.data.data.compareTo(key);
        if(compare<=0){
            root.right = insert(root.right,key);

        }else{
            root.left = insert(root.left,key);

        }
        root.data.size++;
        return root;
    }

   private static class NodeWithSize<T extends Comparable<T>> implements Comparable< NodeWithSize<T>> {
        T data;
        int size;

        public NodeWithSize(T data, int size) {
            this.data = data;
            this.size = size;
        }

        public NodeWithSize(T data) {
            this.data = data;
        }

        @Override
        public int compareTo( NodeWithSize<T> o) {
            return this.data.compareTo(o.data);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ",    "[", "]")
                    .add("d=" + data)
                    .add("s=" + size)
                    .toString();
        }
    }

}
