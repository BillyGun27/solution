import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
     static class AdjList{
        Node head;
        Node tail;
        Node current;
        int totalNode;

        AdjList(){
            totalNode = 0;
            head = new Node();
            tail = head;
            current = head;
        }

        public void add(int data , int weight){
           totalNode++;
           Node n = new Node(data,weight);
           tail.next = n;
           tail = tail.next;
        }

        //call next first before accessing
        public void next(){
            current = current.next;
        }

        public int getData(){
            return current.data;
        }

        public int getWeight(){
            return current.weight;
        }
        
        public void resetGet(){
            current = head;
        }

        public int size(){
            return totalNode;
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

            Node(int d, int w){
                data = d;
                weight = w;
                next = null;
            }
        }
    }

    static int BFS(AdjList[] graph ,int[] machines ){
        int[] visited = new int[graph.length+1];
        int[] min = new int[graph.length+1];
        int[] fakeStack = new int[graph.length+1];
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

            for(int i=0;i<graph[s].size();i++){
                graph[s].next();
                int next = graph[s].getData();
                int edge = graph[s].getWeight();
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
        AdjList[] graph = new AdjList[totCity];

        for(int i=0;i<totCity;i++){
            AdjList g = new AdjList();
            graph[i] = g; 
        }

        for(int i=0;i<roads.length;i++){
            int nodeA = roads[i][0];
            int nodeB = roads[i][1];
            int edge = roads[i][2];

            graph[nodeA].add(nodeB,edge);
            graph[nodeB].add(nodeA,edge);

        }

        return BFS(graph, machines);
        
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
