package hackerrank.easy;

import java.util.HashMap;
import java.util.Map;

public class WatchTime {
    public static void main(String[] args) {
        System.out.println(timeConversion("07:45:05 PM"));
        System.out.println(timeConversion("12:00:00 AM"));
        System.out.println(timeConversion("12:00:00 PM"));
        System.out.println(timeConversion("12:45:45 PM"));


        Map<String,Integer> map1 = new HashMap<>();
        map1.put("hello",1);
        map1.put("world",1);

        Map<String,Integer> map2 = new HashMap<>();
        map2.put("hello",1);
        map2.put("worlds",1);

        map2.forEach((key,value)-> map1.merge(key,value,(v1,v2)->v1+v2));
        System.out.println(map1);
    }
    public static String timeConversion(String s) {
        // Write your code here
        String  hourString =  s.substring(0, 2);
        String  minute =  s.substring(3, 5);
        String  second =  s.substring(6, 8);
        int hour = Integer.parseInt(hourString.charAt(0)=='0'?hourString.substring(1):hourString);

        if(s.endsWith("AM")){
            hour = (12 + hour)%12;

        }else{
            hour = 12 + hour;
        }
        hourString = hour+"";

        if(hour<10){
            hourString = "0"+hour;
        }
        return hourString+":"+minute+":"+second;

    }
}
