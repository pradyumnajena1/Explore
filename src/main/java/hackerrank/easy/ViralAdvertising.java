package hackerrank.easy;

public class ViralAdvertising {
    public static void main(String[] args) {
        System.out.println(viralAdvertising(5));
    }

    public static int viralAdvertising(int n) {
        // Write your code here
        int shared = 5;
        int cumulativeLiked = 0;
        for (int i = 1; i <= n; i++) {
            int liked = shared / 2;
            cumulativeLiked = cumulativeLiked + liked;
            shared = liked * 3;
        }
        return cumulativeLiked;

    }
}
