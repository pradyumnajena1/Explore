package hackerrank.medium;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class QueueUsing2Stacks {

    public static class Solution {

        public static void main(String[] args) throws IOException {
            /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Pradyumna\\IdeaProjects" +
                    "\\Explore\\src\\main\\resources\\Input.txt"));
            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int q = Integer.parseInt(firstMultipleInput[0]);
            List<List<Integer>> queries = new ArrayList<>();
            IntStream.range(0, q ).forEach(i -> {
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
            processQueries(queries);

        }

        private static void processQueries(List<List<Integer>> queries) {
           // System.out.println(queries.size());
            QueueUsingTwoStacks<Integer> queueUsingTwoStacks = new QueueUsingTwoStacks<>();
            for(List<Integer> query: queries){
                if(query.get(0)==1){
                    queueUsingTwoStacks.enqueue(query.get(1));
                } else if (query.get(0)==2) {
                    queueUsingTwoStacks.dequeue();
                } else if (query.get(0)==3) {
                    System.out.println(queueUsingTwoStacks.peek());
                }
            }
        }

        public static class QueueUsingTwoStacks<T> {
            private Stack<T> stackA;
            private Stack<T> stackB;
            private int size;

            public QueueUsingTwoStacks() {
                stackA = new Stack<>();
                stackB = new Stack<>();
            }

            public void enqueue(T value) {
                stackA.push(value);
                size++;
            }

            public T dequeue() {
                if (size == 0) {
                    throw new IllegalStateException("Empty queue");
                }
                if (stackB.isEmpty()) {
                    while (!stackA.isEmpty()) {
                        stackB.push(stackA.pop());
                    }

                }
                T pop = stackB.pop();
                size--;
                return pop;
            }

            public T peek() {
                if (size == 0) {
                    throw new IllegalStateException("Empty queue");
                }
                if (stackB.isEmpty()) {
                    while (!stackA.isEmpty()) {
                        stackB.push(stackA.pop());
                    }

                }
                return stackB.peek();
            }
        }

    }
}
