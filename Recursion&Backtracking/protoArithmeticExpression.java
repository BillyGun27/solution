import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int expression(int[] arr, char[] op,int n,int result){
        if(n == arr.length){
            return result;
        }
        result += arr[n] ;
        System.out.println( arr[n] );
        return expression( arr, op, n+1, result );

    }

    // Complete the arithmeticExpressions function below.
    static String arithmeticExpressions(int[] arr) {
        char[] op = new char[arr.length-1];
        
        System.out.println( expression(arr,op,0,0) );
        return "1+2+3";

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
