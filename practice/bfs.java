import java.util.*;
public class Solution {
    static void BFS(int fn , int node ,ArrayList<ArrayList<Integer>> graph){
        int[] visited = new int[node+1];
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(fn);
        visited[fn] = 1;

        while(q.size() != 0){
            fn = q.poll();
            System.out.println(fn);

            for(int i=0;i<graph.get(fn).size();i++){
                if(visited[graph.get(fn).get(i)] == 0){
                    q.add(graph.get(fn).get(i));
                    visited[graph.get(fn).get(i)] = 1;
                }
            }
        }

        

    } 

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

        int node = 5;
        int[][] path = {{1,2},{2,3},{3,4},{2,4},{4,5}};

        for(int i=0;i<node+1;i++){
            ArrayList<Integer> g = new ArrayList<Integer>();
            graph.add(g);
        }

        for(int i=0;i<path.length;i++){
            graph.get(path[i][0]).add(path[i][1]);
        }

        BFS(1, node ,graph);

    }
}

