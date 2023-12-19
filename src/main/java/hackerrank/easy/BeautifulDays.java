package hackerrank.easy;

public class BeautifulDays {
    public static void main(String[] args) {
        System.out.println(beautifulDays(2010, 2030, 6));
    }

    public static int beautifulDays(int i, int j, int k) {
        // Write your code here
        int count = 0;
        for(int n=i;n<=j;n++){
            String number = Integer.toString(n);
            number = removeTrailingZeros(number);
            String revString = reverse(number);
            int revNumber = Integer.parseInt(revString);
            if(Math.abs(n-revNumber)%k==0){
                count++;
            }
        }
        return count;

    }

    private static String removeTrailingZeros(String number) {
        int endIndex = number.length()-1;
        while (number.charAt(endIndex)=='0'){
            endIndex--;
        }
        number =  number.substring(0, endIndex+1);
        return number;
    }

    private static String reverse(String s){
        char[] charArray = s.toCharArray();
        reverse(charArray);
        return  new String(charArray);
    }

    private static void reverse(char[] charArray) {
        int left = 0, right= charArray.length-1;
        while (left < right) {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;right--;
        }
    }
}
