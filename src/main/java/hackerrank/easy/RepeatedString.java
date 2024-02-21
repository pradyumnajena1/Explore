package hackerrank.easy;

import java.io.*;

public class RepeatedString {
    public static long repeatedString(String s, long n) {
        // Write your code here
        int count = getCount(s);
        long repeat = n/s.length();
       String remaining = s.substring(0, (int) (n%s.length()));
       long total =   repeat*getCount(s) + getCount(remaining);
       return total;
    }

    private static int getCount(String s) {
        int count = 0;
        for(char ch: s.toCharArray()){
            if(ch=='a'){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        long n = Long.parseLong(bufferedReader.readLine().trim());

        long result = repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
