package hackerrank.medium;

import epp.ListUtils;
import epp.array.ArrayUtils;

import java.io.*;
import java.util.ArrayList;

import java.util.List;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class QHeap1 {
    public static class Solution {

        private static class MinHeap {
            private final List<Integer> heap = new ArrayList<>();
            private int size = 0;

            public MinHeap() {
            }

            @Override
            public String toString() {
                return "MinHeap{" +
                        "heap=" + heap.subList(0,size) +
                        '}';
            }

            public int getMin() {
                if (size == 0) {
                    throw new IllegalStateException("empty heap");
                }
                return heap.get(0);
            }

            public void add(int value) {

                if(heap.size()>size){
                    heap.set(size,value);
                }else{
                    heap.add(value);
                }
                size++;
                heapifyUp(size-1);
               // System.out.println(this);
            }

            public void remove(int value) {

                int index = find(value);
                swap(heap, index, size - 1);
                size--;
                heapifyDown(index);


                //System.out.println(this);
            }

            private int find(int value) {
                return find(value, 0);
            }

            private int find(int value, int index) {
                if (index >= size) {
                    return -1;
                }
                if (heap.get(index) == value) {
                    return index;
                }
                int i = find(value, getLeft(index));
                if (i != -1) {
                    return i;
                }
                return find(value, getRight(index));
            }

            public void heapifyDown(int i) {
                int minimum = i;
                int left = getLeft(i);
                int right = getRight(i);
                if (left < size && heap.get(left) < heap.get(minimum)) {
                    minimum = left;
                }
                if (right < size && heap.get(right) < heap.get(minimum)) {
                    minimum = right;
                }
                if (minimum != i) {
                     swap(heap, i, minimum);
                    heapifyDown(minimum);
                }

            }
            public void heapifyUp(int i) {
                 int current = i;
                 while (current>0){
                     int parent = current/2;
                     if(heap.get(parent)>heap.get(current)){
                         swap(heap,current,parent);
                     }else{
                         break;
                     }
                     current = parent;
                 }

            }

            public static int getRight(int i) {
                return i * 2 + 2;
            }

            public static int getLeft(int i) {
                return i * 2 + 1;
            }

            public static <T> void swap(List<T> values, int i, int j) {
                T temp = values.get(i);
                values.set(i, values.get(j));
                values.set(j, temp);
            }
        }

        public static List<Integer> solve(List<List<Integer>> queries) {
            List<Integer> result = new ArrayList<>();
            MinHeap heap = new MinHeap();
            for (List<Integer> query : queries) {
               // System.out.println(query);
                int type = query.get(0);
                if (type == 1) {
                    heap.add(query.get(1));
                } else if (type == 2) {
                    heap.remove(query.get(1));
                } else {
                    result.add(heap.getMin());
                }
            }
            return result;
        }

        public static void main(String[] args) throws IOException {
            /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);


            List<List<Integer>> queries = new ArrayList<>();
            IntStream.range(0, n).forEach(i -> {
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

            // Write your code here

            List<Integer> result = solve(queries);

            bufferedWriter.write(
                    result.stream()
                            .map(Object::toString)
                            .collect(joining("\n"))
                            + "\n");
            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
