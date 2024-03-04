package meta;

public class MultiplyTwoStrings {
    public static void main(String[] args) {
        String s1 = "4154";
        String s2 = "51454";
        String mul = multiply(s1,s2);
        System.out.println(mul);
    }

    private static String multiply(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if(len1==0 || len2==0){
            return "0";
        }
        int[] result = new int[len1+len2];
        int index1 = 0;
        int index2 = 0;

        for(int i = len1-1;i>=0;i--){
            int carry = 0;
            int n1 = s1.charAt(i)-'0';

            index2 = 0;
            for(int j= len2-1;j>=0;j--){

                int n2 = s2.charAt(j)-'0';
                int sum = n1 * n2 + result[index1+index2]+carry;
                int digit = sum%10;
                carry = sum/10;
                result[index1+index2] = digit;
                index2++;
            }

            if(carry>0){
                result[index1+index2] = carry;
            }

            index1++;
        }
        int i = result.length-1;
        while (i>=0 && result[i]==0){
            i--;
        }
        if(i==-1){
            return "0";
        }
        String resultString = "";
        while (i>=0){
            resultString+=result[i];
            i--;
        }

        return resultString;
    }


}
