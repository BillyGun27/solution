import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    static int BFS(int[][][] graph,int[] arrlen ,int[] machines ){
        int[] visited = new int[arrlen.length];
        int[] min = new int[arrlen.length];
        int[] fakeStack = new int[arrlen.length];
        int start = 0;
        int end = 1;

        int s =0;
        fakeStack[start] = machines[0];
        visited[machines[0]] =  1;
        
        //System.out.println("ddfs");
        
        while(start < end){
            s = fakeStack[start] ;
            start++;
            //System.out.println(s);

            for(int i=0;i<arrlen[s];i++){
                int next = graph[s][i][0];
                int edge = graph[s][i][1];
                if(visited[next] == 0){
                    visited[next] = 1;
                    fakeStack[end]=next;

                    if(min[s] == 0){
                        min[next] = edge;
                    }else{
                        if(min[s]<edge){
                            min[next] = min[s];
                        }else{
                             min[next] = edge;
                        }
                         
                    }

                     
                    end++;
                }
            }
        }

        int minTime = 0;
        for(int i=0;i<machines.length;i++){
            minTime += min[machines[i]];

        }
        return minTime;
    }

    // Complete the minTime function below.
    static int minTime(int[][] roads, int[] machines) {
        int totCity = roads.length+1;
        int[][][] graph = new int[totCity][totCity][2];
        int[] arrlen = new int[totCity];

        for(int i=0;i<roads.length;i++){
            int nodeA = roads[i][0];
            int nodeB = roads[i][1];
            int edge = roads[i][2];

            graph[nodeA][arrlen[nodeA]][0] = nodeB;
            graph[nodeA][arrlen[nodeA]][1] = edge;
            arrlen[nodeA]++;

            graph[nodeB][arrlen[nodeB]][0] = nodeA;
            graph[nodeB][arrlen[nodeB]][1] = edge;
            arrlen[nodeB]++;
        }

        /*
        for(int i=0;i<arrlen.length;i++){
            System.out.println(arrlen[i]);
        }
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<arrlen[i];j++){
                System.out.print(graph[i][j][0] + " ");
            }
            System.out.println(" kkk ");
        }*/

        //System.out.println("dada");
        return BFS(graph, arrlen, machines);
        
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] roads = new int[n - 1][3];

        for (int i = 0; i < n - 1; i++) {
            String[] roadsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int roadsItem = Integer.parseInt(roadsRowItems[j]);
                roads[i][j] = roadsItem;
            }
        }

        int[] machines = new int[k];

        for (int i = 0; i < k; i++) {
            int machinesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            machines[i] = machinesItem;
        }

        int result = minTime(roads, machines);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
