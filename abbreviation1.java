import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    private static boolean same;
    private static HashMap<String,Integer> memo =  new HashMap<String,Integer>();

    static void abbr(String a, String b,int sta, int stb){
        int checkAB = (a.length()-sta) - (b.length()-stb) ;
        
        if(same || checkAB < 0 ){
            return;
        }

        if(b.length() == stb){
            String c = a.substring(sta);
            same = c.equals(c.toLowerCase());
            return; 
        }

        if( memo.containsKey(a.substring(sta)+"#"+b.substring(stb)) ){
            return;
        }
        memo.put(a.substring(sta)+"#"+b.substring(stb),1);

        if( Character.isLowerCase( a.charAt(sta) ) ){
            abbr(a,b,sta+1,stb);
        }

        if( Character.toUpperCase( a.charAt(sta) ) !=  b.charAt(stb) ){
            return;
        }

        abbr(a,b,sta+1,stb+1);

        return;

    }

    // Complete the abbreviation function below.
    static String abbreviation(String a, String b) {
        same = false;

        abbr(a,b,0,0);

        if( same ){
            return "YES";
        }else{
            return "NO";
        }
        

    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
