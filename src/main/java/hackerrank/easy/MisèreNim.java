package hackerrank.easy;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MisèreNim {
   static  class Result {

        /*
         * Complete the 'misereNim' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts INTEGER_ARRAY s as parameter.
         */

        public static String misereNim(List<Integer> s) {
            // Write your code here
                 return isWinnableState(s)?"FIRST":"SECOND";
        }

        public static boolean isWinnableState(List<Integer> s){
            int sum = s.stream().mapToInt(Integer::intValue).sum();
            if(sum==1){
                return false;
            }
            for(int i=0;i<s.size();i++){
                if(s.get(i)>0){
                    Integer currentValue = s.get(i);
                    s.set(i,0);
                    if(!isWinnableState(s)){
                        s.set(i,currentValue);
                        return true;
                    }
                    s.set(i,1);
                    if(!isWinnableState(s)){
                        s.set(i,currentValue);
                        return true;
                    }

                }
            }
            return false;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int t = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, t).forEach(tItr -> {
                try {
                    int n = Integer.parseInt(bufferedReader.readLine().trim());

                    List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                            .map(Integer::parseInt)
                            .collect(toList());

                    String result = Result.misereNim(s);

                    bufferedWriter.write(result);
                    bufferedWriter.newLine();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
