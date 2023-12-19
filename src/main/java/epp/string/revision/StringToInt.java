package epp.string.revision;

public class StringToInt {
    public static String[] digits = { "","one","two","three","four","five","six","seven","eight","nine"};
    public static String[] teens = {"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen",
            "nineteen"};
    public static  String[] tens = {"","", "twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};

    public static void main(String[] args) {
        String value = "22345678";
        int intValue= stringToInt(value);
        System.out.println(intValue);
        value=intToString(intValue);
        System.out.println(value);
    }

    private static String intToString(int intValue) {
        String result = "";

        if(intValue<0){
            result = "-";
            intValue = -intValue;
        }
        String[] powers = {"", " Thousand"," Million"," Billion"};
        int powerIndex = 0;
        while (intValue>0){
            int reminder = intValue%1000;
            result =  intToStringBelowThousand(reminder) + powers[powerIndex] +" "+ result;
            intValue = intValue /1000;
            powerIndex++;
        }


        return result;
    }

    private static String intToStringBelowThousand(int value) {
        String result = "";
        if(value>100){
            result =digits[ (value /100)] + " Hundreds " + intToStringBelowHundred(value%100);
        }else{
           result= intToStringBelowHundred(value);
        }
        return result;
    }

    private static String intToStringBelowHundred(int value) {
          if(value==0){
            return "";
        }
        if(value<10){
            return digits[value];
        }
        if(value<20){
            return teens[value-10];
        }
        return tens[value/10] + digits[value%10];
    }

    private static int stringToInt(String value) {
        if(value==null || value.length()==0){
            throw new IllegalArgumentException("invalid string");
        }
        int sign =1;
        if(value.startsWith("-")){
            sign = -1;
            value = value.substring(1);
        }
        int intValue = 0;
        for(int i=0;i<value.length();i++){
            intValue = intValue*10 + value.charAt(i) - '0';
        }
        return sign*intValue;
    }
}
