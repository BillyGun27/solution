import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;

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

        void add(int d,int w){
           /*
            boolean upd = false;
            Node temp = head;
            for(int i=0;i<pos;i++){
                temp = temp.next;
                if(temp.data == d){
                    upd = true;
                    if(w < temp.weight ){
                        temp.weight = w;
                    }
                    
                    break;
                }
            }

            if(!upd){
              */  
                tail.next = new Node(d,w);
                tail = tail.next;
                pos++;
            //}
            
        }
        /*
        void update(int d, int w){
            Node temp = head;
            for(int i=0;i<pos;i++){
                temp = temp.next;
                if(temp.data == d){
                    temp.weight = w;
                    break;
                }
            }
        }
        */

        void next(){
            current = current.next;
        }

        int get(){
            return current.data;
        }

        int getWeight(){
            return current.weight;
        }

        void reset(){
            current = head; 
        }

        int size(){
            return pos;
        }

        class Node{
            int data;
            int weight;
            Node next;

            Node(){
                data = 0;
                weight = 0;
                next = null;
            }

            Node(int d,int w){
                data = d;
                weight = w;
                next = null;
            }
        } 
    }

    static int minDistance(int[] dist,int[] visited){
        int min_dist = Integer.MAX_VALUE;
        int min_index = 0;
        for(int i=1;i<dist.length;i++){
            if(visited[i]==0 && dist[i] < min_dist){
                min_dist = dist[i];
                min_index = i;
            }
        }

        return min_index;
    }

    // Complete the shortestReach function below.
    static int[] shortestReach(int n, int[][] edges, int s) {
        Graph[] graph = new Graph[n+1];
        for(int i=0;i<n+1;i++){
            Graph g = new Graph();
            graph[i] = g ;
        }

        for(int i=0;i<edges.length;i++){
            int src = edges[i][0];
            int dst = edges[i][1];
            int weight = edges[i][2];
            
            graph[src].add(dst,weight);
            graph[dst].add(src,weight);
            
        }

        int[] visited = new int[graph.length];
        int[] distance = new int[graph.length];
        
        for(int i=1;i<distance.length;i++){
            distance[i] = Integer.MAX_VALUE;
        }
        distance[s] = 0;

        for(int v=0;v<n;v++){
            int cur = minDistance(distance,visited);
            visited[cur] = 1;
            //System.out.println(cur);
        
                for(int i=0;i<graph[cur].size();i++){
                    graph[cur].next();
                    int next = graph[cur].get();
                    int weight = graph[cur].getWeight();
                
                    if(visited[next] == 0){  
                        int dist = distance[cur] + weight;
                        if( distance[next] > dist){
                            distance[next] = dist;
                            
                        }
                    }
                }
                graph[cur].reset();
            
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
