package epp.recursion;

public class RegexMatcher {

    public static void main(String[] args) {
        RegexMatcher regexMatcher = new RegexMatcher();
        boolean matches = regexMatcher.match("aw.*w9$","tytytyawbcw9");
        System.out.println(matches);
    }

    private boolean match(String regex, String text) {
        if(regex.startsWith("^") && regex.endsWith("$")){
            return strictMatch(regex.substring(1,regex.length()-1),text);
        }else if(regex.startsWith("^")){
            for(int i=0;i<=text.length();i++){
                if(strictMatch(regex.substring(1),text.substring(0,i))){
                    return true;
                }
            }
            return false;
        }else if(regex.endsWith("$")){
            for(int i=0;i<text.length();i++){
                if(strictMatch(regex.substring(0,regex.length()-1),text.substring(i))){
                    return true;
                }
            }
            return false;

        }else {
            for(int i=0;i<text.length()-1;i++){
                for (int j=i+1;j<=text.length();j++){
                    if(strictMatch(regex,text.substring(i,j))){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean strictMatch(String regex, String text) {
        System.out.println(" strictMatch regex = "+regex+" text="+text);
        if(regex.length()==0 ){
            return text.length()==0;
        }
        else if(regex.length()==1  ){
            if(text.length()!=1){
                return false;
            }
            if(regex.equals(".")){
                return true;
            }
            return regex.equals(text);

        }else{
              String regPrefix = regex.substring(0,2);
              String regSuffix = regex.substring(2);
              if(regPrefix.charAt(0) == '.' && regPrefix.charAt(1)=='*'){
                  for(int i=0;i<=text.length();i++){
                      String textPrefix = text.substring(0,i);
                      String textSuffix = text.substring(i);
                      if(strictMatch(regSuffix,textSuffix)){
                          return true;
                      }
                  }
                  return false;
              }else if( (Character.isAlphabetic(regPrefix.charAt(0)) || Character.isDigit(regPrefix.charAt(0))) && regPrefix.charAt(1)=='*'){
                  for(int i=0;i<=text.length();i++){
                      String textPrefix = text.substring(0,i);
                      String textSuffix = text.substring(i);
                      if( (textPrefix.length()==0 || containsOnly(textPrefix,regPrefix.charAt(0))) &&  strictMatch(regSuffix,textSuffix)){
                          return true;
                      }
                  }
                  return false;
              }else if(regPrefix.charAt(0) == '.' && (Character.isDigit( regPrefix.charAt(1)) || Character.isAlphabetic( regPrefix.charAt(1)))){
                  if(text.length()<1){
                      return false;
                  }
                  return strictMatch(regex.substring(1),text.substring(1));
              } else {
                  if(text.length()<1){
                      return false;
                  }
                  return regex.charAt(0) == text.charAt(0) && strictMatch(regex.substring(1),text.substring(1));
              }
        }

    }

    private boolean containsOnly(String textPrefix, char ch) {
        for(int i=0;i<textPrefix.length();i++){
            if(textPrefix.charAt(i)!= ch){
                return false;
            }
        }
        return true;
    }
}
