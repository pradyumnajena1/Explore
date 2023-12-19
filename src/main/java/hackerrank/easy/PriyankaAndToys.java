package hackerrank.easy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PriyankaAndToys {

    public static void main(String[] args) {
        System.out.println(toys(new ArrayList<>(List.of(1, 2, 3, 4, 5, 10, 11, 12, 13))));
        System.out.println(toys(new ArrayList<>(List.of(1, 2, 3, 21, 7, 12, 14, 21))));
    }

    public static int toys(List<Integer> w) {
        // Write your code here
        if(w.size()==0){
            return 0;
        }
       w.sort(Comparator.naturalOrder());
       Integer min = w.get(0);
       int containerCount=1;
       for(int i=1;i<w.size();i++){
           if(w.get(i)>min+4){
               containerCount++;
               min = w.get(i);
           }
       }
       return containerCount;
    }
}
