import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static class Graph {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        
        public Graph(int size) {
            for(int i=0; i < size ; i++){
                ArrayList<Integer> g = new ArrayList<Integer>();
                graph.add(g);
            }
        }

        public void addEdge(int first, int second) {
            graph.get(first).add(second);
            graph.get(second).add(first);
        }
        
        public int[] shortestReach(int startId) { // 0 indexed
            int[] visited = new int[graph.size()];
            int[] distance = new int[graph.size()];
            LinkedList<Integer> q = new LinkedList<Integer>();
            
            int n = startId;

            visited[n] = 1;
            q.add(n);
            
            while(q.size() != 0){
                n = q.poll();
                //System.out.println("b "+n);

                for(int i=0 ; i<graph.get(n).size() ; i++){
                    int cn = graph.get(n).get(i);
                    if(visited[cn] != 1){
                        visited[cn] = 1;
                        q.add(cn);
                        distance[cn] = distance[n] + 6;
                    }
                }
            }

            //System.out.println(graph.size());
            //int[] summary = new int[graph.size()-1];
            //int j = 0;
            for(int i=0;i<graph.size();i++){
                    if( 0 >= distance[i]){
                        distance[i] = -1;
                    }
            }

            return distance;

        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
      
        int queries = scanner.nextInt();
        
        for (int t = 0; t < queries; t++) {
            
            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();
            
            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;
                
                // add each edge to the graph
                graph.addEdge(u, v);
            }
            
            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);
 
            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();            
        }
        
        scanner.close();
    }
}

