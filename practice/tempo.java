import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    public static int total;

    static void stepCom(int s,int prev,int curr, int[] arr,int[] arthree,int max){
        if(s > max){
            return;
        }else if(s == max){
            for(int i=0;i<curr;i++){
                System.out.print(arr[i]+" ");
                //arr[i] = 0;
            }

            System.out.println("|"+s+"|");
            
            for(int i=0;i<4;i++){
                System.out.print(arthree[i]+"k");
                arthree[i] = 0;
            }

            System.out.println("|x"+s+"x|");

            total++;
            return;
        }else{
            for(int i=prev;i<4;i++){
                arr[curr] = i;
                arthree[i]++;
                stepCom( s+i,i,curr+1, arr,arthree ,max);
            }
        }
    }

    // Complete the stepPerms function below.
    static int stepPerms(int n) {
        total = 0;
        int[] arr = new int[n];
        int[] arthree = new int[4];
        stepCom(0,1,0,arr,arthree,n);
        return total;

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
