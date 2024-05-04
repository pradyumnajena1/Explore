package hackerrank.easy;

import java.io.*;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HappyLadybugs {
    static class Result {

        /*
         * Complete the 'happyLadybugs' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts STRING b as parameter.
         */

        public static String happyLadybugs(String b) {
            // Write your code here

            Map<Character, Long> freqMap =
                    b.chars().mapToObj(x -> Character.valueOf((char) x)).collect(Collectors.groupingBy(Function.identity(),
                            Collectors.counting()));
            for (Character c : freqMap.keySet()) {
                if (!c.equals('_') && freqMap.get(c) == 1) {
                    return "NO";
                }
            }
            if (freqMap.containsKey('_')) {
                return "YES";
            } else {
                return isHappy(b)?"YES":"NO";
            }


        }

        private static boolean isHappy(String b) {
            for (int i = 0; i < b.length(); i++) {
                if ( (i>0&& b.charAt(i) != b.charAt(i - 1)) &&
                        (i< b.length()-1 && b.charAt(i) != b.charAt(i + 1))) {
                    return false;
                }
            }
            return true;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int g = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, g).forEach(gItr -> {
                try {
                    int n = Integer.parseInt(bufferedReader.readLine().trim());

                    String b = bufferedReader.readLine();

                    String result = Result.happyLadybugs(b);

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
