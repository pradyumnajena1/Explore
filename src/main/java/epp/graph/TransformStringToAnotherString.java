package epp.graph;

import java.util.*;

public class TransformStringToAnotherString {
    public static void main(String[] args) {
        String source = "dog";
        String target = "fun";

        HashSet<String> dictionary = new HashSet<>();
        dictionary.add("fog");
        dictionary.add("fon");
        dictionary.add("fun");
        List<String> shortestPath = getPath(source,target, dictionary);
        System.out.println(shortestPath);
    }

    private static List<String> getPath(String source, String target, HashSet<String> dictionary) {
        Queue<String> bfsQueue = new ArrayDeque<>();
        Set<String> visitedSet = new HashSet<>();
        Map<String,String> parentMap = new HashMap<>();
        bfsQueue.offer(source);
        visitedSet.add(source);
        boolean found = false;
        while (!bfsQueue.isEmpty()){
            String current = bfsQueue.poll();
            if(current.equals(target)){
                found = true;
                break;
            }
            List<String> neighbours = getNeighbours(current);
            neighbours.stream().filter(x->dictionary.contains(x) && !visitedSet.contains(x)  ).forEach(x->{
                bfsQueue.offer(x);
                visitedSet.add(x);
                parentMap.put(x,current);
            });
        }
        if(found){
            List<String> path = buildPath(parentMap,target);

            return path;
        }
            return new ArrayList<>();
    }

    private static List<String> getNeighbours(String word) {
        List<String> result = new ArrayList<>();
        for(int i=0;i<word.length();i++){
            String prefix = word.substring(0,i);
            char ch = word.charAt(i);
            String suffix  = word.substring(i+1);
            for(char c='a';c<'z';c++){
                if(c!=ch){
                    result.add(prefix+c+suffix);
                }
            }
        }
        return result;
    }

    private static List<String> buildPath(Map<String, String> parentMap, String target) {
        List<String> path = new ArrayList<>();
        String current = target;
        while (current!=null){
            path.add(current);
            current = parentMap.get(current);
        }
        Collections.reverse(path);
        return path;
    }
}
