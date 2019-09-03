import java.util.*;
public class Solution {

    static int[] visited;
    static void DFS(int current, ArrayList<ArrayList<Integer>> graph ){

        visited[current] = 1;
        System.out.println(current);

        for(int i=0;i<graph.get(current).size();i++){
            if(visited[graph.get(current).get(i)] == 0 ){
                DFS(graph.get(current).get(i), graph);
            }
        }


    }

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

        int node = 5;
        int[][] inp = {{1,2},{2,3},{3,4},{4,5},{2,5}};

        for(int i=0; i<node+1;i++){
            ArrayList<Integer> g = new ArrayList<Integer>();
            graph.add(g);
        }

        for(int i=0; i<inp.length;i++){
            graph.get(inp[i][0]).add(inp[i][1]);
        }

        visited = new int[node+1];
        //System.out.println(graph.get(1).get(0));
        for(int i=1; i<node+1;i++){
            if(visited[i] == 0 ){
                DFS(i, graph);
            }
        }
        

    }
}



