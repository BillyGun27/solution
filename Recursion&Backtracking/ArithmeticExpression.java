import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static String printed;
    static boolean found;
    static int[][] dp;
    static void expressions(int[] arr,int cur, long result,char[] ops){
        if(found){
            return;
        }
        if(dp[(int)(result%101L)+101][cur] == 1){
            return;
        }
        
        if(result % 101L == 0 ){
            found = true;
            int i;
            
            for(i=0;i<ops.length;i++){
                char op = '*';
                if(i<cur-1){
                    op = ops[i];
                }

                printed+=arr[i]+Character.toString(op) ;
            }
            printed += arr[i];

            return;
        }
        
        if(cur == arr.length){  
            //System.out.println((result%101)+" "+result ); 
            dp[(int)(result%101L)+101][cur] = 1;
            return;
        }
        
        
        ops[cur-1] = '+';
        long accum = ( (result%101L) + (arr[cur]%101L) %101L);
        expressions(arr,cur+1,accum,ops);

        ops[cur-1] = '*';
        accum = ( (result%101L) * (arr[cur]%101L) %101L );
        expressions(arr,cur+1,accum,ops);

        ops[cur-1] = '-';
        accum = ( (result%101L) - (arr[cur]%101L) %101L );
        expressions(arr,cur+1,accum,ops);

        dp[(int)(result%101L)+101][cur] = 1;
        return ;
    }

    // Complete the arithmeticExpressions function below.
    static String arithmeticExpressions(int[] arr) {
        char[] ops = new char[arr.length-1];
        printed = "";
        found = false;
        dp = new int[202][arr.length+1];

        expressions(arr,1,arr[0],ops);
        return printed;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        String result = arithmeticExpressions(arr);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
