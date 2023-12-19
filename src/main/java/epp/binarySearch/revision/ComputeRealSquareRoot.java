package epp.binarySearch.revision;

public class ComputeRealSquareRoot {
   private static double epsilon = 0.0000000001;
    public static void main(String[] args) {
        double value = 10;
        double sqrt = computeRealSquareRoot(value, epsilon);
        System.out.println(sqrt);
    }

    private static double computeRealSquareRoot(double value, double epsilon) {
        double start = 1.0;
        double end = value;
        if(value<1.0){
            start = 0.0;
            end = 1.0;
        }
        Double sqrt = null;

        while ( compare(start,end,epsilon)==-1) {
            double mid = start + (end - start) *0.5;
            double square_m = mid * mid;
            System.out.println(square_m);
            if (compare(square_m,value,epsilon)==0 ) {
                sqrt = mid;
                break;
            } else if ( compare(square_m,value,epsilon)==1) {
                end = mid  ;

            } else {
                start = mid  ;
            }
        }
        return sqrt!=null?sqrt:start;
    }

    private static int compare(double a, double b,double epsilon) {
        double diff = (a-b)/b;

        return diff< - epsilon?-1: diff> epsilon?1:0;
    }


}
