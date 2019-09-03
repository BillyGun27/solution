import java.util.*;
public class Solution {

    static void BFS(int fn , int node ,int[][] graph){
        int[] visited = new int[node+1];
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(fn);
        visited[fn] = 1;

        while(q.size() != 0){
            fn = q.poll();
            System.out.println(fn);

            for(int i=1;i<graph[fn].length;i++){
                if(visited[ i ] == 0 && graph[fn][i] == 1 ){
                    q.add(i);
                    visited[i] = 1;
                }
            }
        }

    }

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

        int node = 5;
        int[][] path = {{1,2},{2,3},{3,4},{4,5},{2,5}};
        
        int[][] matrix_graph = new int[node+1][node+1]; 

         
        for(int i=0;i<path.length;i++){
            matrix_graph[path[i][0]][path[i][1]] = 1;
        }
         

        BFS(1, node ,matrix_graph);

    }
}
