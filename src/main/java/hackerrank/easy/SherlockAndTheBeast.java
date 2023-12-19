package hackerrank.easy;

public class SherlockAndTheBeast {
    public static void main(String[] args) {
           decentNumber(1);
           decentNumber(3);
           decentNumber(5);
           decentNumber(11);
    }

    public static void decentNumber(int n) {
        // Write your code here
        int i = 0;
        int j = 0;

        int threes = 0;
        int fives = 0;
        for (i = 0; i <= n  ; i+=3) {
            for (j = 0; j <= n  ; j+=5) {
                if (i   + j   == n) {
                    fives=i;
                    threes=j;

                    break;
                }
            }
        }
        if(threes==0&&fives==0){
            System.out.print(-1);
        }else{
            for(int k=0;k<fives;k++){
                System.out.print(5);
            }
            for(int k=0;k<threes;k++){
                System.out.print(3);
            }
        }
        System.out.println();

    }
}
