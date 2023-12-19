package hackerrank.easy;

public class catAndMouse {
    public static void main(String[] args) {
        System.out.println(catAndMouse(1, 2, 3));
        System.out.println(catAndMouse(1, 3, 2));
    }
    static String catAndMouse(int x, int y, int z) {
        if( Math.abs(x-z) > Math.abs(y-z)){
            return "Cat B";
        }else if(Math.abs(x-z) < Math.abs(y-z)){
            return "Cat A";
        }else{
            return "Mouse C";
        }


    }
}
