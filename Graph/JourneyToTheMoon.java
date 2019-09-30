import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    static class Graph{
        Node head;
        Node current, tail;
        int pos;

        Graph(){
            pos = 0;
            head = new Node();
            current = head;
            tail = head;
        }

        void add(int d){
            tail.next = new Node(d);
            tail = tail.next;
            pos++;
        }

        int get(){
            current = current.next;
            return current.data;
        }

        void reset(){
            current = head;
        }

        int size(){
            return pos;
        }


        class Node{
            int data;
            Node next;
            Node(){
                data = 0;
                next = null;
            }

            Node(int d){
                data = d;
                next = null;
            }

        }
    }
    
    static int dfs(int n,Graph[] graph,int[] visited){
        visited[n] = 1;
        int totalNode = 1;
        for(int i=0;i<graph[n].size();i++){
            int next = graph[n].get();
            if(visited[next]==0){
                totalNode+= dfs(next,graph,visited);
            }
        }

        return totalNode ;

    }

    // Complete the journeyToMoon function below.
    static long journeyToMoon(int n, int[][] astronaut) {
        Graph[] graph = new Graph[n];
        for(int i=0;i<n;i++){
            Graph g = new Graph();
            graph[i] = g;
        } 

        for(int i=0;i<astronaut.length;i++){
            int src = astronaut[i][0];
            int dst = astronaut[i][1];

            graph[src].add(dst);
            graph[dst].add(src);
        }

        int[] visited = new int[n];
        int[] group = new int[n];
        int gn = 0;
        for(int i=0;i<n;i++){
            if(visited[i] == 0){
                group[gn] = dfs(i,graph,visited);
                gn++;
            }
            
        }

        long comb = 0;
        for(int i=0;i<gn-1;i++){
            for(int j=i+1;j<gn;j++){
                comb+= group[i]*group[j];
            }
        }
        return comb;
        

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] np = scanner.nextLine().split(" ");

        int n = Integer.parseInt(np[0]);

        int p = Integer.parseInt(np[1]);

        int[][] astronaut = new int[p][2];

        for (int i = 0; i < p; i++) {
            String[] astronautRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int astronautItem = Integer.parseInt(astronautRowItems[j]);
                astronaut[i][j] = astronautItem;
            }
        }

        long result = journeyToMoon(n, astronaut);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
