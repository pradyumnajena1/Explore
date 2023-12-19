package epp.recursion;

import java.util.HashSet;
import java.util.Set;

public class StringsWithBalancedParen {
    public static void main(String[] args) {
        Set<String> balancedParens = getStringsWithBalancedParens(3);
        System.out.println(balancedParens);
    }

    private static Set<String> getStringsWithBalancedParens(int n) {
        if(n==1){
            return Set.of( "()");
        }

        Set<String> parens = getStringsWithBalancedParens(n - 1);
        Set<String> newParens = new HashSet<>();
        for(String s:parens) {


                newParens.add(s + "()");
                newParens.add("()" + s);

                newParens.add("("+ s +")");


        }
        return newParens;
    }
}
