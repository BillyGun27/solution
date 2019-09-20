import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {
    static int shortest;

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
            if(current == null){
                current = head;
            }
        }

        public void reset(){
            current = head;
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

public static void dfs(int start,int[] target,Graph[] graph ,int[] visited,int[] searched,int distance){

        visited[start] = 1;
        int[] queue = new int[graph.length+1];
        
            
        for(int i=0;i<graph[start].size();i++){
            graph[start].next();
            int vertex = graph[start].get();
            int weight = graph[start].getWeight();
            if(visited[vertex] < graph[vertex].size()){
                //System.out.println(start+" "+vertex+" "+(distance+weight));
                visited[vertex]++;

                boolean found = false;
                int all_searched = 1;
                for(int j=0;j<target.length;j++){                        
                    if(vertex == target[j] && searched[j] == 0 ){
                            searched[j] = 1;
                            //System.out.println("found "+vertex+" "+ (distance+weight) );
                            found = true;
                    }
                    
                }

                for(int j=0;j<target.length;j++){ 
                    if(searched[j] == 0 ){
                        all_searched = 0;
                    }
                    
                }

                if(all_searched == 1 && found){
                    //System.out.println("all searched" + (distance+weight)  );
                    if(shortest == 0){
                        shortest = distance+weight;
                    }else if( shortest > distance+weight ){
                        shortest = distance+weight;
                    }
                    
                    return;
                }
                
                //if(!found){
                    dfs(vertex, target, graph, visited,searched, distance+weight);
                //}
                
                
            }
        }
        graph[start].reset();
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

        //int[][] memo = new int[graph.length][graph.length]
        
        shortest = 0;

        for(int i=0;i<city.length;i++){
            int[] visited = new int[graph.length];
            int[] searched = new int[city.length];
            searched[i] = 1;
            dfs(city[i],city,graph,visited,searched,0);
        }
        
            
            
       
        return shortest;
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
