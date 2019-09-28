import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    static int play(int[] board,int[] visited,int position){
        int[] queue = new int[101];
        int start=0,end=0;
        int[] roll = new int[101];
        int minroll = 100;

        visited[position] = 1;
        queue[end] = position;
        end++;

        while(start < end){
            position = queue[start];
            start++;
        
                for(int i=1;i<7;i++){
                    int next =position+i;
                    if(next>=100){
                        next = 100;
                    }
                        //System.out.println(position + " "+board[next]+" "+next);
                        if(board[next] > 0 && visited[board[next]] == 0 ){
                            visited[next] = 1;
                            visited[board[next]] = 1;
                            roll[board[next]] = roll[position] + 1;
                            queue[end] = board[next];
                            end++;
                            //System.out.println("ss"+position + " ("+next+") "+board[next]+" "+roll[board[next]]);
                        }else if(visited[next] == 0){
                            visited[next] = 1;
                            roll[next] = roll[position] + 1;
                            queue[end] = next;
                            end++;
                            //System.out.println("nor"+position + " "+next+" "+roll[next]);
                        }
                        
                    

                    if(next>=100){
                            //roll[next] = roll[position] + 1;
                            //System.out.println("found "+position+" "+next+"/"+board[next]+" " + roll[next]);
                            minroll = roll[next];
                            return minroll;
                            /*if(roll[next]<minroll){
                                minroll = roll[next];
                            }*/
                    }

                }
            
        }
        //System.out.println("nani");
        return -1;
    
    }

    // Complete the quickestWayUp function below.
    static int quickestWayUp(int[][] ladders, int[][] snakes) {
        //System.out.println("___");
        int[] board = new int[101];
        int[] visited = new int[101];

        for(int i=0;i<ladders.length;i++){
            board[ladders[i][0]] = ladders[i][1];
        }

        for(int i=0;i<snakes.length;i++){
            board[snakes[i][0]] = snakes[i][1];
        }

        return play(board,visited,1);

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[][] ladders = new int[n][2];

            for (int i = 0; i < n; i++) {
                String[] laddersRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int laddersItem = Integer.parseInt(laddersRowItems[j]);
                    ladders[i][j] = laddersItem;
                }
            }

            int m = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[][] snakes = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] snakesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int snakesItem = Integer.parseInt(snakesRowItems[j]);
                    snakes[i][j] = snakesItem;
                }
            }

            int result = quickestWayUp(ladders, snakes);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
