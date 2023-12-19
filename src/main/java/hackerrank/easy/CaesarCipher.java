package hackerrank.easy;

public class CaesarCipher {
    public static void main(String[] args) {
        System.out.println(caesarCipher("abcdefghijklmnopqrstuvwxyz", 2));
        System.out.println(caesarCipher("abcdefghijklmnopqrstuvwxyz", 3));
    }

    public static String caesarCipher(String s, int k) {
        // Write your code here
        char[] chars = s.toCharArray();
        for(int i=0;i<chars.length;i++){
            if(Character.isAlphabetic(chars[i])){
                if(Character.isLowerCase(chars[i])){

                    chars[i] = Character.valueOf((char) ('a'+ (chars[i]-'a' + k )%26));
                }else{
                    chars[i] = Character.valueOf((char) ('A'+ (chars[i]-'A' + k )%26));
                }
            }
        }
        return new String(chars);
    }
}
