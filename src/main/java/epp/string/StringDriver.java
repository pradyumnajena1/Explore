package epp.string;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class StringDriver {
  public static void main(String[] args) {
    System.out.println(decodeExcelColdId("AB"));
    System.out.println(encodeExcelCol(28));
    int[] A = {4, 0, 0, 0, 0};
    int[] B = {1, 2, 3, 4};
    mergeSortedArrays(A, B);
    ArrayUtils.printArray(A);
    System.out.println(isPalindrome("A man, a plan, a canal, Panama."));
    char[] charArray = "hello world sachin tendulkar".toCharArray();
    reverseWords(charArray);
    ArrayUtils.printArray(charArray);
    System.out.println(phoneNmemonics("2276696"));
  }

  public static int decodeExcelColdId(String col) {
    int id = 0;
    for (int i = 0; i < col.length(); i++) {
      char c = col.charAt(i);
      id = id * 26 + (c - 'A' + 1);
    }
    return id;
  }

  public static String encodeExcelCol(int col) {
    StringBuilder sb = new StringBuilder();

    while (col > 0) {
      sb.append((char) ('A' + (col - 1) % 26));
      col = (col - 1) / 26;
    }
    return sb.reverse().toString();
  }

  // remove bs and replace as with dds
  public static int replaceAndRemove(char[] arr) {
    int writeIndex = 0;
    int replaceCount = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != 'b') {
        arr[writeIndex++] = arr[i];
      } else if (arr[i] == 'a') {
        replaceCount++;
      }
    }
    int finalSize = writeIndex + replaceCount;
    int redaIndex = writeIndex - 1;
    writeIndex = finalSize - 1;
    for (int i = redaIndex; i >= 0; i++) {
      if (arr[i] == 'a') {
        arr[writeIndex--] = 'd';
        arr[writeIndex--] = 'd';
      } else {
        arr[writeIndex--] = arr[i];
      }
    }
    return finalSize;
  }

  public static void mergeSortedArrays(int[] A, int[] B) {
    int readIndexB = B.length - 1;
    int readIndexA = A.length - B.length - 1;
    int writeIndex = A.length - 1;

    while (readIndexB >= 0 && readIndexA >= 0) {
      if (A[readIndexA] >= B[readIndexB]) {
        A[writeIndex--] = A[readIndexA--];
      } else {
        A[writeIndex--] = B[readIndexB--];
      }
    }
    while (readIndexB >= 0) {
      A[writeIndex--] = B[readIndexB--];
    }
  }

  public static boolean isPalindrome(String s) {
    int i = 0;
    int j = s.length() - 1;
    while (i < j) {

      while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
        i++;
      }
      while (j > i && !Character.isLetterOrDigit(s.charAt(j))) {
        j--;
      }
      if (Character.toLowerCase( s.charAt(i)) !=Character.toLowerCase( s.charAt(j))) {
        return false;
      }
      i++;
      j--;
    }

    return true;
  }

  public static void reverseWords(char[] str){
        reverse(str, 0, str.length);
        int start = 0,end;
        while((end = find(str,' ',start)) !=-1 ){
          reverse(str, start, end);
          start = end+1;
        }
  }

  private static int find(char[] str, char c, int start) {
    for(int i = start; i < str.length; i++){
      if(str[i] == c){
        return i;
      }
    }
    return -1;
  }

  private static void reverse(char[] str, int start, int end) {
    for(int i = start,j=end-1; i < j; i++,j--){
      ArrayUtils.swap(str,i,j);
    }
  }

  public static List<String> phoneNmemonics(String phoneNumber){
    String[] mapping = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> result = new ArrayList<>();
    char[] partialResult  = new char[phoneNumber.length()] ;
    phoneMnemonicsHelper(phoneNumber, 0, partialResult, result, mapping);
    return result;
  }

  private static void phoneMnemonicsHelper(String phoneNumber, int i, char[] partialResult, List<String> result,
                                         String[] mapping) {
    if(i == phoneNumber.length()){
      result.add(new String(partialResult));
      return;
    }
    String digits = mapping[phoneNumber.charAt(i) - '0'];
    for(char digit : digits.toCharArray()){
      partialResult[i] = digit;
      phoneMnemonicsHelper(phoneNumber, i+1, partialResult, result, mapping);
    }
  }
}
