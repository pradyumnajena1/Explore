package hackerrank.easy;

import epp.ListUtils;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BeautifulTriplets {

    static class Result {

        /*
         * Complete the 'beautifulTriplets' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER d
         *  2. INTEGER_ARRAY arr
         */

        public static int beautifulTriplets(int d, List<Integer> arr) {
            // Write your code here
            int count = 0;
            for (int i = 1; i < arr.size() - 1; i++) {
                List<Integer> left = arr.subList(0, i);
                int leftCount =  getCount(left,arr.get(i) - d) ;
                if (leftCount > 0) {
                    List<Integer> right = arr.subList(i + 1, arr.size());
                    int rightCount =  getCount(right,arr.get(i) + d) ;
                    count+= leftCount*rightCount;
                }
            }
            return count;
        }

        private static int getCount(List<Integer> list,int value){
            int firstIndex = ListUtils.getFirstIndex(list, value);
            if(firstIndex>=0){
                int lastIndex = ListUtils.getLastIndex(list, value);
                return lastIndex-firstIndex+1;
            }
            return 0;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int d = Integer.parseInt(firstMultipleInput[1]);

            List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            int result = Result.beautifulTriplets(d, arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
