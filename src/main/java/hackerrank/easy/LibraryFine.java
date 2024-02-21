package hackerrank.easy;

import java.io.*;
import java.util.Date;

public class LibraryFine {
    static class Result {

        /*
         * Complete the 'libraryFine' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER d1
         *  2. INTEGER m1
         *  3. INTEGER y1
         *  4. INTEGER d2
         *  5. INTEGER m2
         *  6. INTEGER y2
         */

        public static int libraryFine(int d1, int m1, int y1, int d2, int m2, int y2) {
            // Write your code here
            Date exDate = new Date(y2,m2-1,d2);
            Date returnedDate = new Date(y1,m1-1,d1);
            if(!returnedDate.after(exDate)  ){
                return 0;
            }
            if(returnedDate.getYear()>exDate.getYear()){
                return 10000;
            }
            int effMonth = (exDate.getYear() - returnedDate.getYear()) * 12 + exDate.getMonth();
            if(returnedDate.getMonth() > effMonth) {
                return (returnedDate.getMonth()-effMonth)*500;
            }
            if(returnedDate.getDate()>exDate.getDate()){
                return (returnedDate.getDate() - exDate.getDate())*15;
            }
            return 0;

        }


    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int d1 = Integer.parseInt(firstMultipleInput[0]);

            int m1 = Integer.parseInt(firstMultipleInput[1]);

            int y1 = Integer.parseInt(firstMultipleInput[2]);

            String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int d2 = Integer.parseInt(secondMultipleInput[0]);

            int m2 = Integer.parseInt(secondMultipleInput[1]);

            int y2 = Integer.parseInt(secondMultipleInput[2]);

            int result = Result.libraryFine(d1, m1, y1, d2, m2, y2);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
