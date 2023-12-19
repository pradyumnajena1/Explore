package hackerrank.hard;

import epp.array.ArrayUtils;
import epp.hashmap.revision.Trie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuildPalindrome {
    public static void main(String[] args) {
        System.out.println(buildPalindrome("pgjlcktlaqsalnoiphbtuy",
                "rrghseznkmzukaiihat"));
    }

    public static String buildPalindrome(String a, String b) {
        // Write your code here
        Trie<Character> trie = buildReverseSubStringTrie(b);
        String maxPalindrome="";

        for(int i=0;i<a.length();i++){
            for(int j=i+1;j<=a.length();j++){
                String substring = a.substring(i,j);
                List<Character> characters = arrayToList(substring.toCharArray());
                String palindrome="";
                if( substring.length()*2 >= palindrome.length() && trie.contains(characters)){
                      palindrome = substring+ reverse(substring);
                    if(palindrome.length()>maxPalindrome.length()||
                            palindrome.length()==maxPalindrome.length()&&palindrome.compareTo(maxPalindrome)<0){
                        maxPalindrome = palindrome;
                    }
                }

                if( substring.length()*2-1 >= palindrome.length() && trie.contains(characters.subList(0,
                        characters.size()-1))){
                      palindrome = substring + reverse(substring.substring(0,substring.length()-1));
                    if(palindrome.length()>maxPalindrome.length()||
                            palindrome.length()==maxPalindrome.length()&&palindrome.compareTo(maxPalindrome)<0){
                        maxPalindrome = palindrome;
                    }
                }
                if( substring.length()*2+1 >= palindrome.length() && trie.containsAsPrefix(characters)){
                    List<List<Character>> wordsWithPrefix = trie.getWordsWithPrefix(characters);
                    for(List<Character> wordWithPrefix:wordsWithPrefix){
                        List<Character> suffix = wordWithPrefix.subList(characters.size(), wordWithPrefix.size());
                        if(isPalindrome(suffix)){
                            StringBuilder sb = new StringBuilder();
                            sb.append(substring);
                            for(Character ch:suffix){
                                sb.append(ch);
                            }
                            sb.append(substring);
                            palindrome=sb.toString();
                            if(palindrome.length()>maxPalindrome.length()||
                                    palindrome.length()==maxPalindrome.length()&&palindrome.compareTo(maxPalindrome)<0){
                                maxPalindrome = palindrome;
                            }
                        }
                    }



                }




            }
        }
        return maxPalindrome.equals("")?"-1": maxPalindrome;
    }

    private static boolean isPalindrome(List<Character> suffix) {
        int start = 0,end = suffix.size()-1;
        while (start<end){
            if(suffix.get(start)!=suffix.get(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    private static Trie<Character> buildReverseSubStringTrie(String b) {
        Trie<Character> trie = new Trie<>();
        b = reverse(b);
        for(int i=0;i<b.length();i++){
            for(int j=i+1;j<=b.length();j++){
                String substring = b.substring(i,j);
                char[] charArray = substring.toCharArray();
                List<Character> charSequence = arrayToList(charArray);
                trie.add( charSequence);

            }
        }
        return trie;
    }

    private static List<Character> arrayToList(char[] charArray) {
        List<Character> charSequence = new ArrayList<>();
        for(char c: charArray){
            charSequence.add(c);
        }
        return charSequence;
    }
    private static String reverse(String s){
        char[] charArray = s.toCharArray();
        reverse(charArray);
        return  new String(charArray);
    }

    private static void reverse(char[] charArray) {
        int left = 0, right= charArray.length-1;
        while (left < right) {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;right--;
        }
    }
}
