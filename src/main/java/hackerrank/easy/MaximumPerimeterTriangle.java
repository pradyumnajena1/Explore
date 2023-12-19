package hackerrank.easy;

import epp.Triplet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MaximumPerimeterTriangle {
    public static void main(String[] args) {
        System.out.println(maximumPerimeterTriangle(new ArrayList<>(List.of(1, 1, 1, 2, 3, 5))));
        System.out.println(maximumPerimeterTriangle(new ArrayList<>(List.of( 1, 2, 3 ))));
        System.out.println(maximumPerimeterTriangle(new ArrayList<>(List.of(1 ,1 ,1 ,3, 3 ))));
    }
    public static List<Integer> maximumPerimeterTriangle(List<Integer> sticks) {
        // Write your code here
        List<Triplet<Integer,Integer,Integer> > triangles = new ArrayList<>();
        sticks.sort(Comparator.reverseOrder());
        for(int i=0;i<sticks.size();i++){
            for(int j=i+1;j<sticks.size();j++){
                for(int k=j+1;k<sticks.size() && sticks.get(k)+sticks.get(j)>sticks.get(i);k++){

                    triangles.add(new Triplet<>(sticks.get(i),sticks.get(j),sticks.get(k) ));
                }
            }
        }
        if(triangles.size()==0){
            return new ArrayList<>(List.of(-1));
        }
        Integer maxPerimeter = null;
        for(int i=0;i<triangles.size();i++){
            Triplet<Integer, Integer, Integer> triplet = triangles.get(i);
            int perimeter = getPerimeter(triplet);
            if(maxPerimeter==null||maxPerimeter< perimeter){
                maxPerimeter = perimeter;
            }
        }
        Integer finalMaxPerimeter = maxPerimeter;
        List<Triplet<Integer, Integer, Integer>> maxTriangles = triangles.stream().filter(triplet -> getPerimeter(triplet) == finalMaxPerimeter).collect(Collectors.toList());
        Comparator<Triplet<Integer, Integer, Integer>> firstSideMax =
                Comparator.comparingInt( (Triplet<Integer, Integer, Integer> triplet )-> triplet.getFirst()).reversed();
        Comparator<Triplet<Integer, Integer, Integer>> thirdSideMax =
                Comparator.comparingInt( (Triplet<Integer, Integer, Integer> triplet )-> triplet.getThird()).reversed();

        maxTriangles.sort(firstSideMax.thenComparing(thirdSideMax));
        Triplet<Integer, Integer, Integer> triplet = maxTriangles.get(0);
        List<Integer> result = new ArrayList<>();
        result.add(triplet.getThird());
        result.add(triplet.getSecond());
        result.add(triplet.getFirst());
        return result;


    }

    public static int getPerimeter(Triplet<Integer, Integer, Integer> triplet) {
        return triplet.getFirst() + triplet.getSecond() + triplet.getThird();
    }
}
