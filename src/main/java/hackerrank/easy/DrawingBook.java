package hackerrank.easy;

public class DrawingBook {
    public static void main(String[] args) {

    }
    public static int pageCount(int n, int p) {
        // Write your code here
        int fromFront = p/2;
        int fromBack = n%2==1? (n-p)/2: (n-p)/2+1;
        return Math.min(fromBack,fromFront);

    }
}
