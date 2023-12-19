package hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GridChallenge {
    public static void main(String[] args) {
        System.out.println(gridChallenge(new ArrayList<>(List.of("mpxz",
                "abcd",
                "wlmf"))));
    }

    public static String gridChallenge(List<String> grid) {
        // Write your code here
        for(int i=0;i<grid.size();i++){
            char[] charArray = grid.get(i).toCharArray();
            Arrays.sort(charArray);
            grid.set(i,new String(charArray));
        }
       // grid.sort(Comparator.naturalOrder());
        for(int col = 0;col<grid.get(0).length();col++){
            for(int row=0;row<grid.size()-1;row++){
                if(grid.get(row).charAt(col)>grid.get(row+1).charAt(col)){
                    return "NO";
                }
            }
        }
        return "YES";

    }
}
