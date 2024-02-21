package hackerrank.hard;

import epp.ListUtils;
import epp.binaryTree.BinaryTreeNode;
import guidetocompetitiveprogramming.DistanceBetweenNodesQueryProcessor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class BSTMaintenance {
   static class Result {

        /*
         * Complete the 'solve' function below.
         *
         * The function is expected to return an INTEGER_ARRAY.
         * The function accepts INTEGER_ARRAY arr as parameter.
         */

        public static List<Integer> solve(List<Integer> arr) {
            // Write your code here
            ArrayList<Integer> result = new ArrayList<>();
            BinaryTreeNode<Integer> root= null;
            List<List<Integer>> adjList = new ArrayList<>();
            for(int i=0;i<=arr.size();i++){
                adjList.add(new ArrayList<>());
            }
            for(int i:arr){
                root =  insertNode( root,new BinaryTreeNode<Integer>(i),adjList);
            }
            result.add(0);
            if(arr.size()>=2) {
                DistanceBetweenNodesQueryProcessor queryProcessor = new DistanceBetweenNodesQueryProcessor(adjList,
                        arr.get(0));
                for (int i = 1; i < arr.size(); i++) {
                    int sum = 0;
                    for (int j = 0; j < i; j++) {
                        sum += queryProcessor.getDistance(arr.get(j), arr.get(i));
                    }
                    result.add(sum);
                }
                for (int i = 1; i < result.size(); i++) {
                    result.set(i, result.get(i) + result.get(i - 1));
                }
            }
            return result;

        }

       public static  BinaryTreeNode<Integer> insertNode(BinaryTreeNode<Integer> root,
                                                                            BinaryTreeNode<Integer> newNode,
                                                         List<List<Integer>> adjList) {
           newNode.left=null;
           newNode.right=null;
           if (root == null) {
               return newNode;
           }
           if (newNode.data.compareTo(root.data) >= 0) {
               BinaryTreeNode<Integer> rightRoot = insertNode(root.right, newNode, adjList);
               root.right = rightRoot;
               if(rightRoot==newNode){
                   adjList.get(root.data).add(newNode.data);
                   adjList.get(newNode.data).add(root.data);
               }
           } else {
               BinaryTreeNode<Integer> leftRoot = insertNode(root.left, newNode, adjList);
               root.left = leftRoot;
               if(leftRoot==newNode){
                   adjList.get(root.data).add(newNode.data);
                   adjList.get(newNode.data).add(root.data);
               }
           }

           return root;
       }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

            List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            List<Integer> result = Result.solve(arr);

            bufferedWriter.write(
                    result.stream()
                            .map(Object::toString)
                            .collect(joining("\n"))
                            + "\n"
            );

            bufferedReader.close();
            bufferedWriter.close();
        }
    }


}
