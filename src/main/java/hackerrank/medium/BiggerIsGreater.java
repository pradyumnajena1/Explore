package hackerrank.medium;

import epp.array.ArrayUtils;

public class BiggerIsGreater {
    public static void main(String[] args) {
         System.out.println(biggerIsGreater("652431"));
        System.out.println(biggerIsGreater("abcd"));
        System.out.println(biggerIsGreater("ab"));
        System.out.println(biggerIsGreater("bb"));
        System.out.println(biggerIsGreater("dhck"));
        System.out.println(biggerIsGreater("dkhc"));
    }
    public static String biggerIsGreater(String w) {
        // Write your code here
        char[] charArray = w.toCharArray();
        String nextPerm = new String(biggerIsGreater(charArray));
        if(w.equals(nextPerm)){
            return "no answer";
        }
        return nextPerm;
    }

    private static char[] biggerIsGreater(char[] charArray) {
        if(charArray.length<=1){
            return charArray;
        }
        int start = charArray.length-2;
        while (start>=0 && charArray[start]>=charArray[start+1]){
            start--;
        }
        if(start==-1){
            return charArray;
        }
        // find the next biggest char
        int start2 = charArray.length-1;
        while (charArray[start]>=charArray[start2]){
            start2--;
        }
        swap(charArray,start2,start);
         reverse(charArray,start+1,charArray.length-1);


        return charArray;
    }
    private static void reverse(char[] array, int left, int right) {
        while (left < right) {
            swap(array, left++, right--);
        }
    }

    public static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
