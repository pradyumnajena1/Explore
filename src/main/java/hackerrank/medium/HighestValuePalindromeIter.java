package hackerrank.medium;

import epp.Pair;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class HighestValuePalindromeIter {
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
            String result = highestValuePalindromeHelper(s, n, k);
            return isPalindrome(result) ? result : "-1";

        }

        public static String highestValuePalindromeHelper(String s, int n, int k) {
            String result = null;
            Stack<Pair<Integer, Integer>> stack = new Stack<>();
            Map<Pair<Integer, Integer>, String> cache = new HashMap<>();
            stack.push(new Pair<>(n, k));

            while (!stack.isEmpty()) {
                Pair<Integer, Integer> key = stack.pop();
                n = key.getFirst();
                k = key.getSecond();

                if (cache.containsKey(key)) {
                    continue;
                }

                String substring = getSubstring(s, n);
                if (k == 0) {
                    result = substring;
                    cache.put(key, result);
                    continue;
                }
                if (k >= n) {
                    result = substring.replaceAll(".", "9");
                    cache.put(key, result);
                    continue;
                }
                char firstChar = substring.charAt(0);
                char lastChar = substring.charAt(substring.length() - 1);
                if (firstChar == lastChar) {
                    if (firstChar != '9' && substring.length() > 1 && k >= 2) {
                        if (cache.containsKey(new Pair<>(n - 2, k - 2))) {
                            result = "9" + cache.get(new Pair<>(n - 2, k - 2)) + "9";
                            cache.put(key, result);
                        } else {
                            stack.push(new Pair<>(n, k));
                            stack.push(new Pair<>(n - 2, k - 2));
                        }

                    } else {
                        if (cache.containsKey(new Pair<>(n - 2, k))) {
                            result = firstChar + cache.get(new Pair<>(n - 2, k)) + firstChar;
                            cache.put(key, result);
                        } else {
                            stack.push(new Pair<>(n, k));
                            stack.push(new Pair<>(n - 2, k));
                        }

                    }
                } else {
                    char maxChar = (char) Math.max(firstChar, lastChar);
                    if (maxChar != '9' && k >= 2) {
                        if (cache.containsKey(new Pair<>(n - 2, k - 2))) {
                            result = "9" + cache.get(new Pair<>(n - 2, k - 2)) + "9";
                            cache.put(key, result);
                        } else {
                            stack.push(new Pair<>(n, k));
                            stack.push(new Pair<>(n - 2, k - 2));
                        }

                    } else {
                        if (cache.containsKey(new Pair<>(n - 2, k - 1))) {
                            result = maxChar + cache.get(new Pair<>(n - 2, k - 1)) + maxChar;
                            cache.put(key, result);
                        } else {
                            stack.push(new Pair<>(n, k));
                            stack.push(new Pair<>(n - 2, k - 1));
                        }

                    }
                }

            }
           // System.out.println(cache);
            return cache.get(new Pair<>(n, k));
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
