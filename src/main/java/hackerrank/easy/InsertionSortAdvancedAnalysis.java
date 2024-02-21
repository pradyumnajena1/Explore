package hackerrank.easy;

import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class InsertionSortAdvancedAnalysis {

  static   class Result {

        /*
         * Complete the 'insertionSort' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts INTEGER_ARRAY arr as parameter.
         */

        public static long insertionSort(List<Integer> arr) {
            // Write your code here
            long count=0;
            BinaryTreeNode<TreeNode> tree=null;
            for(int i=0;i<arr.size();i++){
                int value = arr.get(i);
                AtomicLong count1 = new AtomicLong(0);
                tree =   insertNode(tree, new TreeNode(value), count1) ;
              count+=count1.get();

            }
            return count;

        }
      public static   BinaryTreeNode<TreeNode> insertNode(BinaryTreeNode<TreeNode> root, TreeNode data,
                                                          AtomicLong count) {



          if (root == null) {
              return new BinaryTreeNode<>(data);
          }
          if (data.compareTo(root.data) >= 0) {
              root.right = insertNode(root.right, data,count);
              root.data.rightSubtreeSize++;
          } else {
              count.addAndGet( root.data.rightSubtreeSize+1);
              root.left = insertNode(root.left, data,count);
          }

          return root;
      }

    }
    static class TreeNode implements Comparable<TreeNode>{
      int data;
      long rightSubtreeSize;

        public TreeNode(int data) {
            this.data = data;
        }

        @Override
        public int compareTo(TreeNode o) {
            return Integer.compare(data,o.data);
        }
    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int t = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, t).forEach(tItr -> {
                try {
                    int n = Integer.parseInt(bufferedReader.readLine().trim());

                    List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                            .map(Integer::parseInt)
                            .collect(toList());

                    long result = Result.insertionSort(arr);

                    bufferedWriter.write(String.valueOf(result));
                    bufferedWriter.newLine();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
