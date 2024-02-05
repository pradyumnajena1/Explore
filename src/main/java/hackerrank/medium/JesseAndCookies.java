package hackerrank.medium;

import java.io.*;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class JesseAndCookies {

    public static int cookies(int k, List<Integer> A) {
        // Write your code here
        PriorityQueue<Integer> queue = new PriorityQueue<>(A);
        int numSteps = 0;
        while (queue.size()>=2 && queue.peek()<k){
            Integer smallest = queue.poll();
            Integer secondSmallest = queue.poll();
            Integer newCandy = smallest + 2* secondSmallest;
            queue.offer(newCandy);
            numSteps++;
        }

        if(queue.peek()>=k){
            return numSteps;
        }
        return -1;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> A = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result =  cookies(k, A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
