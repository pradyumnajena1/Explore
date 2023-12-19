package hackerrank.easy;

public class MinimumNumber {
    public static void main(String[] args) {
        System.out.println(minimumNumber(6, "LY!Z"));
    }

    public static int minimumNumber(int n, String password) {
        // Return the minimum number of characters to make the password strong
        String numbers = "0123456789";
        String lower_case = "abcdefghijklmnopqrstuvwxyz";
        String upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String special_characters = "!@#$%^&*()-+";
        boolean numbersMissing = true;
        boolean upper_caseMissing = true;
        boolean lower_caseMissing = true;
        boolean special_charactersMissing = true;
        for (int i = 0; i < password.length(); i++) {

            if (numbers.indexOf(password.charAt(i)) >= 0) {
                numbersMissing = false;
            } else if (lower_case.indexOf(password.charAt(i)) >= 0) {
                lower_caseMissing = false;
            } else if (upper_case.indexOf(password.charAt(i)) >= 0) {
                upper_caseMissing = false;
            } else if (special_characters.indexOf(password.charAt(i)) >= 0) {
                special_charactersMissing = false;
            }
        }
        int reqdChars = 0;
        if(numbersMissing){
            reqdChars++;
        }
        if(lower_caseMissing){
            reqdChars++;
        }
        if(upper_caseMissing){
            reqdChars++;
        }
        if(special_charactersMissing){
            reqdChars++;
        }
        int lengthd = 0;
        if(password.length()<6){
            lengthd = 6-password.length();
        }
        return Math.max(reqdChars,lengthd);
    }
}
