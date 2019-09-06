import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the abbreviation function below.
    static String abbreviation(String a, String b) {
        char[] Aa = a.toCharArray();
        char[] Bb = b.toCharArray();
        int[][] dp = new int[Aa.length+1][Bb.length+1];
        dp[0][0] = 1;
    
        for(int i=0;i<Aa.length;i++){
            for(int j=0;j<Bb.length+1;j++){
                if(dp[i][j]==0){
                    continue;
                }

                if(j<Bb.length && Character.toUpperCase(Aa[i]) == Bb[j]){
                    dp[i+1][j+1]=1;
                }
                if(!Character.isUpperCase(Aa[i])){
                    dp[i+1][j]=1;
                }
            }
        } 

        if( dp[Aa.length][Bb.length] == 1 ){
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
