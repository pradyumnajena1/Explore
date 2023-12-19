package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

public class NumberToWords {
    public static void main(String[] args) {
        Solution solution = new Solution();
     /*   System.out.println(solution.numberToWords(600));
        System.out.println(solution.numberToWords(604));
        System.out.println(solution.numberToWords(614));
        System.out.println(solution.numberToWords(624));
        System.out.println(solution.numberToWords(634));
        System.out.println(solution.numberToWords(1634));*/
        System.out.println(solution.numberToWords(1234634));
        System.out.println(solution.numberToWords(2301234634L));
    }
    private static class Solution {
        private Map<Integer,String> digits = getDigitsMap();
        private Map<Integer,String> tens = getTensMap();
        private Map<Integer,String> teens = getTeensMap();

        private Map<Integer, String> getTeensMap() {
            HashMap<Integer, String> result = new HashMap<>();
            result.put(11,"Eleven");
            result.put(12,"Twelve");
            result.put(13,"Thirteen");
            result.put(14,"Fourteen");
            result.put(15,"Fifteen");
            result.put(15,"Sixteen");
            result.put(15,"Seventeen");
            result.put(15,"Eighteen");
            result.put(15,"Nineteen");
            return result;
        }

        private Map<Integer,String> powers = getPowersMap();

        private Map<Integer, String> getPowersMap() {
            HashMap<Integer, String> powers = new HashMap<>();

            powers.put(100,"Hundreds");

            powers.put(1000,"Thousands");
            powers.put(1000000,"Millions");
            powers.put(1000000000,"Billions");
            return powers;
        }

        private Map<Integer, String> getTensMap() {
            HashMap<Integer, String> result = new HashMap<>();
            result.put(10,"Ten");
            result.put(20,"Twenty");
            result.put(30,"Thirty");
            result.put(40,"Forty");
            result.put(50,"Fifty");
            result.put(60,"Sixty");
            result.put(70,"Seventy");
            result.put(80,"Eighty");
            result.put(90,"Ninety");
            return result;
        }

        private static HashMap<Integer, String> getDigitsMap() {
            HashMap<Integer, String> result = new HashMap<>();
            result.put(1,"One");
            result.put(2,"Two");
            result.put(3,"Three");
            result.put(4,"Four");
            result.put(5,"Five");
            result.put(6,"Six");
            result.put(7,"Seven");
            result.put(8,"Eight");
            result.put(9,"Nine");
            return result;
        }
        public String numberToWords(long num){
            return numberToWords(num,1);
        }

        public String numberToWords(long num,int power) {
             if(num<1000){
                 return numberToWordsBelowThousand((int) num);
             }else {
                 int reminder = (int) (num%1000);
                 num = num/1000;
                 StringBuilder sb = new StringBuilder();
                 if(num>0){

                     String str = powers.get((int) Math.pow(1000, power));
                     sb.append(numberToWords(num,power+1)).append(" ").append(str);
                 }
                 if(reminder>0){

                     sb.append(" ") .append(numberToWordsBelowThousand(reminder));
                 }
                 return sb.toString();
             }
        }

        private String numberToWordsBelowThousand(int num) {
            StringBuilder sb = new StringBuilder();
            int hundreds = (int) (num/100);
            if(hundreds>0){
                sb.append(digits.get(hundreds)).append(" ").append(powers.get(100));
            }
            num = num%100;
            if(num>0){
                int ten = num/10;
                if(ten>=2){
                    int digit = num%10;
                    sb.append(" ").append(tens.get(ten*10)).append(" ").append(digits.get(digit));
                }else{
                    int digit = num%10;
                    if(digit==0 && ten==1){
                        sb.append(" ").append(tens.get(10));
                    }else if(ten==1){
                        sb.append(" ").append(teens.get(num));
                    }else if(ten==0){
                        sb.append(" ").append(digits.get(digit));
                    }
                }
            }



            return sb.toString();
        }
    }
}
