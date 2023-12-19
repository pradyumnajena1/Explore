package hackerrank.medium;

import epp.array.ArrayUtils;

public class Encryption {
    public static void main(String[] args) {
        System.out.println(encryption("if man was meant to stay on the ground god would have given us roots"));
        System.out.println(encryption("haveaniceday"));
        System.out.println(encryption("chillout"));
    }

    public static String encryption(String s) {
        // Write your code here
        s = removeSpaces(s);
        System.out.println(s);
        int start = s.length();

        RowCol rowCol = getRowCol(start);

        String[] grid = new String[rowCol.rows];
        for (int i = 0; i < rowCol.rows; i++) {
            grid[i] = s.substring(i * rowCol.cols, Math.min(s.length(), (i + 1) * rowCol.cols));
        }
        ArrayUtils.printArray(grid);
        String[] encoded = new String[rowCol.cols];
        for (int i = 0; i < rowCol.cols; i++) {
            encoded[i] = "";
            for (int j = 0; j < rowCol.rows; j++) {
                if (i < grid[j].length()) {

                    encoded[i] += grid[j].charAt(i);
                }
            }
        }
        ArrayUtils.printArray(encoded);
        String result = String.join(" ", encoded);

        return result;
    }

    private static RowCol getRowCol(int start) {
        int cols;
        int rows;
        do {
            double sqrt = Math.sqrt(start);
            rows = (int) Math.floor(sqrt);
            cols = (int) Math.ceil(sqrt);
            if(rows*cols>= start){
                break;
            }
            start++;

        } while (true);
        RowCol result = new RowCol(rows, cols);
        return result;
    }

    private static class RowCol {
        public final int rows;
        public final int cols;

        public RowCol(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
        }
    }

    private static String removeSpaces(String s) {
        char[] chars = s.toCharArray();
        int writePos = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != ' ') {
                chars[writePos++] = chars[i];
            }
        }
        return new String(chars, 0, writePos);
    }
}
