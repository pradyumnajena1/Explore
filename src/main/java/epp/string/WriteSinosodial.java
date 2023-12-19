package epp.string;

import epp.array.ArrayUtils;

public class WriteSinosodial {
    public static void main(String[] args) {
        String s = "Hello_world";
        printSinosodial(s);
    }

    private static void printSinosodial(String s) {
        char[][] array = new char[3][s.length()];
        for(int i=0;i<s.length();i++){
            int reminder = i % 4;
            char ch = s.charAt(i);
            int row = 0;
            if(reminder ==0 || reminder ==2){
                row=1;
            }else if(reminder ==1){
                row=0;
            } else{
                row=2;
            }
            array[row][i] = ch;
        }
        ArrayUtils.print2DArray(array);

    }
}
