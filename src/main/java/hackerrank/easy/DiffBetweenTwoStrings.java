package hackerrank.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DiffBetweenTwoStrings {

   static class Solution {

        static String[] diffBetweenTwoStrings(String source, String target) {

            Result result = diffBetweenTwoStrings(source,0,source.length()-1,target,0,target.length()-1 );
            return result.tokens.toArray(new String[0]);

            // your code goes here
        }

        static class Result{
            int edits;
            List<String> tokens ;

            public Result() {
                tokens = new ArrayList<>();
            }

            public Result(int edits, List<String> tokens){
                this.edits = edits;
                this.tokens = tokens;
            }

            public Result add(Result result){
                ArrayList<String> strings = new ArrayList<>(tokens);
                strings.addAll(result.tokens);
                return new Result(this.edits+ result.edits, strings);
            }


        }

        static Result diffBetweenTwoStrings(String source,int sstart,int send ,String target,int tstart,int tend ){
            if(sstart>send){
                List<String> edits =
                        new ArrayList<>(target.substring(tstart,tend+1).chars().mapToObj(c-> "+"+ Character.valueOf((char) c) ).collect(Collectors.toList()));
                return new Result(tend-tstart+1,edits);
            }

            if(tstart>tend){
                List<String> edits =
                        new ArrayList(source.substring(sstart,send+1).chars().mapToObj(c-> "-"+Character.valueOf((char) c)).collect(Collectors.toList()));
                return new Result(send-sstart+1,edits);
            }
            // checkin cache
            if(source.charAt(sstart)==target.charAt(tstart)){
                Result result = new Result(1,new ArrayList<>());
                result.tokens.add(""+ source.charAt(sstart));
                Result partial = diffBetweenTwoStrings(source,sstart+1,send,target,tstart+1,tend);
                return result.add(partial);

            }else{

                Result delete = diffBetweenTwoStrings(source,sstart+1,send,target,tstart,tend);
                Result  add = diffBetweenTwoStrings(source,sstart,send,target,tstart+1,tend);

                if(delete.edits<add.edits){
                    Result result = new Result(1,new ArrayList<>());
                    result.tokens.add("-"+ source.charAt(sstart));
                    return result.add(delete);
                }else{
                    Result result = new Result(1,new ArrayList<>());
                    result.tokens.add("+"+ target.charAt(tstart));
                    return result.add(add);
                }
            }


        }

        public static void main(String[] args) {

            String[] result =  diffBetweenTwoStrings("ABCDEFG","ABDFFGH");
            System.out.println(Arrays.toString(result));

        }
    }
}
