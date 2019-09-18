import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    static int sup_digit(String n){
        if(n.length() == 1){
            return Integer.parseInt( n );
        }

        int total = 0;
        for(int i=0;i<n.length();i++){
            total+= n.charAt(i) - '0';
        }
        return sup_digit(Integer.toString(total));
    }

    // Complete the superDigit function below.
    static int superDigit(String n, int k) {
        int total = 0;
        for(int i=0;i<n.length();i++){
            total+= n.charAt(i) - '0' ;
        }

        total = total*k;
        return sup_digit(Integer.toString(total));

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        String n = nk[0];

        int k = Integer.parseInt(nk[1]);

        int result = superDigit(n, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
