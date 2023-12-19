package epp.string;

import java.util.HashMap;
import java.util.Map;

public class RomanToDecimal {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);
        map.put("IV",4);
        map.put("IX",9);
        map.put("XL",40);
        map.put("XC",90);
        map.put("CD",400);
        map.put("CM",900);

        System.out.println(romanToDecimal("LIX",map));
        System.out.println(romanToDecimal("XXXXXIIIIIIII",map));
    }

    private static int romanToDecimal(String romanString, Map<String, Integer> map) {
        int value =0;
        while(!romanString.isEmpty()){
            if(romanString.startsWith("CM") || romanString.startsWith("CD") ||  romanString.startsWith("XC")  || romanString.startsWith("XL") || romanString.startsWith("IX") || romanString.startsWith("IV")  ){
                value+= map.get(romanString.substring(0,2));
                romanString = romanString.substring(2);
            }else{
                value+= map.get(romanString.substring(0,1));
                romanString = romanString.substring(1);
            }
        }
        return value;
    }
}
