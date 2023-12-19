package epp.utils;

public class StringUtils {
    public static String getBinaryString(int i) {
        int width = 32;
        return getBinaryString(i, width);
    }

    public static String getBinaryString(int i, int width) {
        String binaryString = Integer.toBinaryString(i);
        char prependChar = '0';
        return getLeftPaddedString(width, binaryString, prependChar);
    }

    public static String getLeftPaddedString(int width, String string, char prependChar) {
        String replace = String.format("%1$"+ width +"s" , string).replace(' ', prependChar);
        return replace;
    }
    public static String getRightPaddedString(int width, String string, char prependChar) {
        String replace = String.format("%1$"+ -width +"s" , string).replace(' ', prependChar);
        return replace;
    }

    public static void main(String[] args) {
        System.out.println(getRightPaddedString(10,"hello",'-'));
        System.out.println(getLeftPaddedString(10,"hello",'-'));
    }

}
