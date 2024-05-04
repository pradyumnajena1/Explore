package hackerrank.easy;

import epp.array.ArrayUtils;

import java.io.*;
import java.util.stream.IntStream;

public class PalindromeIndex {
    static class Result {

        /*
         * Complete the 'palindromeIndex' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts STRING s as parameter.
         */

        public static int palindromeIndex(String s) {
            // Write your code here
            int[] palindrome = isPalindrome(s);

            if (palindrome[0] == -1) {
                return -1;
            }
            String leftDelete = s.substring(0, palindrome[0]) + s.substring(palindrome[0]+1);
            String rightDelete = s.substring(0, palindrome[1]) + s.substring(palindrome[1]+1);

            if (isPalindrome(leftDelete)[0] == -1) {
                return palindrome[0];
            } else if (isPalindrome(rightDelete)[0] == -1) {
                return palindrome[1];
            }
            return -1;


        }

        public static int[] isPalindrome(String s) {
            int left = 0;
            int right = s.length() - 1;
            while (left < right && s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            }
            return left < right ? new int[]{left, right} : new int[]{-1, s.length()};
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int q = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, q).forEach(qItr -> {
                try {
                    String s = bufferedReader.readLine();

                    int result = Result.palindromeIndex(s);

                    bufferedWriter.write(String.valueOf(result));
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
