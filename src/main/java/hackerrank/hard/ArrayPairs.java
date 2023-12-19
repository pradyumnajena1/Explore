package hackerrank.hard;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ArrayPairs {
    public static void main(String[] args) throws IOException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        long result =  solve(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static long solve(List<Integer> arr) {
        // Write your code here
        int totalCount = 0;
        TreeNode root = null;
        for (int i = 0; i < arr.size(); i++) {
            Integer currentValue = arr.get(i);
            //System.out.println("currentValue "+ currentValue);
            root = insert(root, currentValue, i);
            List<TreeNode> greaterOrEquals = findNodesGreaterThanEqualTo(root, currentValue);
            int startIndex = -1;
            for (TreeNode node : greaterOrEquals) {
               // System.out.println("greaterOrEquals "+ node);
                int endIndex = Math.min(node.index, i - 1);

                int count = getNumNodesBetween(root,  new TreeNode(1,startIndex),
                        new TreeNode(node.value / currentValue,endIndex));

               // System.out.println("count "+ count);
                totalCount += count;
                startIndex = node.index;
            }


        }
        return totalCount;
    }

    private static int getNumNodesBetween(TreeNode root, TreeNode low,TreeNode high) {
       // System.out.println(  " low = " + low + ", high = " + high);
        int numNodesLessThan = getNumNodesLessThan(root, low);
        int numNodesGreaterThan = getNumNodesGreaterThan(root, high);
        int numNodesBetween = root.size - numNodesLessThan - numNodesGreaterThan;
        return numNodesBetween;
    }
    private static int getNumNodesLessThan(TreeNode root, TreeNode high ) {
        if(root==null){
            return 0;
        }
        int compare = root.compareTo(high);
        if(compare<0){
            return (root.left!=null?root.left.size:0)+1 + getNumNodesLessThan(root.right,high);
        }else  {
            return getNumNodesLessThan(root.left,high);
        }
    }
    private static int getNumNodesGreaterThan(TreeNode root, TreeNode low ) {
        if(root==null){
            return 0;
        }
        int compare = root.compareTo(low);
        if(compare<=0){
            return getNumNodesGreaterThan(root.right,low);
        }else  {
            return (root.right!=null?root.right.size:0)+1 + getNumNodesGreaterThan(root.left,low);

        }
    }



    private static List<TreeNode> findNodesGreaterThanEqualTo(TreeNode root, int value) {
        List<TreeNode> collector = new ArrayList<>();
        findNodesGreaterThanEqualTo(root, value, collector);
        return collector;
    }

    private static void findNodesGreaterThanEqualTo(TreeNode root, int value, List<TreeNode> collector) {
        if (root == null) {
            return;
        }
        findNodesGreaterThanEqualTo(root.right, value, collector);
        if(value>root.value){
            return;
        }
        collector.add(root);
        findNodesGreaterThanEqualTo(root.left, value, collector);


    }

    private static TreeNode insert(TreeNode root, int value, int index) {
        TreeNode newNode = new TreeNode(value, index);
        newNode.size=1;
        if (root == null) {

            return newNode;
        }
        if (newNode.compareTo(root) < 0) {
            root.left = insert(root.left, newNode.value, newNode.index);

        } else {
            root.right = insert(root.right, newNode.value, newNode.index);
        }
        root.size++;
        return root;
    }

    private static class TreeNode implements Comparable<TreeNode> {
        int value;
        int index;
        int size;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(TreeNode o) {
            int compare = Integer.compare(this.value, o.value);
            return compare != 0 ? compare : Integer.compare(this.index, o.index);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", TreeNode.class.getSimpleName() + "[", "]")
                    .add("value=" + value)
                    .add("index=" + index)
                    .add("size=" + size)
                    .toString();
        }
    }
}
