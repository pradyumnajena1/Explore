package meta;

public class AddTwoBinaryStrings {
    public static void main(String[] args) {
        System.out.println(addTwoBinaryStrings("11", "1"));
        System.out.println(addTwoBinaryStrings("1101", "100"));
    }

    private static String addTwoBinaryStrings(String s1, String s2) {

            return addTwoBinaryStrings(s1,s2,s1.length()-1,s2.length()-1,0);
    }

    private static String addTwoBinaryStrings(String s1, String s2, int index1, int index2, int carry) {
        if(index1<0 && index2<0){
           return carry>0?String.valueOf(carry):"";
        }
        int sum = carry;
        if(index1>=0){
            sum+= s1.charAt(index1)-'0';
        }
        if(index2>=0){
            sum+= s2.charAt(index2)-'0';
        }
        int digit = sum%2;
        carry = sum/2;
        return addTwoBinaryStrings(s1,s2,index1-1,index2-1,carry)+String.valueOf(digit);
    }
}
