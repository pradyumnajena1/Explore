package hackerrank.medium;

import epp.Pair;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class HighestValuePalindrome {
    static class Result {
        public static void main(String[] args) {
            System.out.println(getSubstring("1221", 4));
            System.out.println(getSubstring("1221", 2));
            System.out.println(getSubstring("1221", 0));
            System.out.println(getSubstring("12321", 5));
            System.out.println(getSubstring("12321", 3));
            System.out.println(getSubstring("12321", 1));
            System.out.println(getSubstring("12321", 0));
        }

        /*
         * Complete the 'highestValuePalindrome' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts following parameters:
         *  1. STRING s
         *  2. INTEGER n
         *  3. INTEGER k
         */
        public static String highestValuePalindrome(String s, int n, int k) {
            Map<Pair<Integer, Integer>, String> cache = new HashMap<>();
            String result = highestValuePalindromeHelper(s, n, k, cache);
            return isPalindrome(result)?result:"-1";
        }

        public static String highestValuePalindromeHelper(String s, int n, int k, Map<Pair<Integer, Integer>, String> cache) {
            // Write your code here
             System.out.println( " n = " + n + ", k = " + k);
            String substring = getSubstring(s, n);
            if (k == 0) {
                return substring;
            }
            if (k >= n) {
                String s1 = substring.replaceAll(".", "9");
                return s1;
            }
            Pair<Integer, Integer> key = new Pair<>(n, k);
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
            String result = null;
            char firstChar = substring.charAt(0);
            char lastChar = substring.charAt(substring.length() - 1);
            if (firstChar == lastChar) {
                if (firstChar != '9' && substring.length() > 1 && k >= 2) {
                    result = "9" + highestValuePalindromeHelper(s, n - 2, k - 2, cache) + "9";
                } else if (firstChar != '9' && substring.length() == 1 && k >= 1) {
                    result = "9";
                } else {

                    result = firstChar + highestValuePalindromeHelper(s, n - 2, k, cache) + firstChar;
                }
            } else {
                char maxChar = (char) Math.max(firstChar, lastChar);
                if (maxChar != '9' && k >= 2) {
                    result = "9" + highestValuePalindromeHelper(s, n - 2, k - 2, cache) + "9";
                } else {

                    result = maxChar + highestValuePalindromeHelper(s, n - 2, k - 1, cache) + maxChar;
                }


            }

            cache.put(key, result);
            return result;
        }




        private static String getSubstring(String s, int n) {
            int length = s.length();
            int center = length / 2;
            int left = center - n / 2;
            if (length % 2 == 0) {
                center = length / 2 - 1;
                left = center - n / 2 + 1;
            }
            return s.substring(left, left + n);
        }
        private static boolean isPalindrome(String s) {
            return isPalindrome(s, 0, s.length() - 1);
        }

        private static boolean isPalindrome(String s, int start, int end) {
            if (start >= end) {
                return true;
            }
            while (start < end && s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            }

            return start >= end;
        }


    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int k = Integer.parseInt(firstMultipleInput[1]);

            String s = bufferedReader.readLine();

            String result = Result.highestValuePalindrome(s, n, k);

            bufferedWriter.write(result);
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
