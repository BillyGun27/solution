import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    
    static long dividing(long pileTotal, long[] set, Map<Long, Long> dp){
        long mostmoves = 0;
        

        if (pileTotal == 0 || pileTotal == 1) {
            //System.out.println(0+" end");
            return 0;
        }

        if (dp.containsKey(pileTotal)) {
            return dp.get(pileTotal);
        }

        for(int i=0;i<set.length;i++){
            long moves = 0;
            if( pileTotal % set[i] == 0 && pileTotal != set[i] ){
                long pileNumber = pileTotal/set[i];
                moves += pileNumber * dividing( set[i], set, dp );
                moves += 1;
                //System.out.println( pileTotal + " "+ set[i]+" "+pileNumber+" "+moves);
            }

            if(mostmoves<moves){
                mostmoves = moves;
            }
           
        }

        dp.put(pileTotal, mostmoves);
        //System.out.println(mostmoves+" endv");
        return mostmoves;
    }

    // Complete the stoneDivision function below.
    static long stoneDivision(long n, long[] s) {
        
        return dividing(n,s,new HashMap<>());

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            long n = Long.parseLong(nm[0]);

            int m = Integer.parseInt(nm[1]);

            long[] s = new long[m];

            String[] sItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < m; i++) {
                long sItem = Long.parseLong(sItems[i]);
                s[i] = sItem;
            }

            long result = stoneDivision(n, s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
