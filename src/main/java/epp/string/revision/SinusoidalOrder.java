package epp.string.revision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SinusoidalOrder {
    public static void main(String[] args) {
        String s = "Hello_World!";
        printSinusoidalOrder(s);
    }

    private static void printSinusoidalOrder(String s) {
        List<Character>[] result = getSinusoidalOrder(s);

        printSinusoidalOrder(result);
        printSnakeString(result);

    }

    private static void printSnakeString(List<Character>[] sinusoidalOrder) {
        List<Character> result = getSnakeString(sinusoidalOrder);
        System.out.println(result);
    }

    private static List<Character> getSnakeString(List<Character>[] sinusoidalOrder) {
        List<Character> result  = new ArrayList<>();
        for(List<Character> sinusOrder: sinusoidalOrder){
            result.addAll(sinusOrder);
        }
        return result;
    }

    private static void printSinusoidalOrder(List<Character>[] result) {
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
    }

    private static List<Character>[] getSinusoidalOrder(String s) {
        ArrayList<Character> center = new ArrayList<>();
        ArrayList<Character> upper = new ArrayList<>();
        ArrayList<Character> lower = new ArrayList<>();
        Map<Integer, List<Character>> buffer = Map.of(
                0, center,
                1, upper,
                3,lower);

        for(int i = 0; i< s.length(); i++){
            int selected = i%4;
            if(selected==2){
                selected=0;
            }
            for(Map.Entry<Integer, List<Character>> entry:buffer.entrySet()){
                entry.getValue().add(entry.getKey()==selected? s.charAt(i):' ');
            }

        }
        List<Character>[] result = new List[]{upper,center,lower};
        return result;
    }
}
