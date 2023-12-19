package hackerrank.easy;

public class CountingValleys {
    public static void main(String[] args) {
        System.out.println(countingValleys(8, "DDUUUUDD"));
        System.out.println(countingValleys(8, "UDDDUDUU"));
    }

    public static int countingValleys(int steps, String path) {
        // Write your code here

        int depth = 0;
        int count = 0;
        for (int i = 0; i < steps; i++) {
            if (path.charAt(i) == 'D') {
                depth++;
            } else {
                depth--;
                if (depth == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
