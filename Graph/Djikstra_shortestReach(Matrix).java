import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int minDistance(int[] dist,int[] visited){
        int min_dist=Integer.MAX_VALUE;
        int min_index=0;

        for(int i=1;i<dist.length;i++){
            if(dist[i]<min_dist && visited[i] == 0){
                min_dist = dist[i];
                min_index=i;
            }
        }
        return min_index;
    }

    // Complete the shortestReach function below.
    static int[] shortestReach(int n, int[][] edges, int s) {

        int[][] graph = new int[n+1][n+1];
        for(int i=0;i<edges.length;i++){
            int src = edges[i][0];
            int dst = edges[i][1];
            int weight = edges[i][2];
            
            if(graph[src][dst] == 0 || graph[src][dst] > weight){
                graph[src][dst] = weight;
                graph[dst][src] = weight;
            }
            
             if(graph[dst][src] == 0 || graph[dst][src] > weight){
                graph[src][dst] = weight;
                graph[dst][src] = weight;
            }
            

        }

        int[] visited = new int[n+1];
        int[] distance = new int[n+1];

       
        for(int i=1;i<distance.length;i++){
            distance[i] = Integer.MAX_VALUE;
        }
        distance[s] = 0;
        
        for(int v=1;v<n+1;v++){
            int cur = minDistance(distance,visited);
            visited[cur] = 1;

            for(int next=1;next<n+1;next++){
                if( graph[cur][next] != 0 && visited[next]==0 ){
                    if( distance[next] > (distance[cur]+graph[cur][next]) && distance[cur] != Integer.MAX_VALUE ){
                        distance[next] = distance[cur]+graph[cur][next];
                    }
                }
            }
        }

        
        int[] resDis = new int[distance.length-2];
        int j=0;
        for(int i=1;i<distance.length;i++){
            if(i != s){
                if(distance[i] == Integer.MAX_VALUE){
                    resDis[j] = -1;
                }else{
                    resDis[j] = distance[i];
                } 
                
                j++;
            }
        }
        
        return resDis;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][3];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 3; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = shortestReach(n, edges, s);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
