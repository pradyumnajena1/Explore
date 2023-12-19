package hackerrank.medium;

import epp.DisjointUnionSet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class RoadsAndLibraries {
    public static void main(String[] args) throws IOException {
       /* System.out.println(roadsAndLibraries(3, 2, 1, new ArrayList<>(List.of(
                new ArrayList<>(List.of(1, 2)),
                new ArrayList<>(List.of(3, 2)),
                new ArrayList<>(List.of(1, 3))))));

        System.out.println(roadsAndLibraries(6, 2, 5, new ArrayList<>(List.of(
                new ArrayList<>(List.of(1, 3)),
                new ArrayList<>(List.of(3, 4)),
                new ArrayList<>(List.of(2, 4)),
                new ArrayList<>(List.of(1, 2)),
                new ArrayList<>(List.of(2, 3)),
                new ArrayList<>(List.of(5, 6))
                ))));*/
        main2(null);
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                int c_lib = Integer.parseInt(firstMultipleInput[2]);

                int c_road = Integer.parseInt(firstMultipleInput[3]);

                List<List<Integer>> cities = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        cities.add(
                                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                        .map(Integer::parseInt)
                                        .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                long result =  roadsAndLibraries(n, c_lib, c_road, cities);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static long roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> cities) {
        // Write your code here
        DisjointUnionSet disjointUnionSet = new DisjointUnionSet(n+1);
        long numHospitals = n;
        long numRoads = 0;
        for(List<Integer> edge:cities){
            Integer source = edge.get(0);
            int parentSource = disjointUnionSet.find(source);
            Integer destination = edge.get(1);
            int parentDestination = disjointUnionSet.find(destination);
            if(parentSource!=parentDestination){
                if(c_lib>c_road){
                    disjointUnionSet.union(source,destination);
                    numHospitals--;
                    numRoads++;
                }
            }
        }
        return numHospitals*c_lib+numRoads*c_road;

    }
}
