import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the whatFlavors function below.
    static void whatFlavors(int[] cost, int money) {
        
        HashMap<Integer,Integer> ice = new HashMap<Integer,Integer>();

        for(int i=0;i<cost.length;i++){
            if(ice.containsKey(cost[i])){
                if(money == cost[i]*2 ){
                    System.out.println( (ice.get(cost[i])+1)+" "+ (i+1) );
                } 
            }else{
                ice.put(cost[i], i );
            }
            
        }

        for(int i=0;i<cost.length;i++){
            int pair = money - cost[i];
            if( ice.containsKey( pair ) && pair != cost[i] ){
                System.out.println( (i+1)+" "+(ice.get(pair)+1) );
                break;
            }
        }

        /*
        for(int i=0;i<cost.length-1;i++){
            for(int j=i+1;j<cost.length;j++){
                if(cost[i]+cost[j] == money){
                    System.out.println( (i+1)+" "+ (j+1) );
                    break;
                }
            }
        }
        */

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int money = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] cost = new int[n];

            String[] costItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int costItem = Integer.parseInt(costItems[i]);
                cost[i] = costItem;
            }

            whatFlavors(cost, money);
        }

        scanner.close();
    }
}
