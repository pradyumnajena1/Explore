package hackerrank.medium;

import epp.binaryTree.BinaryTreeNode;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ArrayManipulation {
    public static void main(String[] args) throws IOException {
          System.out.println(arrayManipulation(10, new ArrayList<>(List.of(List.of(1, 5, 3), List.of(4, 8, 7),
            List.of(6, 9, 1)))));


         BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Pradyumna\\IdeaProjects" +
                "\\Explore\\src\\main\\resources\\Input.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\Pradyumna\\IdeaProjects\\Explore\\src\\main\\resources\\Output.txt"));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });



        long result = arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();

    }

    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        List<EndPoint> endPoints = new ArrayList<>();
        for(int i=0;i< queries.size();i++){
            List<Integer> query = queries.get(i);
            endPoints.add(new EndPoint( query.get(0),query,true));
            endPoints.add(new EndPoint( query.get(1),query,false));
        }
        endPoints.sort(Comparator.naturalOrder());
       // System.out.println(endPoints);
        long sum = 0;
        long maxSum = 0;
        long[] values = new long[n+1];
        for(EndPoint endPoint:endPoints){
            if(endPoint.isOpen){
                sum+= endPoint.query.get(2);
            }
            values[endPoint.x] = sum;
            if(sum>maxSum){
                maxSum = sum;
            }
            if(!endPoint.isOpen){
                sum-= endPoint.query.get(2);
            }
        }
        return maxSum;
    }
    private static class EndPoint implements Comparable<EndPoint>{
        int x;
        List<Integer> query;
        boolean isOpen;

        public EndPoint(int x, List<Integer> query, boolean isOpen) {
            this.x = x;
            this.query = query;
            this.isOpen = isOpen;
        }

        @Override
        public int compareTo(EndPoint o) {
            int compare = Integer.compare(x, o.x);
            return compare!=0?compare:isOpen ?-1:1;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", EndPoint.class.getSimpleName() + "[", "]")
                    .add("x=" + x)
                    .add("sum=" + query.get(2))
                    .add("isOpen=" + isOpen)
                    .toString();
        }
    }




}
