package meta;

public class IntegerToRoman {
    public static void main(String[] args) {
        IntegerToRoman integerToRoman = new IntegerToRoman();
        System.out.println(integerToRoman.intToRoman(27));
        System.out.println(integerToRoman.intToRoman(3));
        System.out.println(integerToRoman.intToRoman(1994));
    }
    public String intToRoman(int num) {
        String romanString = "";
        while (num > 0) {
            if (num >= 1000) {
                int count = num / 1000;
                num = num % 1000;
                while (count > 0) {
                    romanString = romanString + "M";
                    count--;
                }

            }
            if (num >= 900) {
                num = num - 900;
                romanString = romanString + "CM";
            }
            if (num >= 500) {
                num = num - 500;
                romanString = romanString + "D";
            }
            if (num >= 400) {
                num = num - 400;
                romanString = romanString + "CD";
            }
            if (num >= 100) {
                int count = num / 100;
                num = num % 100;
                while (count > 0) {
                    romanString = romanString + "C";
                    count--;
                }
            }
            if (num >= 90) {
                num = num - 90;
                romanString = romanString + "XC";
            }
            if (num >= 50) {
                num = num - 50;
                romanString = romanString + "L";
            }
            if (num >= 40) {
                num = num - 40;
                romanString = romanString + "XL";
            }
            if (num >= 10) {
                int count = num / 10;
                num = num % 10;
                while (count > 0) {
                    romanString = romanString + "X";
                    count--;
                }
            }
            if (num >= 9) {
                num = num - 9;
                romanString = romanString + "IX";
            }
            if (num >= 5) {
                num = num - 5;
                romanString = romanString + "V";
            }
            if (num >= 4) {
                num = num - 4;
                romanString = romanString + "IV";
            }
            while (num > 0) {
                romanString = romanString + "I";
                num--;
            }
        }
        return romanString;
    }
}
