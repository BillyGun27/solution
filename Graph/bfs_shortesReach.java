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
        Node tail,current;
        int pos;

        Graph(){
            head = new Node();
            tail = head;
            current = head; 
            pos=0;
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

    // Complete the bfs function below.
    static int[] bfs(int n, int m, int[][] edges, int s) {
        Graph[] graph = new Graph[n+1];
        for(int i=0;i<n+1;i++){
            Graph g = new Graph();
            graph[i] = g ;
        }

        for(int i=0;i<m;i++){
            int src = edges[i][0];
            int dst = edges[i][1];
            graph[src].add(dst);
            graph[dst].add(src);
        }

        int[] visited = new int[graph.length];
        int[] distance = new int[graph.length];

        int[] queue = new int[graph.length];
        int start=0, end=0;
        queue[end] = s;
        end++;
        visited[s] = 1;
        int st = 0;
        while(start<end){
            st = queue[start];
            start++;

            for(int i=0;i<graph[st].size();i++){
                int next = graph[st].get();
                if(visited[next] == 0){
                    visited[next] = 1;

                    distance[next] = distance[st] + 6; 

                    queue[end] = next;
                    end++;

                }
            }
            graph[st].reset();
        }

        int[] resDis = new int[distance.length-2];
        int j=0;
        for(int i=1;i<distance.length;i++){
            if(i != s){
                if(distance[i] == 0){
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

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = bfs(n, m, edges, s);

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