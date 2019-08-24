import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
        long total = 0;
        int[] groupTot = new int[n];
        char[] groupName = new char[n];
        
        char[] check = s.toCharArray();
        char curchar = check[0];
        int curpos = 0;

        groupName[curpos] = check[0];
        for(int i=0;i<n;i++){
            if(curchar == check[i]){
                groupTot[curpos]++;
            }else{
                curpos++;
                curchar = check[i];
                groupTot[curpos]++;
                groupName[curpos]= curchar;
            }
        }

        for(int j=0;j<curpos+1;j++){
           if(groupTot[j]==1){
                if( j != 0 && j!= curpos ){
                    if(groupName[j-1] == groupName[j+1] ){
                        if(groupTot[j-1]<groupTot[j+1]){
                            total+=groupTot[j-1];
                        }else{
                            total+=groupTot[j+1];
                        }
                    }
                }
                
                total++;
           }else{
               total+= ( groupTot[j] * (groupTot[j]+1) )/2 ;
           }
        }

       

        return total;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
