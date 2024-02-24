package hackerrank.easy;

import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;

import javax.swing.tree.TreeNode;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
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
          long count = 0;
          int[] biggerCount = new int[arr.size()];
          Stack<Integer> stack = new Stack<>();
          for(int i=0;i<arr.size();i++){
              while(!stack.isEmpty() && arr.get( stack.peek())<arr.get(i)){
                  stack.pop();
              }
              if(stack.isEmpty()){
                  biggerCount[i] = 0;
              }else{
                  biggerCount[i] = arr.get(stack.peek())== arr.get(i)?biggerCount[stack.peek()]:
                          biggerCount[stack.peek()] +1;
              }
              count+=biggerCount[i];
          }
          return count;

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
