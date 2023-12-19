package hackerrank.easy;

public class BeautifulBinaryString {
    public static void main(String[] args) {
        System.out.println(beautifulBinaryString("0101010"));
        System.out.println(beautifulBinaryString("01100"));
        System.out.println(beautifulBinaryString("0100101010"));
    }

    public static int beautifulBinaryString(String b) {
        // Write your code here
        if (b.length() < 3) {
            return 0;
        }
        char[] chars = b.toCharArray();
        int changeCount = 0;
        for (int i = 2; i < chars.length; i++) {
            if (chars[i - 2] == '0' && chars[i - 1] == '1' && chars[i] == '0') {
                changeCount++;
                chars[i] = 1;
            }
        }
        return changeCount;
    }
}
