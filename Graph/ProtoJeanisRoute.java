import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    public static class Graph{
        Node head;
        Node current, tail;
        int s;

        Graph(){
            head = new Node();
            current = head;
            tail = head;
            s = 0;
        }

        public void add(int d, int w){
            Node n = new Node(d,w);
            tail.next = n;
            tail = tail.next;
            s++;
        }

        public void next(){
            current = current.next;
        }

        public int get(){
            return current.data;
        }

        public int getWeight(){
            return current.weight;
        }

        public int size(){
            return s;
        }

        public class Node{
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

public static void dfs(int start,int[] target,Graph[] graph ,int[] visited,int distance){
        visited[start] = 1;
        int[] queue = new int[graph.length+1];
        
            
        for(int i=0;i<graph[start].size();i++){
            graph[start].next();
            int vertex = graph[start].get();
            int weight = graph[start].getWeight();
            if(visited[vertex] == 0){
                System.out.println(start+" "+vertex+" "+(distance+weight));
                visited[vertex] = 1;

                boolean found = false;
                for(int j=0;j<target.length;j++){
                    if(vertex == target[j]){
                        System.out.println("found "+vertex+" "+ (distance+weight) );
                        //return;
                        found = true;
                    }
                }
                
                if(!found){
                    dfs(vertex, target, graph, visited, distance+weight);
                }
                
                
            }
        }

        //return;

        

    }

    /*
     * Complete the jeanisRoute function below.
     */
    static int jeanisRoute(int[] city, int[][] roads) {
        /*
        * Write your code here.
        */
        
        Graph[] graph = new Graph[roads.length+2];
        
        for(int i=0;i<graph.length;i++){
            Graph g = new Graph();
            graph[i] = g;    
        }

        for(int i=0;i<roads.length;i++){
            graph[roads[i][0]].add(roads[i][1],roads[i][2]);
            graph[roads[i][1]].add(roads[i][0],roads[i][2]);
        }

        int[] visited = new int[graph.length];
        dfs(city[0],city,graph,visited,0);
       
        return 0;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0].trim());

        int k = Integer.parseInt(nk[1].trim());

        int[] city = new int[k];

        String[] cityItems = scanner.nextLine().split(" ");

        for (int cityItr = 0; cityItr < k; cityItr++) {
            int cityItem = Integer.parseInt(cityItems[cityItr].trim());
            city[cityItr] = cityItem;
        }

        int[][] roads = new int[n-1][3];

        for (int roadsRowItr = 0; roadsRowItr < n-1; roadsRowItr++) {
            String[] roadsRowItems = scanner.nextLine().split(" ");

            for (int roadsColumnItr = 0; roadsColumnItr < 3; roadsColumnItr++) {
                int roadsItem = Integer.parseInt(roadsRowItems[roadsColumnItr].trim());
                roads[roadsRowItr][roadsColumnItr] = roadsItem;
            }
        }

        int result = jeanisRoute(city, roads);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
