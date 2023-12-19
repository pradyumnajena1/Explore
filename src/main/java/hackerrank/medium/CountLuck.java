package hackerrank.medium;

import epp.Pair;
import epp.array.ArrayUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class CountLuck {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {

            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                List<String> matrix = IntStream.range(0, n).mapToObj(i -> {
                            try {
                                return bufferedReader.readLine();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        })
                        .collect(toList());

                int k = Integer.parseInt(bufferedReader.readLine().trim());

                String result =  countLuck(matrix, k);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static String countLuck(List<String> matrix, int k) {
        // Write your code here
           int numRows = matrix.size();
           int numCols = matrix.get(0).length();
        Pair<Integer,Integer> source = findSource(matrix,numRows,numCols);
        Pair<Integer,Integer> destination = findDest(matrix,numRows,numCols);
       /* System.out.println(source);
        System.out.println(destination);*/
       boolean canReach =  canReach(matrix,source,destination,k);
      return canReach?"Impressed":"OOPS";

    }

    private static boolean canReach(List<String> matrix, Pair<Integer, Integer> source, Pair<Integer, Integer> destination, int k) {
        System.out.println("canReach "+k);
        int numRows = matrix.size();
        int numCols = matrix.get(0).length();
        Queue<Pair<Integer,Integer>> bfsQueue = new ArrayDeque<>();
        Set<Pair<Integer,Integer>> visitedSet = new HashSet<>();
        bfsQueue.offer(source);
        visitedSet.add(source);
        int numDecisions= 0;
        while (!bfsQueue.isEmpty()){

            Pair<Integer, Integer> polled = bfsQueue.poll();
            if(polled.equals(destination)){
                System.out.println(numDecisions);
                return numDecisions==k;
            }
            List<Pair<Integer, Integer>> neighbours = ArrayUtils.getNeighbours(numRows-1, numCols-1, polled);
            List<Pair<Integer, Integer>> validNeighbours =
                    neighbours.stream()
                            .filter(x -> !visitedSet.contains(x)).filter(x -> matrix.get(x.getFirst()).charAt(x.getSecond()) != 'X')
                            .sorted(Comparator.comparingInt(x->countDecision(matrix,numRows,numCols, x)))
                            .collect(toList());

            if(validNeighbours.size()>1){
                numDecisions++;
            }
            for(Pair<Integer,Integer> validNeigh:validNeighbours){

                bfsQueue.offer(validNeigh);
                visitedSet.add(validNeigh);
            }
        }

        return false;
    }

    public static int countDecision(List<String> matrix, int numRows, int numCols,Pair<Integer,Integer> point){
        int decision = 0;
        if(point.getFirst()<numRows-1){
            if(matrix.get(point.getFirst()+1).charAt(point.getSecond()) != 'X'){
                decision++;
            }

        }
        if(point.getFirst()>0){
            if(matrix.get(point.getFirst()-1).charAt(point.getSecond()) != 'X'){
                decision++;
            }

        }
        if(point.getSecond()>0){
            if(matrix.get(point.getFirst()).charAt(point.getSecond()-1) != 'X'){
                decision++;
            }

        }
        if(point.getSecond()<numCols-1){
            if(matrix.get(point.getFirst()).charAt(point.getSecond()+1) != 'X'){
                decision++;
            }

        }
        return decision;
    }

    private static Pair<Integer, Integer> findSource(List<String> matrix, int numRows, int numCols) {
        return findCharacter(matrix, numRows, numCols, 'M');
    }
    private static Pair<Integer, Integer> findDest(List<String> matrix, int numRows, int numCols) {
        return findCharacter(matrix, numRows, numCols, '*');
    }

    private static Pair<Integer, Integer> findCharacter(List<String> matrix, int numRows, int numCols, char charToFind) {
        for(int i = 0; i< numRows; i++){
            for(int j = 0; j< numCols; j++){
                if(matrix.get(i).charAt(j)== charToFind){
                    return new Pair<>(i, j);
                }
            }
        }
        return null;
    }
}
