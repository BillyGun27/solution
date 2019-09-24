import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int pow(int X, int N){
        if(N == 1){
            return X;
        }

        return X * pow(X,N-1);
    }

    static int powerRec(int X, int N, int C ,int Y){
        if(X == Y){
            return 1;
        }else if(X<Y){
            return 0;
        }
        int total = 0;
        int i=C;
        while(pow(i,N)<=X){
            total += powerRec(X,N,i+1,(Y+pow(i,N)) );
            i++;
        }

        return total;
    }

    // Complete the powerSum function below.
    static int powerSum(int X, int N) {
        
        return powerRec(X, N, 1, 0);

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int X = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int N = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = powerSum(X, N);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
