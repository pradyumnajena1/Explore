package hackerrank.medium;

import epp.array.ArrayUtils;

import java.io.*;
import java.util.*;

public class ShortPalindrome3 {
    static class Result {
        private static final long mod = (long) (Math.pow(10, 9) + 7);

        /*
         * Complete the 'shortPalindrome' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts STRING s as parameter.
         */

        public static long shortPalindrome(String s) {
            // Write your code here
            int[][] freq = new int[s.length()][26];
            freq[0][s.charAt(0) - 'a']++;
            for (int i = 1; i < s.length(); i++) {
                System.arraycopy(freq[i-1],0,freq[i],0,26);
                int current = s.charAt(i) - 'a';
                freq[i][current]++;
            }
            //ArrayUtils.print2DArray(freq);
            long totalCount = 0;
            Map<Character, List<Integer>> charIndices = new HashMap<>();
            for(int index=0;index<s.length();index++){
                System.out.println(index);
                List<Integer> indices = charIndices.getOrDefault(s.charAt(index),new ArrayList<>());
                if(indices.size()>0){
                    int secondIndex = indices.get(indices.size()-1);
                    long count = getCount(freq, secondIndex, index, s.length());
                    int firstIndex = indices.get(0);
                    long count2 = getCount2(freq, firstIndex, index, s.length());
                    System.out.println(count + " "+count2);
                    totalCount = totalCount+  count+count2;

                }

                indices.add(index);
                charIndices.put(s.charAt(index),indices);
            }

            return totalCount;
        }

        private static long getCount(int[][] frequency, int secondIndex, int thirdIndex,int length) {
            System.out.println("  secondIndex = " + secondIndex + ", thirdIndex = " + thirdIndex   );
            long count = 0;
            int[] rangeFrequency1 = getRangeFrequency(frequency, 0, secondIndex-1);
            int[] rangeFrequency2 = getRangeFrequency(frequency, thirdIndex+1, length-1);
            // ArrayUtils.printArray(rangeFrequency1);
           //  ArrayUtils.printArray(rangeFrequency2);
            for (int k = 0; k < 26; k++) {

                    count = (count + rangeFrequency1[k]*rangeFrequency2[k] );

            }
            return count;
        }
        private static long getCount2(int[][] frequency, int firstIndex, int fourthIndex,int length) {
            System.out.println("  firstIndex = " + firstIndex + ", fourthIndex = " + fourthIndex   );
            long count = 0;
            int[] rangeFrequency = getRangeFrequency(frequency, firstIndex+1, fourthIndex-1);

           // ArrayUtils.printArray(rangeFrequency);

            for (int k = 0; k < 26; k++) {
                if(rangeFrequency[k]>=2)
                count = (count + nCr(rangeFrequency[k],2) );

            }
            return count;
        }

        private static int[] getRangeFrequency(int[][] frequency, int i, int j) {
            System.out.println("  i = " + i + ", j = " + j);
            int[] rangeFrequency = new int[26];
            if(i>j){
                return rangeFrequency;
            }
            for (int k = 0; k < 26; k++) {
                rangeFrequency[k] = frequency[j][k] - (i >= 1 ? frequency[i - 1][k] : 0);
            }
            return rangeFrequency;
        }

        public static int nCr(int n, int r) {
            if (r > n)
                return 0;
            if (r == 0 || r == n)
                return 1;
            return nCr(n - 1, r - 1) + nCr(n - 1, r);
        }


    }

    public static class Solution {

        static class CacheKey {
            int start;
            int end;
            boolean isChosen;


            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                CacheKey cacheKey = (CacheKey) o;

                if (start != cacheKey.start) return false;
                if (end != cacheKey.end) return false;
                return isChosen == cacheKey.isChosen;
            }

            @Override
            public int hashCode() {
                int result = start;
                result = 31 * result + end;
                result = 31 * result + (isChosen ? 1 : 0);
                return result;
            }

            @Override
            public String toString() {
                return new StringJoiner(", ", CacheKey.class.getSimpleName() + "[", "]")
                        .add("start=" + start)
                        .add("end=" + end)
                        .toString();
            }
        }
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String s = bufferedReader.readLine();

            long result = Result.shortPalindrome(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
