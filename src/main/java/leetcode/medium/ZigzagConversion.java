package leetcode.medium;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 6. Zigzag Conversion
 * Medium
 * Topics
 * Companies
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 */
public class ZigzagConversion {
    public static void main(String[] args) {
        System.out.println(new ZigzagConversion().convert("PAYPALISHIRING", 3));
        System.out.println(new ZigzagConversion().convert("PAYPALISHIRING", 4));
    }
    public String convert(String s, int numRows) {
         StringBuilder[] builders = new StringBuilder[numRows];
         for(int i=0;i<builders.length;i++){
             builders[i] = new StringBuilder();
         }
         int sum=0;
         int delta = 1;
         for(int i=0;i<s.length();i++){
             builders[sum] .append(s.charAt(i));
             if(sum==0){
                 delta=1;
             }else if(sum==numRows-1){
                 delta=-1;
             }
             sum+=delta;
         }
       String result=  Arrays.stream(builders).map(x->x.toString()).collect(Collectors.joining(""));
         return result;
    }
}
