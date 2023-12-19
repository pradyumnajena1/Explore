package epp.binarySearch.revision;

/**
 * all the numbers appears exactly thrice while one appears only twice, find that number
 */
public class FindNumberAppearingOnlyTwice {
    public static void main(String[] args) {
        int sum = 0;
        for(int i=1;i<10;i++){
            sum = sum ^ i;
            if(i!=9){
                sum = sum ^ i;
                sum = sum ^ i;
            }
        }
        System.out.println(sum);
    }
}
