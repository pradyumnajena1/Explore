package tlap.chapter1;

import java.util.ArrayList;
import java.util.List;

public class NumberEncoder {
    public static void main(String[] args) {
        NumberEncoder numberEncoder = new NumberEncoder();
        String encoded = numberEncoder.encode(new ArrayList<>(List.of(18, 12312, 171, 763, 98423, 1208, 216, 11, 500, 18, 241, 0, 32, 20620, 27, 10)));
        System.out.println(encoded);
    }

    public String encode(List<Integer> numbers){
      StringBuilder result = new StringBuilder();
      Modes currentMode = Modes.UPPER;
      for(int number:numbers){
          Character aChar = currentMode.getChar(number);
          if(aChar==null){
              currentMode = currentMode.getNext();
          }else{
              result.append(aChar);
          }
      }
      return result.toString();
    }
    private static enum Modes{
        UPPER(27) {
            @Override
            public Character getChar(int number) {
                int reminder = number % UPPER.modulo;
                if(reminder==0){
                    return null;
                }
                return  Character.valueOf((char) ('A'+reminder-1));
            }

            @Override
            public Modes getNext() {
                return LOWER;
            }
        },LOWER(27) {
            @Override
            public Character getChar(int number) {
                int reminder = number % LOWER.modulo;
                if(reminder==0){
                    return null;
                }
                return  Character.valueOf((char) ('a'+reminder-1));
            }

            @Override
            public Modes getNext() {
                return PUNCTUATION;
            }
        },PUNCTUATION(9) {
            private char[] punctuations = new char[]{'0','!','?',',','.',' ',';','"','\''};
            @Override
            public Character getChar(int number) {
                int reminder = number % PUNCTUATION.modulo;
                if(reminder==0){
                    return null;
                }
                return  Character.valueOf((char) (punctuations[reminder]));
            }

            @Override
            public Modes getNext() {
                return UPPER;
            }
        };

        private final int modulo;

        Modes(int modulo) {
            this.modulo = modulo;
        }
        public abstract Character getChar(int number);
        public abstract Modes   getNext( );
    }
}
