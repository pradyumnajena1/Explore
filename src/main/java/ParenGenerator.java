import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParenGenerator {


    public static void main(String[] args) {
        Set<String>  validparens = generateValidParens(3);
        System.out.println(validparens);
    }

    private static Set<String> generateValidParens(int numParens) {
        if(numParens==1){
            Set<String> result = new HashSet<>();
            result.add("()");
            return result;
        }
        Set<String> result = new HashSet<>();
        Set<String> partialResult = generateValidParens(numParens - 1);
        for(String expression:partialResult){
            List<String> newExpressions = generateNewExpressions(expression);
            result.addAll(newExpressions);
        }
        return result;
    }

    private static List<String> generateNewExpressions(String expression) {
        ArrayList<String> result = new ArrayList<>();
        result.add("("+expression+")");
        result.add("()"+expression);
        result.add(expression+"()");
        return result;
    }
}
