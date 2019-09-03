import java.util.*;
public class Solution {

    static int[] visited;

    static void DFS(int current, int[][] graph ){

        visited[current] = 1;
        System.out.println(current);

        for(int i=0;i<graph[current].length;i++){
            if(visited[ i ] == 0 && graph[current][i] == 1 ){
                DFS( i , graph);
            }
        }


    }

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */


        int node = 5;
        int[][] inp = {{1,2},{2,3},{3,4},{4,5},{2,5}};

        int[][] graph =  new int[node+1][node+1];


        for(int i=0; i<inp.length;i++){
            graph[inp[i][0]][inp[i][1]] = 1;
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



