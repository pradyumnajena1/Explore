package meta;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words.
 * The returned string should only have a single space separating the words.
 * Do not include any extra spaces.
 */
public class ReverseWordsInString {
    public static void main(String[] args) {
        String s = "  The   sky   is blue ";
      String reversed =   reverseWordsInString(s);
        System.out.println(reversed);

    }

    private static String reverseWordsInString(String s) {
        List<String> words = getWords(s);
        reverseList(words);
        String result = words.stream().collect(Collectors.joining(" "));
        return result;
    }

    private static void reverseList(List<String> words) {
        int low = 0;
        int high = words.size()-1;
        while (low<high){
            swap(words,low++,high--);
        }
    }

    private static void swap(List<String> words, int i, int j) {
        String t = words.get(i);
        words.set(i,words.get(j));
        words.set(j,t);
    }

    private static List<String> getWords(String s) {
        boolean inWord = false;
        int lastWordStart = 0;
        List<String> words = new ArrayList<>();
        for(int i = 0; i< s.length(); i++){
            if(s.charAt(i)==' '){
                if(inWord){
                    inWord = false;
                    words.add(s.substring(lastWordStart,i));

                }
            } else   {
                if(!inWord){
                    inWord = true;
                    lastWordStart = i;
                }
            }
        }
        return words;
    }
}
