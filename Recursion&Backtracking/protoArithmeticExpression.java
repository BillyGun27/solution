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
    static void expressions(int[] arr,int cur, int result,char[] ops){
        if(found){
            return;
        }
        if(cur == arr.length){
            //System.out.println(result);
            if(result % 101 == 0 ){
                found = true;
                int i;
                
                for(i=0;i<ops.length;i++){
                    //System.out.print(ops[i]+" ");
                    printed+=arr[i]+Character.toString(ops[i]) ;
                }
                printed += arr[i];
                //System.out.println(result);

                
            }
            //return result;
             return;
        }
        //System.out.println(cur);
        ops[cur-1] = '+';
        int accum = result + arr[cur];
        expressions(arr,cur+1,accum,ops);

        ops[cur-1] = '*';
        accum = result * arr[cur];
        expressions(arr,cur+1,accum,ops);

        ops[cur-1] = '-';
        accum = result - arr[cur];
        expressions(arr,cur+1,accum,ops);

        
    }

    // Complete the arithmeticExpressions function below.
    static String arithmeticExpressions(int[] arr) {
        char[] ops = new char[arr.length-1];
        printed = "";
        found = false;

        //System.out.println(expressions(arr,1,arr[0])
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
