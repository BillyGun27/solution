import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
//Binary Search
public class Solution {

    // Complete the minTime function below.
    static long minTime(long[] machines, long goal) {
        Arrays.sort(machines);
    
        long max_day = machines[machines.length-1]*goal; //min_day * min_rep ;
        long min_day = 0;

        long current_prod = 0 ;
        long product = 0;
        
        long day = 0;
        while( min_day!=max_day ){
            day = (max_day+min_day) / 2;
            
            product = 0;
            for(int i=0;i<machines.length;i++){
                product += day/machines[i];
            }

            current_prod = product;

            if(current_prod>=goal){
                max_day = day;
            }else{
                min_day = day+1;
            }

        }

        return min_day ;        

    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nGoal = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nGoal[0]);

        long goal = Long.parseLong(nGoal[1]);

        long[] machines = new long[n];

        String[] machinesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long machinesItem = Long.parseLong(machinesItems[i]);
            machines[i] = machinesItem;
        }

        long ans = minTime(machines, goal);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
