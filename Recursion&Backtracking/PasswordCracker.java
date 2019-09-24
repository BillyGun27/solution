import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

        static Map<Integer, Boolean> dp ;

    public static String cracking(List<String> passwords, String loginAttempt,int attPos,String[] combination, int size ,String check){
        
        if(loginAttempt.equals(check)){
             String p = combination[0];

            for(int j=1;j<size;j++){
                p += " " + combination[j] ;
            }
        
            return p;
        }

        if (dp.containsKey(attPos)) {
            return "WRONG PASSWORD";
        }
    

        for(int i=0;i<passwords.size();i++){
          
            if(loginAttempt.startsWith(passwords.get(i), attPos )){
                combination[size] = passwords.get(i);
                String ck = cracking(passwords,loginAttempt,attPos+passwords.get(i).length(),combination,size+1,check+passwords.get(i) );
                if(ck != "WRONG PASSWORD"){
                    return ck;
                }
            }
            
        }

        dp.put(attPos, false);
        return "WRONG PASSWORD";

    }

    /*
     * Complete the 'passwordCracker' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING_ARRAY passwords
     *  2. STRING loginAttempt
     */

    public static String passwordCracker(List<String> passwords, String loginAttempt) {
    // Write your code here
        dp  = new HashMap<>();
        String[] combination= new String[loginAttempt.length()];

        return cracking(passwords,loginAttempt,0,combination,0,"");
     
        
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<String> passwords = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .collect(toList());

                String loginAttempt = bufferedReader.readLine();

                String result = Result.passwordCracker(passwords, loginAttempt);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
