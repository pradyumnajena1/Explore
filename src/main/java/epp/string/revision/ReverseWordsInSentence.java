package epp.string.revision;

import epp.array.ArrayUtils;

public class ReverseWordsInSentence {
    public static void main(String[] args) {
        String s = " Alice likes Bob  ";
       s =  reverseWordsInSentence(s);
        System.out.println(s);
    }

    private static String reverseWordsInSentence(String s) {
        char[] charArray = s.toCharArray();
        reverseString(charArray,0,s.length()-1);
        System.out.println(new String(charArray));
        int start=0;
        int end=0;
        while (end<charArray.length){
            while (start<charArray.length && charArray[start]==' '){
                start++;
                end++;
            }
            while (end < charArray.length && charArray[end]!=' '){
                end++;
            }
            reverseString(charArray,start,end-1);
            start=end;
        }
        return new String(charArray);
    }
    private static void reverseString(char[] s, int start, int end){
        while (start<end){
            ArrayUtils.swap(s,start++,end--);
        }
    }
}
