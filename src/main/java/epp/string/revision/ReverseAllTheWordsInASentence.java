package epp.string.revision;

import epp.array.ArrayUtils;

public class ReverseAllTheWordsInASentence {

  public static void main(String[] args) {
    String reversed = reverse("  Alice likes   Bob  ");
    System.out.println(reversed);
  }

  private static String reverse(String s) {

    char[] charArray = s.toCharArray();
    reverse(charArray);
    return new String(charArray);
  }

  private static void reverse(char[] chars) {
    reverse(chars, 0, chars.length);
    int start = 0;
    int end;
    while ((end = find(chars,start,' '))!=-1){
      reverse(chars,start,end);
      start = end+1;
    }
    reverse(chars,start,chars.length);
  }

  private static int find(char[] chars, int start, char c) {
    for(int i=start;i<chars.length;i++){
      if(chars[i] == c){
        return i;
      }
    }
    return -1;
  }

  private static void reverse(char[] chars, int start, int end) {
    if (start >= end) {
      return;
    }
    end = end - 1;
    while (start < end) {
      ArrayUtils.swap(chars, start++, end--);
    }
  }
}
