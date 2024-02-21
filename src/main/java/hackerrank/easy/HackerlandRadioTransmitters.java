package hackerrank.easy;

import epp.binarySearch.BinarySearchUtils;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class HackerlandRadioTransmitters {
   static class Result {

        /*
         * Complete the 'hackerlandRadioTransmitters' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER_ARRAY x
         *  2. INTEGER k
         */

        public static int hackerlandRadioTransmitters(List<Integer> x, int k) {
            // Write your code here
               x.sort(Comparator.naturalOrder());
            System.out.println(x +" "+ k);
               int count=0;
               int left = 0;
              while (left<x.size()){
                  count++;
                  int next = x.get(left)+k;
                  int index =  findFirstItemBiggerThan(x, left, x.size() - 1, next);
                  if(index<0){
                      int position = x.size() - 1;
                      System.out.println(position);
                      return count;
                  }
                  int position = index - 1;
                  System.out.println(position);
                  next = x.get(position)+k;
                  index =  findFirstItemBiggerThan(x, index, x.size() - 1, next);
                  if(index<0){
                      return count;
                  }
                  left = index;
              }
              return count;
        }

       public static <T extends Comparable<T>> int findFirstItemBiggerThan(List<T> values, int start, int end, T value) {
           int low = start;
           int high = end;
           Integer index = null;
           while (low <= high) {
               int mid = low + (high - low) / 2;
               if (values.get(mid).compareTo(value) < 0) {
                   low = mid + 1;
               } else if (values.get(mid).compareTo(value) == 0) {
                   low = mid + 1;
               } else {
                   high = mid - 1;
                   index = mid;
               }
           }
           return index != null ? index : -(low + 1);
       }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int k = Integer.parseInt(firstMultipleInput[1]);

            List<Integer> x = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            int result = Result.hackerlandRadioTransmitters(x, k);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
