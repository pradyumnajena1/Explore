package epp.string;

public class StringToInteger {
    public static void main(String[] args) {
        String s = "-123";
        int value = stringToInt(s);
        System.out.println(value);
    }

    public static int stringToInt(String s) {
        int sign = s.charAt(0)=='-'?-1:1;

        char[] absChars = sign==1?s.toCharArray():s.substring(1).toCharArray();
        int absValue = 0;
        for(int i=0;i<absChars.length;i++){
            absValue = absValue*10 +( absChars[i]-'0');
        }
        return absValue*sign;
    }
}
