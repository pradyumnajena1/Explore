package hackerrank.easy;

public class SaveThePrisoner {
    public static void main(String[] args) {
        System.out.println(saveThePrisoner(4, 6, 2));
        System.out.println(saveThePrisoner(5, 2, 1));
        System.out.println(saveThePrisoner(5, 2, 2));
    }
    public static int saveThePrisoner(int n, int m, int s) {
        // Write your code here
       return  ((s-1) + (m-1) ) % n + 1;

    }
}
