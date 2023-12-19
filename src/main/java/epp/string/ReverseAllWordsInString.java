package epp.string;

import java.util.Arrays;

public class ReverseAllWordsInString {
    public static void main(String[] args) {
        String s = "Alice likes Bob";
        char[] chars = s.toCharArray();
          reverseAllWords(chars);
        System.out.println(Arrays.toString(chars));

    }

    private static  void reverseAllWords(char[] chars) {
        reverse(chars,0,chars.length-1);
        boolean inWord = false;
        int start = -1;

        for(int i=0;i<chars.length;i++){
            if(Character.isAlphabetic(chars[i])){
                if(!inWord){
                    inWord=true;
                    start = i;
                }
            }else {
                if(inWord){
                    inWord=false;
                    reverse(chars,start,i-1);

                }
            }

        }
        reverse(chars,start,chars.length-1);

    }

    private static void reverse(char[] chars, int start, int end) {
        while (start<end){
            swap(chars,start++,end--);
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
