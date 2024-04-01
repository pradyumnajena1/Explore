package hackerrank.easy;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JumpingontheClouds2 {

    public static class Solution {

        // Complete the jumpingOnClouds function below.
        static int jumpingOnClouds(int[] c, int k) {
          int current = 0;
          int energy = 100;
          do{
              int next = (current+k)%c.length;
              energy = energy-1;
              if(c[next]==1){
                  energy=energy-2;
              }
              current=next;
          }while (current!=0);
          return energy;

        }



        public static void main(String[] args) throws IOException {
             Scanner scanner = new Scanner(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String[] nk = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nk[0]);

            int k = Integer.parseInt(nk[1]);

            int[] c = new int[n];

            String[] cItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int cItem = Integer.parseInt(cItems[i]);
                c[i] = cItem;
            }

            int result = jumpingOnClouds(c, k);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedWriter.close();

            scanner.close();
        }
    }

}
