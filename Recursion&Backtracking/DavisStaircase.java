import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    //memoization and recursive
    
    static int[] memo = new int[37]  ;
    // Complete the stepPerms function below.
    static int stepPerms(int n) {
        if(n == 1){
            return 1;
        }else if(n == 2){
            return 2;
        }else if(n == 3){
            return 4;
        }else if(memo[n] != 0 ){
            return memo[n];
        }else{
            memo[n] = stepPerms(n-1)+stepPerms(n-2)+stepPerms(n-3);
            return memo[n];
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int s = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int sItr = 0; sItr < s; sItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int res = stepPerms(n);

            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
