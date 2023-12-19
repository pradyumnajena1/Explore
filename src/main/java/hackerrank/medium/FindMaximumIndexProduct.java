package hackerrank.medium;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FindMaximumIndexProduct {
    public static void main(String[] args) {
        System.out.println(solve(new ArrayList<>(List.of(6 ,1 ,9, 3, 2))));
    }

    public static void main2(String[] args) throws IOException {
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
        long maxProduct = 0;
        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> rightStack = new Stack<>();
        int[] left = new int[arr.size()];
        int[] right = new int[arr.size()];
        for(int i=0;i<arr.size();i++){
            while (leftStack.size()>0 && arr.get(i) >= arr.get(leftStack.peek()) ){
                leftStack.pop();
            }
            if(leftStack.size()==0){
                left[i] = 0;
            }else{
                left[i] = leftStack.peek()+1;
            }
            leftStack.push(i);
        }
        System.out.println(leftStack);

        for(int i=arr.size()-1;i>=0;i--){
            while (rightStack.size()>0 && arr.get(i) >= arr.get(rightStack.peek()) ){
                rightStack.pop();
            }
            if(rightStack.size()==0){
                right[i] = 0;
            }else{
                right[i] = rightStack.peek()+1;
            }
            rightStack.push(i);
        }
        System.out.println(rightStack);

        for(int i=0;i<arr.size();i++){
            long product = (long)left[i]*right[i];
            maxProduct = Math.max(product,maxProduct);
        }

        return maxProduct;
    }


}
