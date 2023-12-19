package hackerrank.medium;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Waiter {
    public static void main(String[] args) throws IOException {
        /*System.out.println(waiter(new ArrayList<>(List.of(3, 4, 7, 6, 5)),1));
        System.out.println(waiter(new ArrayList<>(List.of(3 ,3 ,4, 4, 9)),2));
        System.out.println(waiter(new ArrayList<>(List.of(2,3,4,5,6,7)),3));
      */
        main2(null);
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> number = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result =  waiter(number, q);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static List<Integer> waiter(List<Integer> number, int q) {
        // Write your code here
        List<Integer> primes = generatePrime(q);
        List<Integer> answers = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for(int i=0;i<number.size();i++){
            stack.push(number.get(i));
        }

        for(int i=0;i<q;i++){
            Stack<Integer> stackB = new Stack<>();
            Stack<Integer> stackC = new Stack<>();

            while (!stack.isEmpty()){
                Integer pop = stack.pop();
                if(pop%primes.get(i)==0){
                    stackB.push(pop);
                }else{
                    stackC.push(pop);
                }
            }
            stack  = stackC;
            System.out.println("stack"+ stack);
            System.out.println("stackB "+ stackB);

            while (!stackB.isEmpty()){
                answers.add(stackB.pop());
            }


            System.out.println("answers"+ answers);


        }
        while (!stack.isEmpty()){
            answers.add(stack.pop());
        }

        return answers;
    }

    static List<Integer> generatePrime(int n) {
        List<Integer> result = new ArrayList<>();
        int X = 0, i = 2;
        boolean flag;
        while (result.size() < n) {
            flag = true;
            for (int j = 2; j <= (double) Math.sqrt(i); j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result.add(i);

            }
            i++;
        }
        return result;
    }
}
